package com.mapflat.presentations.funcpatterns

import com.typesafe.scalalogging.StrictLogging
import org.joda.time.DateTime

import scalaz.{-\/, \/, \/-, _}

// Domain classes.
class Event { /* Members not relevant for this example. */ }
class Profile(val name: String) { /* Members not relevant for this example. */ }

// External dependencies, e.g. user and activity services.
trait ServiceProxy {
  // Retrieve user information.
  def retrieveUserProfile(id: Int): Validation[Throwable, Profile] = ???
  def determineLastActive(userId: Int): Validation[Throwable, DateTime] = ???
}

class ScalaSlide extends StrictLogging {

  class UserPusher(val id: Int, val services: ServiceProxy) {
    // Computes events to be pushed since last
    def news(profile: Profile, lastActive: DateTime): Validation[Throwable, Set[Event]] = ???
    // Send an event.
    def sendPush(event: Event) = ???

    def sendPushNotifications(): Unit = {
      val eventsValidated: Validation[Throwable, Set[Event]] = for {
        // Get info on the user and when we last saw him/her.
        userProfile: Profile <- services.retrieveUserProfile(id)
        lastActive: DateTime <- services.determineLastActive(id)
        // From that information, compute news to send the user.
        events: Set[Event] <- news(userProfile, lastActive)
      } yield events
      eventsValidated match {
        case Success(error) => logger.error("Something went wrong:", error)
        case Failure(events: Set[Event]) => events.foreach(sendPush)
      }
    }

    "Essentially the same as scalaz.Disjunction."
  }
}

