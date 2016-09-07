package com.mapflat.presentations.funcpatterns

import com.mapflat.presentations.funcpatterns.ScalaDeps._

import scala.util.Try
import scalaz._

class ScalaSlide {

  "Five variants of dual types for type checked error handling."

  "Scala Option"
  val maybeOpt: Option[Int] = riskyWithOpt()

  "Scala Either"
  val maybeEither: Either[Throwable, Int] = riskyWithEither()

  "Scalaz Disjunction"
  val maybeDisjunction: \/[Throwable, Int] = riskyWithDisjunction()

  "Scala Try"
  val maybeTry: Try[Int] = riskyWithTry()

  "Scalaz Validation"
  val maybeValidation: Validation[Throwable, Int] = riskyWithValidation()

  "Not covered: scalactic.Or, cats.Xor, cats.Validated"
}
