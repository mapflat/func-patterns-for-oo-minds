package com.mapflat.presentations.funcpatterns

import com.mapflat.presentations.funcpatterns.ScalaDeps.ActivityService
import com.typesafe.scalalogging.StrictLogging
import org.joda.time.DateTime

import scala.util.{Failure, Success, Try}

// Domain classes.
class Event {
  /* Members not relevant for this example. */
}

class Profile(val name: String) {
  /* Members not relevant for this example. */
}

// External dependencies, e.g. user and activity services.
trait ServiceProxy {
  // Can throw exceptions that we want to catch.
  def activityService(): ActivityService

  // Retrieve user information.
  def retrieveUserProfile(id: Int): Try[Profile] = ???

  "Try { my_code } captures and wraps exceptions"
  def determineLastActive(userId: Int): Try[DateTime] = Try {
    activityService().lastActive(userId)
  }
}

class ScalaSlide extends StrictLogging {

  class UserPusher(val id: Int, val services: ServiceProxy) {
    // Computes events to be pushed since last
    def news(profile: Profile, lastActive: DateTime): Try[Set[Event]] = ???

    // Send an event.
    def sendPush(event: Event) = ???

    def sendPushNotifications(): Unit = {
      val eventsTry: Try[Set[Event]] = for {
        // Get info on the user and when we last saw him/her.
        userProfile: Profile <- services.retrieveUserProfile(id)
        lastActive: DateTime <- services.determineLastActive(id)
        // From that information, compute news to send the user.
        events: Set[Event] <- news(userProfile, lastActive)
      } yield events
      eventsTry match {
        case Failure(error) => logger.error("Something went wrong:", error)
        case Success(events: Set[Event]) => events.foreach(sendPush)
      }
    }
  }

  "Try enforces use of Throwable as error class."
}


