package com.mapflat.presentations.funcpatterns

class ScalaSlide {
  """
   * There are two types of errors
     - Categorisation is domain-dependent
     - E.g. where do I/O errors belong?

   * System errors
     - E.g. I/O, OoM, disk space, network
     - Not much to do at low level
     - Bubble up, to handle at top or crash
     - Java: Unchecked exceptions
     - Scala: Unchecked exceptions

   * Domain errors
     - Need to handle in business logic
     - Justified to force caller to handle
     - Java: Checked exceptions
       - Disharmony with generics
       - Categorisation decision made by Oracle
     - Scala: Dual types
       - Result in case of error-free computation
       - Something else in the case of error
  """
}

