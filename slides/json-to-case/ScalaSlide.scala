package com.mapflat.presentations.funcpatterns

import play.api.libs.json.Json

import scala.util.{Failure, Success, Try}

case class Address(street: String, zip: String, city: String, country: Option[String])
case class Employee(name: String, address: Address, email: String, salary: Option[Int], note: Option[String])
case class Company(name: String, employees: Seq[Employee])

class ScalaSlide {
  implicit val jsonAddressReads = Json.reads[Address]
  implicit val jsonEmployeeReads = Json.reads[Employee]
  implicit val jsonCompanyReads = Json.reads[Company]

  def readCompany(doc: String): Try[Company] = {
    Json.fromJson[Company](Json.parse(doc)).fold(
      e => Failure(new IllegalArgumentException(e.toString())),
      c => Success(c)
    )
  }
}
