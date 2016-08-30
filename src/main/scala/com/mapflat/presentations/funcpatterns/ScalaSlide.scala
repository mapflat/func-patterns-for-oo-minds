package com.mapflat.presentations.funcpatterns

import com.typesafe.scalalogging.StrictLogging
import org.joda.time.DateTime

import scalaz._
import Scalaz._
import Validation.FlatMap._

class Event
class Friend
class Profile
class Log {
  def determineLastActive(): ValidationNel[Throwable, DateTime] = ???
}

trait ServiceProxy {
  def retrieveSocialNetwork(): ValidationNel[Throwable, Set[Friend]]

  def retrieveActivityLog(): ValidationNel[Throwable, Log]

  def retrieveUserProfile(): ValidationNel[Throwable, Profile]
}

class ScalaSlide {

  class User2(val services: ServiceProxy) extends StrictLogging {

    def sendPush(event: Event) = ???

    def socialEvents(profile: Profile, lastActive: DateTime, friends: Set[Friend]):
      ValidationNel[Throwable, Set[Event]] = ???

    def sendPushNotifications(): Unit = {
      // Validations turned out not to be as simple as in the demos.

      // Wrong, we want it flat: ValidationNel[Throwable, Set[Event]]
      val eventsOrError: Validation[NonEmptyList[Throwable], ValidationNel[Throwable, Set[Event]]] =
        (services.retrieveUserProfile() |@|
          services.retrieveActivityLog().flatMap(_.determineLastActive()) |@|
          services.retrieveSocialNetwork()
          ) apply socialEvents
      // It should be ">=> socialEvents" or something along those lines

      // And this should only be a single level of error handling.
      eventsOrError.fold(
        (error: NonEmptyList[Throwable]) => logger.error("Failed to retrieve user information: ", error),
        eventsOrError2 => eventsOrError2.fold(
          (err: NonEmptyList[Throwable]) => logger.error("Failed to retrieve user social network: ", err),
          (events: Set[Event]) => events.foreach(sendPush)
        )
    )
  }

}

}
