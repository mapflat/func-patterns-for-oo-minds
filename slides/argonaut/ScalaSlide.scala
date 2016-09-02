package com.mapflat.presentations.funcpatterns

import argonaut.Argonaut._
import argonaut._

import scala.util.{Failure, Success, Try}
import scalaz.PLens

class ScalaSlide {
  val streetLens = jObjectPL >=> jsonObjectPL("employees") >=> jArrayPL >=> jsonArrayPL(0) >=> jObjectPL >=> jsonObjectPL("address") >=> jObjectPL >=> jsonObjectPL("street") >=> jStringPL

  val emailLens = jObjectPL >=> jsonObjectPL("employees") >=> jArrayPL >=> jsonArrayPL(0) >=> jObjectPL >=> jsonObjectPL("email") >=> jStringPL
  val noteLens = jObjectPL >=> jsonObjectPL("employees") >=> jArrayPL >=> jsonArrayPL(0) >=> jObjectPL >=> jsonObjectPL("note") >=> jStringPL

  def extractString(doc: String)(lens: PLens[Json, JsonString]): Try[Option[String]] = {
    Parse.parse(doc).fold(
      e => Failure(new IllegalArgumentException("Failed to parse json: " + e)),
      json => Success(lens.get(json).map(_.toString))
    )
  }
}
