package com.mapflat.presentations.funcpatterns

import com.mapflat.presentations.funcpatterns.ScalaDeps._
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
