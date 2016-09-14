package com.mapflat.presentations.funcpatterns

import com.typesafe.scalalogging.StrictLogging
import org.joda.time.DateTime

import scalaz._
import Scalaz._

// Domain classes.
class Event { /* Members not relevant for this example. */ }
class Profile(val name: String) { /* Members not relevant for this example. */ }

// External dependencies, e.g. user and activity services.
trait ServiceProxy {
  // Retrieve user information.
  def retrieveUserProfile(id: Int): Disjunction[Throwable, Profile] = ???
  // "Idiomatic" syntax
  def determineLastActive(userId: Int): Throwable \/ DateTime = ???
}

class ScalaSlide extends StrictLogging {
  class UserPusher(val id: Int, val services: ServiceProxy) {
    // Third syntax variant
    def news(profile: Profile, lastActive: DateTime): \/[Throwable, Set[Event]] = ???

    // Send an event.
    def sendPush(event: Event) = ???

    def sendPushNotifications(): Unit = {
      val eventsDisjunct: Disjunction[Throwable, Set[Event]] = for {

        // Get info on the user and when we last saw him/her.
        userProfile: Profile <- services.retrieveUserProfile(id)
        lastActive: DateTime <- services.determineLastActive(id)

        // From that information, compute news to send the user.
        events: Set[Event] <- news(userProfile, lastActive)
      } yield events

      eventsDisjunct match {
        case -\/(error) => logger.error("Something went wrong:", error)
        case \/-(events: Set[Event]) => events.foreach(sendPush)
      }
    }

    """Looks quite good, assuming you are ok with ascii art.

      But it doesn't compile. :-(
    """
  }
}

