# scala.Either

* Asymmetric dual type - A object or B object
* Right-biased
  - Defaults to right projection
  - For comprehension out of the box
* Designed for error-handling

Issues:
* Ascii-art warning
  - Slippery slope to incomprehensible code
  - "Concise" and "idiomatic" code -> steeper learning curve
  - Risk of cultural split among engineers
* Conventions to remember
* Does not enforce distinct error types
  - **Avoid \/[String, String]**
