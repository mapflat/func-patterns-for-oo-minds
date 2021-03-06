package com.mapflat.presentations.funcpatterns

class ScalaSlide {

  """
    Error handling with dual types conclusions and opinions

    * In harmony with type system
      - Control over domain error handling, e.g. defining system vs domain errors

    * There is a learn & mindset threshold for functional style libraries
      - Ascii art culture makes this worse. Examples from scalaz: <<?: <++> |+| =?>= :++>> -+- ?|?
      - Risk of developer cultural divide
      - Scalactic & Cats may be smoother - check them out and tell us :-)

    * You have a great type system, use dedicated error classes
      - Don't reuse value classes for errors.
        - This makes no sense to the untrained eye: "val name: String \/ String"
      - Throwable is already there for you

    * scala.util.* takes you far
  """
}
