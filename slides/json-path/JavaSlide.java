package com.mapflat.presentations.funcpatterns;

import com.jayway.jsonpath.JsonPath;

import java.util.List;
import java.util.Map;

class JavaSlide {
  String l = "JsonPath lenses, giving read access to a single node.";

  public String name(String json) {
    return JsonPath.read(json, "$.name");
  }

  // Lens for all employees in company. Weakly typed.
  List<Map<String, Object>> numEmployees(String json) {
    return JsonPath.read(json, "$..employees[*]");
  }

  String p = "JsonPath projections, yielding slices of the structure.";

  // All employee emails
  List<String> emails(String json) {
    return JsonPath.read(json, "$.employees[*].email");
  }

  // Names of all employees with high salary
  List<String> highSalaryNames(String json) {
    return JsonPath.read(json, "$.employees[?(@.salary > 32000)].name");
  }
}
