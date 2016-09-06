package com.mapflat.presentations.funcpatterns

class ScalaSlide {
  """
   * System errors
     - E.g. I/O, OoM, disk space, network
     - Not much to do at low level
     - Bubble up, handle high or crash
     - Java: Unchecked exceptions
     - Scala: Unchecked exceptions

   * Domain errors
     - Need to handle in business logic
     - Justified to force caller to handle
     - Java: Checked exceptions
       - Not perfect. Disharmony with generics
     - Scala: Dual types
       - Result in case of error-free computation
       - Something else in the case of error

  """
}

