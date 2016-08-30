package com.mapflat.presentations.funcpatterns;

import com.jayway.jsonpath.JsonPath;
import com.mapflat.presentations.funcpatterns.JavaDeps.*;

import java.util.List;

class JavaSlide {
  List<String> authors(String json) {
    return JsonPath.read(json, "$.store.book[*].author");
  }

  List<String> cheapBooks(String json) {
    return JsonPath.read(json, "$.store.book[?(@.price < 10)]");
  }

  int numBooks(String json) {
    return JsonPath.read(json, "$..book.length()");
  }

}
