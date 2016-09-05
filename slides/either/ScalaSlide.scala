package com.mapflat.presentations.funcpatterns

import com.typesafe.scalalogging.StrictLogging
import org.joda.time.DateTime

// Domain classes.
class Event { /* Members not relevant for this example. */ }
class Profile(val name: String) { /* Members not relevant for this example. */ }

// External dependencies, e.g. user and activity services.
trait ServiceProxy {
  // Retrieve user information.
  def retrieveUserProfile(id: Int): Either[Throwable, Profile] = ???
  def determineLastActive(userId: Int): Either[Throwable, DateTime] = ???
}

class ScalaSlide extends StrictLogging {

  class UserPusher(val id: Int, val services: ServiceProxy) {

    // Computes events to be pushed since last
    def news(profile: Profile, lastActive: DateTime): Either[Throwable, Set[Event]] = ???
    // Send an event.
    def sendPush(event: Event) = ???

    def sendPushNotifications(): Unit = {
      // Get info on the user and when we last saw him/her.
      val eventsEither: Either[Throwable, Set[Event]] = for {
        userProfile: Profile <- services.retrieveUserProfile(id).right
        lastActive: DateTime <- services.determineLastActive(id).right
        // From that information, compute news to send the user.
        events: Set[Event] <- news(userProfile, lastActive).right
      } yield events
      eventsEither match {
        case Left(error) => logger.error("Something went wrong:", error)
        case Right(events: Set[Event]) => events.foreach(sendPush)
      }
    }
  }
}

