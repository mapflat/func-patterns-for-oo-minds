package com.mapflat.presentations.funcpatterns

import org.scalatest.FlatSpec

import scala.util.Success

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
    val address = slide.extract[String](input(0))(slide.streetLens)
    println(s"Address:\n$address\n")
    assert(address === Success(Some("Stortorget 2")))

    val email = slide.extract[String](input(0))(slide.emailLens)
    println(s"Email:\n$email\n")
    assert(email === Success(Some("pelle@storb.com")))

    val salary = slide.extract[Int](input(0))(slide.salaryLens)
    println(s"Salary:\n$salary\n")
    assert(salary === Success(Some(30000)))

    val note = slide.extract[String](input(1))(slide.noteLens)
    println(s"Note:\n$note\n")
    assert(note === Success(None))
  }
}
