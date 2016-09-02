package com.mapflat.presentations.funcpatterns

import ScalaDeps._
import com.typesafe.scalalogging.StrictLogging

class ScalaSlide extends StrictLogging {
  def obtainUser(): Option[User] = {
    try {
      Some(fetchUserFromDb())
    } catch {
      case e: Exception =>
        logger.error("Failed to retrieve user: ", e)
        None
    }
  }

  def doMarketing() = {
    val userOpt = obtainUser()

    // Imperative variant
    if (userOpt.isDefined)
      sendSpam(userOpt.get)
  }

  def sendSpam(user: User): Unit = {
    val emailValidated: Option[EmailAddress] = user.email.validated()
    // Functional
    emailValidated.foreach(sendNewsLetter(_))

    val snailValidated: Option[MailAddress] = user.snailMail.validated()
    // For comprehension
    val snailAddresses = for {
      snailAddress <- snailValidated
      if snailAddress.country == "Norway"
    } yield snailAddress

    snailAddresses.foreach(sendSnailNews)
  }
}

