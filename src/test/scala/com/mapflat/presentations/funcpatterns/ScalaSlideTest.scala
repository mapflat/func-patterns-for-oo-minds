package com.mapflat.presentations.funcpatterns

import org.scalatest.FlatSpec

import ScalaDeps._

import scala.util.Try

class ScalaSlideTest extends FlatSpec {

  val maybeOpt: Option[Int] = riskyOpt()

  val maybeEither: Either[Throwable, Int] = riskyEither()

  val maybeScalazEither: ZEither[Throwable, Int] = riskyZEither()

  val maybeTry: Try[Int] = riskyTry()
}
