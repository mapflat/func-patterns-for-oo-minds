package com.mapflat.presentations.funcpatterns;

import com.jayway.jsonpath.JsonPath;
import com.mapflat.presentations.funcpatterns.JavaDeps.*;

import java.util.List;
import java.util.Map;

class JavaSlide {
  List<String> authors(String json) {
    return JsonPath.read(json, "$.store.book[*].author");
  }

  List<String> cheapBooks(String json) {
    return JsonPath.read(json, "$.store.book[?(@.price < 10)].title");
  }

  int numBooks(String json) {
    List<Map<String, Object>> books = JsonPath.read(json, "$..book[*]");
    return books.size();
  }

}
