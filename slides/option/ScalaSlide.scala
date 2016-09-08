package com.mapflat.presentations.funcpatterns

import com.typesafe.scalalogging.StrictLogging
import org.joda.time.DateTime

// Domain classes.
class Event { /* Members not relevant for this example. */ }
class Profile(val name: String) { /* Members not relevant for this example. */ }

// External dependencies, e.g. user and activity services.
trait ServiceProxy {
  // Retrieve user information.
  def retrieveUserProfile(id: Int): Option[Profile] = ???
  def determineLastActive(userId: Int): Option[DateTime] = ???
}

class ScalaSlide extends StrictLogging {

  class User(val id: Int, val services: ServiceProxy) {

    // For comprehension. Option is a 0-1 size container, so loop over it.
    def nameOpt: Option[String] = for {
      userProfile: Profile <- services.retrieveUserProfile(id)
    } yield userProfile.name
  }

}

