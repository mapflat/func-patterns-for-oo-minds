package com.mapflat.presentations.funcpatterns

import org.scalatest.FlatSpec

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
  }
}
