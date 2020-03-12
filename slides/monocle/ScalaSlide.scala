package com.mapflat.presentations.funcpatterns

import monocle.Lens
import monocle.macros.GenLens
import play.api.libs.json.Json
import ScalaDeps._
import scala.util.{Failure, Success, Try}

case class Address(street: String, zip: String, city: String, country: Option[String])
case class Employee(name: String, address: Address, email: String, salary: Option[Int], note: Option[String])
case class Company(name: String, employees: Seq[Employee])

//noinspection TypeAnnotation
class ScalaSlide {
  def readCompany(doc: String): Try[Company] = {
    Json.fromJson[Company](Json.parse(doc)).fold(
      e => Failure(new IllegalArgumentException(e.toString())),
      c => Success(c)
    )
  }

  // Manual lens creation with getter and setter
  def employeeLens(i: Int) = Lens[Company, Employee](
    // Getter
    _.employees(i))(
    // Setter
    elem => old => old.copy(employees =
      old.employees.take(i) ++ Seq(elem) ++ old.employees.drop(i + 1)))

  // GenLens == semi-automatic lens creation
  def streetLens(i: Int) = employeeLens(i) composeLens
    GenLens[Employee](_.address) composeLens
    GenLens[Address](_.street)

  def emailLens(i: Int) = employeeLens(i) ^|-> GenLens[Employee](_.email)

  // Without lenses for comparison
  def setStreetWithoutLens(c: Company, i: Int, street: String) =
    c.copy(employees = c.employees.take(i) ++ Seq(
      c.employees(i).copy(address = c.employees(i).address.copy(
        street = street
      ))
    ) ++ c.employees.drop(i + 1))
}
