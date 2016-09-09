# Functional patterns for object-oriented minds

Slides and code for presentation to be held by[@lallea](https://github.com/lallea) at [JavaZone
2016](https://2016.javazone.no/).

## Prerequisites

* JDK 8
* Scala 2.11
* Maven
* Python 3


## Running the presentation

* Start IntelliJ and open this project.
* Create run configurations that execute "bin/slideshow.py next" and "bin/slideshow.py prev".
* Enter presentation mode.
* Open the class ScalaSlide.
* Move between the slides by executing the "next" and "prev" runners above. If write_mode is set to True in
  slideshow.py, any changes will be saved, otherwise discarded.
* When entering the second part of the presentation, with lenses, vertically split the editor pane and open
  ScalaSlideTest.
* Switch to JavaSlide and JavaSlideTest for the JsonPath slide, and then back to ScalaSlide + ScalaSlideTest.
* Two slides refer to Spotify production code, which is not included.


