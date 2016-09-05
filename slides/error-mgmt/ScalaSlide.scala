package com.mapflat.presentations.funcpatterns

import com.mapflat.presentations.funcpatterns.ScalaDeps._

import scala.util.Try
import scalaz._

class ScalaSlide {

  "Five variants of dual types for type checked error handling."

  "Scala Option"
  val maybeOpt: Option[Int] = riskyOpt()

  "Scala Either"
  val maybeEither: Either[Throwable, Int] = riskyEither()

  "Scalaz Disjunction"
  val maybeDisjunction: \/[Throwable, Int] = riskyDisjunction()

  "Scala Try"
  val maybeTry: Try[Int] = riskyTry()

  "Scalaz Validation"
  val maybeValidation: Validation[Throwable, Int] = riskyValidation()

  "Not covered: scalactic.Or, cats.Xor, cats.Validated"
}
