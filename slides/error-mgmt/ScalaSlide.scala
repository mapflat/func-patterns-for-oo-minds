package com.mapflat.presentations.funcpatterns

import org.joda.time.DateTime

import scala.util.Try
import scalaz._

// Domain classes.
class Event { /* Members not relevant for this example. */ }
class Profile(val name: String) { /* Members not relevant for this example. */ }

// External dependencies, e.g. user and activity services.
trait ServiceProxy {
  // Retrieve user information.
  def retrieveUserProfile(id: Int): Profile = ???
  def determineLastActive(userId: Int): DateTime = ???
}

class ScalaSlide {

  "Five variants of dual types for type checked error handling."

  "Scala Option"
  val profileOpt: Option[Profile] = retrieveUserProfile()

  "Scala Either"
  val profileEither: Either[Throwable, Profile] = retrieveUserProfile()

  "Scalaz Disjunction"
  val profileDisjunction: \/[Throwable, Profile] = retrieveUserProfile()

  "Scala Try"
  val profileTry: Try[Profile] = retrieveUserProfile()

  "Scalaz ValidationNel"
  val profileValidation: ValidationNel[Throwable, Profile] = retrieveUserProfile()

  "Not covered: scalactic.Or, cats.Xor, cats.Validated"
}
