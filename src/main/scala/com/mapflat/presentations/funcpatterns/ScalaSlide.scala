package com.mapflat.presentations.funcpatterns

import java.io.Serializable

import monocle.{Lens, PLens}
import monocle.macros.GenLens
import play.api.libs.json.Json

import scala.util.{Failure, Success, Try}

case class Address(street: Option[String], zip: String, city: String, country: Option[String])

case class Employee(name: Option[String], address: Address, email: Option[String], salary: Int, note: Option[String])

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

  // Manual lens creation with getter and setter
  def employeeLens(i: Int) = Lens[Company, Employee](
    _.employees(i))(// Getter
    e => c => c.copy(employees = c.employees.take(i) ++ Seq(e) ++ c.employees.drop(i + 1))) // Setter

  val addrStreetLens = GenLens[Employee](_.address) composeLens GenLens[Address](_.street)

  def streetLens(i: Int) = employeeLens(i) composeLens addrStreetLens

  val piiLenses: List[Lens[Employee, Option[String]]] = List(
    GenLens[Employee](_.email),
    GenLens[Employee](_.name),
    addrStreetLens,
    GenLens[Employee](_.note))

  def anonymize(company: Company): Company = {

    def anonEmployee(lenses: List[Lens[Employee, Option[String]]])(e: Employee): Employee = {

      def applyLens(lens: Lens[Employee, Option[String]], employee: Employee): Employee = lens.modify(_ => Some("<anonymized<"))(employee)

      lenses match {
        case Nil => e
        case lens :: tail => anonEmployee(tail)(applyLens(lens, e))
      }
    }
    company.copy(employees = company.employees.map(anonEmployee(piiLenses)))
  }

}
