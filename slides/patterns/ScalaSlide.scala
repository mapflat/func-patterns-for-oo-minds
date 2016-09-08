package com.mapflat.presentations.funcpatterns

import ScalaDeps._
import org.apache.commons.lang3.text.WordUtils

class ScalaSlide {
  "Gang of Four patterns - some are trivial with functional constructs"

  // Object-oriented strategy pattern
  trait Formatter{
    def filter(text: String): Boolean
    def format(text: String): String
  }

  def ooStrategyPatternPrint(t: String, f: Formatter) =
    if (f.filter(t)) println(f.format(t))

  
  // Functional strategy pattern
  def funcStrategyPatternPrint(t: String, p: (String) => Boolean, format: (String) => String) =
    if (p(t)) println(format(t))

}

