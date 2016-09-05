package com.mapflat.presentations.funcpatterns

import com.typesafe.scalalogging.StrictLogging

// Domain classes.
class Profile(val name: String) { /* Members not relevant for this example. */ }

// External dependencies, e.g. user service.
trait ServiceProxy {
  // Retrieve user information.
  def retrieveUserProfile(id: Int): Option[Profile] = ???
}

class ScalaSlide extends StrictLogging {

  class User(val id: Int, val services: ServiceProxy) {

    // For comprehension. Option is a 0-1 size container, so loop over it.
    def nameOpt: Option[String] = for {
      userProfile: Profile <- services.retrieveUserProfile(id)
    } yield userProfile.name
  }

}

