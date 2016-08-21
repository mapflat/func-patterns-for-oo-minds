package com.mapflat.presentations.funcpatterns

import com.mapflat.presentations.funcpatterns.ScalaDeps._

import scala.util.Try
import scalaz._

class ScalaSlide {

  // Five variants of dual types for type checked error handling.

  val maybeOpt: Option[Int] = riskyOpt()

  val maybeEither: Either[Throwable, Int] = riskyEither()

  val maybeScalazEither: \/[Throwable, Int] = riskyZEither()

  val maybeScalazValidation: Validation[Throwable, Int] = riskyValidation()

  val maybeTry: Try[Int] = riskyTry()

}
