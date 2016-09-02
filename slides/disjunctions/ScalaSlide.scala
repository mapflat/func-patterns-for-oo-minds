package com.mapflat.presentations.funcpatterns

import com.typesafe.scalalogging.StrictLogging
import org.joda.time.DateTime

import scalaz.{Disjunction, \/}

class Event
class Friend
class Profile
class Log {
  def determineLastActive(): \/[Throwable, DateTime] = ???
}

trait ServiceProxy {
  def retrieveSocialNetwork(): Disjunction[Throwable, Set[Friend]]

  def retrieveActivityLog(): \/[Throwable, Log]

  def retrieveUserProfile(): Throwable \/ Profile
}

class ScalaSlide {
  class User2(val services: ServiceProxy) extends StrictLogging {

    def sendPush(event: Event) = ???

    def socialEvents(profile: Profile, lastActive: DateTime, friends: Set[Friend]):
      \/[Throwable, Set[Event]] = ???

    def sendPushNotifications(): Unit = {
      val eventsOrError: Disjunction[Throwable, Set[Event]] = for {
        userProfile: Profile <- services.retrieveUserProfile()
        activityLog: Log <- services.retrieveActivityLog()
        lastActive: DateTime <- activityLog.determineLastActive()
        friends: Set[Friend] <- services.retrieveSocialNetwork()
        events: Set[Event] <- socialEvents(userProfile, lastActive, friends)
        numEvents = events.size  // This works.
      } yield events
      eventsOrError.fold(
        (error: Throwable) => logger.error("Failed to push: ", error),
        (events: Set[Event]) => events.foreach(sendPush)
      )
    }
  }

}