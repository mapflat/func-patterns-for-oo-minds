package com.mapflat.presentations.funcpatterns;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class JavaSlideTest {
    @Test
    public void exampleTest() {
        JavaSlide slide = new JavaSlide();
        assertEquals(1, slide.example(new JavaDeps.ExampleClassDep()));
    }
}
