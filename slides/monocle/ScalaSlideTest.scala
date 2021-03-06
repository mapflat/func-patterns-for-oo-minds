package com.mapflat.presentations.funcpatterns

import org.scalatest.FlatSpec

import scala.util.{Success, Try}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ScalaSlideTest extends FlatSpec {
  val input =
    Vector("""
      |{ "name": "Storbolaget",
      |  "employees": [
      |  { "name": "Ebba",
      |    "address":
      |    { "street": "Stortorget 2",
      |      "zip": "123 56",
      |      "city": "Gothenburg",
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
      |      "city": "Lönneberga"
      |    },
      |    "email": "anna@smab.com"
      |  }]
      |}
    """).map(_.stripMargin)

  it should "work" in {
    val slide = new ScalaSlide
    val tryC = slide.readCompany(input(0))
    val street = tryC.map(c => slide.streetLens(0).get(c))
    println(s"Street:\n$street\n")
    assert(street === Success("Stortorget 2"))

    val email = tryC.map(c => slide.emailLens(1).get(c))
    println(s"Email:\n$email\n")
    assert(email === Success("pelle@storb.com"))

    val transformed = tryC.map(c => slide.streetLens(1).set("Storgatan 1A")(c))
    println(s"Transformed:\n$transformed\n")
    assert(transformed.map(c => c.employees(1).address.street) === Success("Storgatan 1A"))
  }
}
