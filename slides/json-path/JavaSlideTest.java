package com.mapflat.presentations.funcpatterns;

import com.google.common.collect.ImmutableList;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;

public class JavaSlideTest {
  JavaSlide slide = new JavaSlide();

  ImmutableList<String> json = ImmutableList.of(
      "{ \"name\": \"Storbolaget\",\n" +
          "        \"employees\": [\n" +
          "        { \"name\": \"Ebba\",\n" +
          "          \"address\":\n" +
          "          { \"street\": \"Stortorget 2\",\n" +
          "            \"zip\": \"123 56\",\n" +
          "            \"city\": \"Gothenburg\",\n" +
          "            \"country\": \"Sweden\"\n" +
          "          },\n" +
          "          \"email\": \"ebba@storb.com\",\n" +
          "          \"note\": \"Private email: ebba@gmail.com\",\n" +
          "          \"salary\": 35000\n" +
          "        },\n" +
          "        { \"name\": \"Pelle\",\n" +
          "          \"address\":\n" +
          "          { \"street\": \"Storgatan 1\",\n" +
          "            \"zip\": \"123 45\",\n" +
          "            \"city\": \"Stockholm\"\n" +
          "          },\n" +
          "          \"email\": \"pelle@storb.com\",\n" +
          "          \"salary\": 30000\n" +
          "        }]\n" +
          "}",
      "{ \"name\": \"Småbolaget\",\n" +
          "        \"employees\": [\n" +
          "        { \"name\": \"Anna\",\n" +
          "          \"address\":\n" +
          "          { \"street\": \"Lilla Gränd 3\",\n" +
          "            \"zip\": \"123 67\",\n" +
          "            \"city\": \"Lönneberga\"\n" +
          "          },\n" +
          "          \"email\": \"anna@smab.com\"\n" +
          "        }]\n" +
          "      }");

  @Test
  public void exampleTest() {
    String name = slide.name(json.get(0));
    System.out.println(name);
    assertThat(name, equalTo("Storbolaget"));

    List<Map<String, Object>> employees = slide.numEmployees(json.get(1));
    System.out.println(employees);
    assertThat(employees, hasSize(1));

    List<String> emails = slide.emails(json.get(0));
    System.out.println(emails);
    assertThat(emails, hasItems("ebba@storb.com", "pelle@storb.com"));

    List<String> wellPaid = slide.highSalaryNames(json.get(0));
    System.out.println(wellPaid);
    assertThat(wellPaid, hasItems("Ebba"));
    assertThat(wellPaid, not(hasItems("Pelle")));
  }
}
