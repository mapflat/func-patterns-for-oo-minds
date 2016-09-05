package com.mapflat.presentations.funcpatterns

import com.typesafe.scalalogging.StrictLogging
import org.joda.time.DateTime

import scalaz.Scalaz._
import scalaz._

// Domain classes.
class Event { /* Members not relevant for this example. */ }
class Profile(val name: String) { /* Members not relevant for this example. */ }

// External dependencies, e.g. user and activity services.
trait ServiceProxy {
  // Retrieve user information.
  def retrieveUserProfile(id: Int): ValidationNel[Throwable, Profile] = ???
  def determineLastActive(userId: Int): ValidationNel[Throwable, DateTime] = ???
}

class ScalaSlide extends StrictLogging {

  class UserPusher(val id: Int, val services: ServiceProxy) {
    // Computes events to be pushed since last
    def news(profile: Profile, lastActive: DateTime): ValidationNel[Throwable, Set[Event]] = ???
    // Send an event.
    def sendPush(event: Event) = ???

    def sendPushNotifications(): Unit = {
      // Get info on the user and when we last saw him/her.
      val eventsValidated: ValidationNel[Throwable, ValidationNel[Throwable, Set[Event]]] =
        (services.retrieveUserProfile(id) |@|
          services.determineLastActive(id)
          // From that information, compute news to send the user.
          ) apply news
      eventsValidated match {
        case Failure(error: NonEmptyList[Throwable]) => error.foreach(logger.error("Something went wrong:", _))
        case Success(eventsInner) => eventsInner match {
          case Failure(error) => error.foreach(logger.error("Something went wrong:", _))
          case Success(events: Set[Event]) => events.foreach(sendPush)
        }
      }

      """
        * ValidationNel does not shortcut, and collects all errors encountered.
        * Powerful, but difficult to use. Given a limited time box, I failed to flatten code
          above to one error handling level.
        * Justified when collecting all errors is important, e.g. user form validation.
      """
    }
  }
}
