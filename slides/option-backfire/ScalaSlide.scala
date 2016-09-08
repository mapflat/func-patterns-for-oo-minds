package com.mapflat.presentations.funcpatterns

import com.typesafe.scalalogging.StrictLogging

// Domain classes.
class Event { /* Members not relevant for this example. */ }
class Profile(val name: String) { /* Members not relevant for this example. */ }
class Log { /* Members not relevant for this example. */ }

// External dependencies, e.g. user and activity services.
trait ServiceProxy {
  // Retrieve user information.
  def retrieveUserProfile(id: Int): Option[Profile] = ???
  // Retrieve a user's activities
  def retrieveActivityLog(userId: Int): Option[Log] = ???
}

class ScalaSlide extends StrictLogging {
  // Error handling with Option. Swallows errors - bad idea.

  class UserPusher(val id: Int, val services: ServiceProxy) {

    // Computes events to be pushed, given a user's activities
    def news(profile: Profile, activityLog: Log): Option[Set[Event]] = ???
    // Send an event.
    def sendPush(event: Event) = ???

    def sendPushNotifications(): Unit = {
      val eventsOpt: Option[Set[Event]] = for {
        // Get info on the user and what the user has been up to.
        userProfile: Profile <- services.retrieveUserProfile(id)
        activityLog: Log <- services.retrieveActivityLog(id)
        // From that information, compute news to send the user.
        events: Set[Event] <- news(userProfile, activityLog)
      } yield events
      
      eventsOpt match {
        case None => logger.error("Something went wrong, but I don't know what")
        case Some(events: Set[Event]) => events.foreach(sendPush)
      }
    }
  }
}
