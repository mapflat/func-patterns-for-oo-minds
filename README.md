# Functional patterns for object-oriented minds

Slides and code for presentation to be held by [@rouzwavi](https://github.com/rouzwawi) and
[@lallea](https://github.com/lallea) at [JavaZone 2016](https://2016.javazone.no/).

## Running the presentation

Start IntelliJ. Make a vertical editor window split. Open up slides/slide.md in the left editor pane and
src/main/java/com/mapflat/funcpatterns/JavaSlide.java in the right pane. Start presentation mode. Switch to
src/main/scala/com/mapflat/funcpatterns/ScalaSlide.scala for displaying Scala code. For tests, use
src/test/{java,scala}/com/mapdlat/funcpatterns/{Java,Scala}SlideTest.{java,scala}.

In order to move forward, run the script "bin/slideshow.py next". In order to back up, run "bin/slideshow.py prev". The
slideshow.py script will clear out (stash?) any local changes you have made, and reset the working directory to the
desired slide.


## Creating slides

Each slide is represented by a git branch, with appropriate contents in slide.md and code files. Common content, such as
bin/slideshow.py, is pushed to master, and all branches are to be rebased on the master@HEAD. External dependencies
should be added to pom.xml in master, in order to avoid having IntelliJ reimporting the Maven project on slide changes.

