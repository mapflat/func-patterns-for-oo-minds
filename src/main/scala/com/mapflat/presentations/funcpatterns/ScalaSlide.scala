package com.mapflat.presentations.funcpatterns

import com.typesafe.scalalogging.StrictLogging
import org.joda.time.DateTime

class Event
class Friend
class Profile
class Log {
  def determineLastActive(): Either[Throwable, DateTime] = ???
}

trait ServiceProxy {
  def retrieveSocialNetwork(): Either[Throwable, Set[Friend]] = ???

  def retrieveActivityLog(): Either[Throwable, Log] = ???

  def retrieveUserProfile(): Either[Throwable, Profile] = ???
}

class ScalaSlide {
  // Error handling with Option. Swallows errors - bad idea.

  class User2(val services: ServiceProxy) extends StrictLogging {

    def sendPush(event: Event) = ???

    def socialEvents(profile: Profile, lastActive: DateTime, friends: Set[Friend]): Set[Event] = ???

    def sendPushNotifications(): Unit = {
      val eventsOrError: Either[Throwable, Set[Event]] = for {
        userProfile <- services.retrieveUserProfile().right
        activityLog <- services.retrieveActivityLog().right
        lastActive <- activityLog.determineLastActive().right
        friends <- services.retrieveSocialNetwork().right
        events <- socialEvents(userProfile, lastActive, friends)
      } yield events
      eventsOrError.fold(
        error => logger.error("Failed to push: ", error),
        events => events.foreach(sendPush)
      )
    }
  }

}
