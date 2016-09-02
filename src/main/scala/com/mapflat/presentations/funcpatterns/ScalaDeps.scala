package com.mapflat.presentations.funcpatterns

object ScalaDeps {

  case class EmailAddress(address: String) {
    def isValid = address.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$.")

    def validated() = if (isValid) Some(this) else None
  }

  case class MailAddress(streetLine1: String, streetLine2: String, zip: Int, town: String, country: String) {
    def isValid: Boolean = ???

    def validated() = if (isValid) Some(this) else None
  }

  case class User(id: Int, name: String, email: EmailAddress, snailMail: MailAddress)


  def fetchUserFromDb(): User = ???


  def sendNewsLetter(address: EmailAddress): Unit = ???

  def sendSnailNews(address: MailAddress): Unit = ???


}
