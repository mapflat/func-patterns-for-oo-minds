package com.mapflat.presentations.funcpatterns

import monocle.macros.{GenLens, Lenses}
import play.api.libs.json.{JsPath, Json, Reads}

import scala.util.{Failure, Success, Try}

@Lenses("get_") case class Address(street: String, zip: String, city: String, country: String)
@Lenses("get_") case class Employee(name: String, address: Address, email: String, salary: Int, note: String)
case class Company(name: String, employees: Seq[Employee])

class ScalaSlide {
  def readCompany(doc: String): Try[Company] = {
    Json.fromJson[Company](Json.parse(doc)).fold(
      e => Failure(new IllegalArgumentException(e.toString())),
      c => Success(c)
    )
  }

//  val streetLens = JsPath \ "employees" \ 0 \ "address" \ "street"
  val streetLens = GenLens[Company](_.employees(0)) composeLens Employee.get_address composeLens Address.get_street
  def emailLens(i: Int) = GenLens[Company](_.employees(i)) ^|-> Employee.get_email

}
