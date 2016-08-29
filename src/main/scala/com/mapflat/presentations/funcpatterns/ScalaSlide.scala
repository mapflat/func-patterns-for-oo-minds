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
  class User2(val services: ServiceProxy) extends StrictLogging {

    def sendPush(event: Event) = ???

    def socialEvents(profile: Profile, lastActive: DateTime, friends: Set[Friend]):
      Either[Throwable, Set[Event]] = ???

    def sendPushNotifications(): Unit = {
      val eventsOrError: Either[Throwable, Set[Event]] = for {
        userProfile: Profile <- services.retrieveUserProfile().right
        activityLog: Log <- services.retrieveActivityLog().right
        lastActive: DateTime <- activityLog.determineLastActive().right
        friends: Set[Friend] <- services.retrieveSocialNetwork().right
        events: Set[Event] <- socialEvents(userProfile, lastActive, friends).right
        // numEvents = events.size  (compile error)
      } yield events
      eventsOrError.fold(
        (error: Throwable) => logger.error("Failed to push: ", error),
        (events: Set[Event]) => events.foreach(sendPush)
      )
    }
  }

}
