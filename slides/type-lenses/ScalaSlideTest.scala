package com.mapflat.presentations.funcpatterns

import shapeless._
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
    val anonymized = tryC.map(c => slide.emailWipe(c))
    println(anonymized)
    assert(anonymized.map(c => c.employees(0).email) ===
      Success(Some("anonymous@noname.com")))
  }
}
