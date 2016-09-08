package com.mapflat.presentations.funcpatterns

import com.mapflat.presentations.funcpatterns.ScalaDeps._

import scala.util.Try
import scalaz._

// Domain classes.
class Event { /* Members not relevant for this example. */ }
class Profile(val name: String) { /* Members not relevant for this example. */ }
class Log { /* Members not relevant for this example. */ }

// External dependencies, e.g. user and activity services.
trait ServiceProxy {
  // Retrieve user information.
  def retrieveUserProfile(id: Int): Profile = ???
  // Retrieve a user's activities
  def retrieveActivityLog(userId: Int): Log = ???
}

class ScalaSlide {

  "Five variants of dual types for type checked error handling."

  "Scala Option"
  val profileOpt: Option[Int] = retrieveUserProfile()

  "Scala Either"
  val profileEither: Either[Throwable, Int] = retrieveUserProfile()

  "Scalaz Disjunction"
  val profileDisjunction: \/[Throwable, Int] = retrieveUserProfile()

  "Scala Try"
  val profileTry: Try[Int] = retrieveUserProfile()

  "Scalaz Validation"
  val profileValidation: Validation[Throwable, Int] = retrieveUserProfile()

  "Not covered: scalactic.Or, cats.Xor, cats.Validated"
}
