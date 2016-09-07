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
  def ooStrategyPatternPrint(t: String, f: Formatter) = if (f.filter(t)) println(f.format(t))

  // Functional strategy pattern
  def funcStrategyPatternPrint(t: String, p: (String) => Boolean, format: (String) => String) = if (p(t)) println(format(t))

  val commandPattern = Map("move" -> move_to_slide, "next" -> next_slide, "prev" -> prev_slide)

  def adapterPattern(formatter: (Int, String) => String) = (text: String, width: Int) => formatter(width, text)

  def observerPattern(text: String, width: Int, lineStatsCollector: (Int) => Unit = {_}): String = {
    val wrapped = WordUtils.wrap(text, width)
    lineStatsCollector(wrapped.split("\n").size)  // Observe line count, ignored by default
    wrapped
  }

  // Chain of responsibility.
  // Clearer?  Depends on taste.
  def authenticate(user: String, authenticators: Seq[(String) => Boolean]) =
    authenticators.map(auth => auth(user)).fold(false)((a, b) => a || b)

  "Likewise with Visitor, Decorator, Interpreter, Builder, Prototype, ..."
}

