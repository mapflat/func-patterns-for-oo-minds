package com.mapflat.presentations.funcpatterns

import com.mapflat.presentations.funcpatterns.ScalaDeps.legacyService
import com.typesafe.scalalogging.StrictLogging
import org.joda.time.DateTime

import scala.util.{Failure, Success, Try}

class Event

class Friend

class Profile

class Log {
  def determineLastActive(): Try[DateTime] =
    Try {
      legacyService.lastActive()  // Throws exceptions on error
    }
}

trait ServiceProxy {
  def retrieveSocialNetwork(): Try[Set[Friend]]

  def retrieveActivityLog(): Try[Log]

  def retrieveUserProfile(): Try[Profile]
}

class ScalaSlide {
  class User2(val services: ServiceProxy) extends StrictLogging {

    def sendPush(event: Event) = ???

    def socialEvents(profile: Profile, lastActive: DateTime, friends: Set[Friend]):
      Try[Set[Event]] = ???

    def sendPushNotifications(): Unit = {
      val eventsOrError: Try[Set[Event]] = for {
        userProfile: Profile <- services.retrieveUserProfile()
        activityLog: Log <- services.retrieveActivityLog()
        lastActive: DateTime <- activityLog.determineLastActive()
        friends: Set[Friend] <- services.retrieveSocialNetwork()
        events: Set[Event] <- socialEvents(userProfile, lastActive, friends)
        numEvents = events.size // This works.
      } yield events
      eventsOrError match {
        case Failure(error: Throwable) => logger.error("Failed to push: ", error)
        case Success(events: Set[Event]) => events.foreach(sendPush)
      }
    }
  }
}
