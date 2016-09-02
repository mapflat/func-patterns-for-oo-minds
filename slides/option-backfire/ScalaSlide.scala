package com.mapflat.presentations.funcpatterns

import org.joda.time.DateTime

class Event
class Friend
class Profile
class Log {
  def determineLastActive(): Option[DateTime] = ???
}

trait ServiceProxy {
  def retrieveSocialNetwork(): Option[Set[Friend]] = ???

  def retrieveActivityLog(): Option[Log] = ???

  def retrieveUserProfile(): Option[Profile] = ???
}

class ScalaSlide {
  // Error handling with Option. Swallows errors - bad idea.

  class User2(val services: ServiceProxy) {

    def sendPush(event: Event) = ???

    def sendPushNotifications(): Unit = {
      val events: Iterable[Event] = for {
        userProfile: Profile <- services.retrieveUserProfile()
        activityLog: Log <- services.retrieveActivityLog()
        lastActive: DateTime <- activityLog.determineLastActive()
        friends: Set[Friend] <- services.retrieveSocialNetwork()
        events: Event <- socialEvents(userProfile, lastActive, friends)
      } yield events
      // If this does nothing, where did we fail?
      events.foreach(sendPush)
    }

    def socialEvents(profile: Profile, lastActive: DateTime, friends: Set[Friend]): Set[Event] = ???
  }

}
