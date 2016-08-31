package com.mapflat.presentations.funcpatterns

import play.api.libs.json.{JsPath, Json}

import scala.util.{Success, Try}

class ScalaSlide {
  val streetLens = JsPath \ "employees" \ 0 \ "address" \ "street"
  val emailLens = JsPath \ "employees" \ 0 \ "email"
  val noteLens = JsPath \ "employees" \ 0 \ "note"

  def extractString(doc: String)(lens: JsPath): Try[Option[String]] = {
    Try {
      Json.parse(doc).validate(lens.read[String]).asOpt
    }
  }
}
