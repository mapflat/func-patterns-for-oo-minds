package com.mapflat.presentations.funcpatterns

import org.scalatest.FlatSpec

import ScalaDeps._

class ScalaSlideTest extends FlatSpec {
  val input =
    """
      |[
      |  { "name": "Storbolaget",
      |    "employees": [
      |    { "name": "Pelle",
      |      "address":
      |      { "street": "Storgatan 1",
      |        "zip": "123 45",
      |        "city": "Stockholm" 
      |      },
      |      "email": "pelle@storb.com",
      |      "salary": 30000
      |    },
      |    { "name": "Ebba",
      |      "address":
      |      { "street": "Stortorget 2",
      |        "zip": "123 56",
      |        "city": "Solna",
      |        "country": "Sweden"
      |      },
      |      "email": "ebba@storb.com",
      |      "note": "Private email ebba@gmail.com",
      |      "salary": 35000
      |    }
      |  },
      |  { "name": "Småbolaget",
      |    "employees": [
      |    { "name": "Anna",
      |      "address":
      |      { "street": "Lilla Gränd 3",
      |        "zip": "123 67",
      |        "city": "Sollentuna"
      |      },
      |      "email": "anna@smab.com",
      |    },
      |  }
      |]
    """.stripMargin

}
