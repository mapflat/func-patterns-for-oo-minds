package com.mapflat.presentations.funcpatterns

import com.mapflat.presentations.funcpatterns.ScalaDeps._
import play.api.libs.json.Json
import shapeless._
import poly._
import scala.util.{Failure, Success, Try}

case class Address(street: Option[String], zip: String, city: String, country: Option[String])

case class Employee(name: Option[String], address: Address, email: Option[String], salary: Option[Int], note: Option[String])

case class Company(name: String, employees: Seq[Employee])

class ScalaSlide {
  def readCompany(doc: String): Try[Company] = {
    Json.fromJson[Company](Json.parse(doc)).fold(
      e => Failure(new IllegalArgumentException(e.toString())),
      c => Success(c)
    )
  }

  // Compact lens syntax
  val employeesLens = lens[Company].employees

  val emailPattern = "\\w+@(\\w+\\.)+\\w+".r  // Very simplifed...
  object anonymizeEmail extends ->((s: String) => emailPattern.replaceAllIn(s, "anonynous@noname.com"))

  def emailWipe(c: Company): Company = everywhere(anonymizeEmail)(c)
}
