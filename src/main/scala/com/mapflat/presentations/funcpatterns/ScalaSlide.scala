package com.mapflat.presentations.funcpatterns

import monocle.Lens
import monocle.macros.{GenLens, Lenses}
import play.api.libs.json.{JsPath, Json, Reads}

import scala.util.{Failure, Success, Try}

@Lenses case class Address(street: String, zip: String, city: String, country: String)
@Lenses case class Employee(name: String, address: Address, email: String, salary: Int, note: String)
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

  val companyNameLens = GenLens[Company](_.name)

  def employeeLens(i: Int) = Lens[Company, Employee](
    _.employees(i))( // Getter
    e => c => c.copy(employees = c.employees.take(i) ++ Seq(e) ++ c.employees.drop(i + 1)))  // Setter

  def streetLens(i: Int) = employeeLens(i) composeLens Employee.address composeLens Address.street

  def emailLens(i: Int) = employeeLens(i) ^|-> Employee.email

}
