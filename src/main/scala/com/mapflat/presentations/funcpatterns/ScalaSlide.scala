package com.mapflat.presentations.funcpatterns

import play.api.libs.json.{JsPath, Json, Reads}

import scala.util.{Success, Try}

class ScalaSlide {
  val streetLens = JsPath \ "employees" \ 0 \ "address" \ "street"
  val emailLens = JsPath \ "employees" \ 1 \ "email"
  val salaryLens = JsPath \ "employees" \ 1 \ "salary"
  val noteLens = JsPath \ "employees" \ 0 \ "note"

  def extract[T](doc: String)(lens: JsPath)(implicit fromJson: Reads[T]): Try[Option[T]] = {
    Try {
      Json.parse(doc).validate(lens.read[T]).asOpt
    }
  }
}
