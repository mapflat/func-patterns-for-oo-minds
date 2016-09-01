# Functional patterns for object-oriented minds

Slides and code for presentation to be held by [@rouzwavi](https://github.com/rouzwawi) and
[@lallea](https://github.com/lallea) at [JavaZone 2016](https://2016.javazone.no/).

## Prerequisites

* JDK 8
* Scala 2.11
* Maven
* Grip
* Python 3


## Running the presentation

Start IntelliJ on the right portion of the screen. Run "bin/slideshow.py start". Move chrome to the left side.

Open up src/main/java/com/mapflat/funcpatterns/JavaSlide.java in IntelliJ. Increase font sizes. Switch to
src/main/scala/com/mapflat/funcpatterns/ScalaSlide.scala for displaying Scala code. For tests, use
src/test/{java,scala}/com/mapdlat/funcpatterns/{Java,Scala}SlideTest.{java,scala}.

In order to move forward, run the "bin/slideshow.py next". In order to back up, run "bin/slideshow.py prev". The
slideshow.py script will clear out any local changes you have made, and reset the working directory to the
desired slide.


## Creating slides

Each slide is represented by a git branch, with appropriate contents in slide.md and code files. Common content, such as
bin/slideshow.py, is pushed to master, and all branches are to be rebased on the master@HEAD. External dependencies
should be added to pom.xml in master, in order to avoid having IntelliJ reimporting the Maven project on slide changes.


## TODO

Copy branches to dirs.

Shorten code.

List other libraries: Scalactic, Cats.

Intro with patterns.

