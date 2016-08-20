package com.mapflat.presentations.funcpatterns

import com.mapflat.presentations.funcpatterns.ScalaDeps._

import scala.util.Try
import scalaz._

class ScalaSlide {

  val maybeOpt: Option[Int] = riskyOpt()

  val maybeEither: Either[Throwable, Int] = riskyEither()

  val maybeScalazEither: \/[Throwable, Int] = riskyZEither()

  val maybeTry: Try[Int] = riskyTry()

}
