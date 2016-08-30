package com.mapflat.presentations.funcpatterns;

import com.mapflat.presentations.funcpatterns.JavaDeps.*;

import java.util.List;

class JavaSlide {
    List<String> authors(String json) {
        return JsonPath.read(json, "$.store.book[*].author");
    }
}
