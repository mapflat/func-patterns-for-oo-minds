package com.mapflat.presentations.funcpatterns

import org.scalatest.FlatSpec

import scala.util.Success
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import play.api.libs.json.Json

@RunWith(classOf[JUnitRunner])
class ScalaSlideTest extends FlatSpec {
  val input =
    Vector(
      """
        |{ "name": "Storbolaget",
        |  "employees": [
        |  { "name": "Ebba",
        |    "address":
        |    { "street": "Stortorget 2",
        |      "zip": "123 56",
        |      "city": "Solna",
        |      "country": "Sweden"
        |    },
        |    "email": "ebba@storb.com",
        |    "note": "Private email: ebba@gmail.com",
        |    "salary": 35000
        |  },
        |  { "name": "Pelle",
        |    "address":
        |    { "street": "Storgatan 1",
        |      "zip": "123 45",
        |      "city": "Stockholm"
        |    },
        |    "email": "pelle@storb.com",
        |    "salary": 30000
        |  }]
        |}""",
      """
        |{ "name": "Småbolaget",
        |  "employees": [
        |  { "name": "Anna",
        |    "address":
        |    { "street": "Lilla Gränd 3",
        |      "zip": "123 67",
        |      "city": "Sollentuna"
        |    },
        |    "email": "anna@smab.com"
        |  }]
        |}
      """).map(_.stripMargin)

  implicit val jsonAddressWrites = Json.writes[Address]
  implicit val jsonEmployeeWrites = Json.writes[Employee]
  implicit val jsonCompanyWrites = Json.writes[Company]

  it should "work" in {
    val slide = new ScalaSlide
    val tryC = slide.readCompany(input(0))
    assert(tryC.map(c => slide.streetLens(0).get(c)) === Success(Some("Stortorget 2")))
    assert(tryC.map(c => slide.anonymize(c).employees.head.email) === Success(Some("<anonymized>")))
    tryC.foreach(c =>
      println(Json.prettyPrint(Json.toJson(slide.anonymize(c))))
    )
  }
}
