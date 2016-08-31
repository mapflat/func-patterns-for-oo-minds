package com.mapflat.presentations.funcpatterns

import play.api.libs.json.{JsPath, Json}

import scala.util.{Success, Try}

class ScalaSlide {
  val streetLens = JsPath \ "employees" \ 0 \ "address" \ "street"

//  val emailLens = jObjectPL >=> jsonObjectPL("employees") >=> jArrayPL >=> jsonArrayPL(0) >=> jObjectPL >=> jsonObjectPL("email") >=> jStringPL
//  val noteLens = jObjectPL >=> jsonObjectPL("employees") >=> jArrayPL >=> jsonArrayPL(0) >=> jObjectPL >=> jsonObjectPL("note") >=> jStringPL

  def extractString(doc: String)(lens: JsPath): Try[Option[String]] = {
    Success(Json.parse(doc).validate(lens.read[String]).asOpt)

//    Parse.parse(doc).fold(
//      e => Failure(new IllegalArgumentException("Failed to parse json: " + e)),
//      json => Success(lens.get(json).map(_.toString))

  }
}
