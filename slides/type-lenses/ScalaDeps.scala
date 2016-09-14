package com.mapflat.presentations.funcpatterns

import play.api.libs.json.Json

object ScalaDeps {
  implicit val jsonAddressReads = Json.reads[Address]
  implicit val jsonEmployeeReads = Json.reads[Employee]
  implicit val jsonCompanyReads = Json.reads[Company]

  implicit val jsonAddressWrites = Json.writes[Address]
  implicit val jsonEmployeeWrites = Json.writes[Employee]
  implicit val jsonCompanyWrites = Json.writes[Company]


}
