package com.mapflat.presentations.funcpatterns;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class JavaSlideTest {

    ""
    String json =
        "{ \"name\": \"Storbolaget\",\n" +
        "        \"employees\": [\n" +
        "        { \"name\": \"Ebba\",\n" +
        "          \"address\":\n" +
        "          { \"street\": \"Stortorget 2\",\n" +
        "            \"zip\": \"123 56\",\n" +
        "            \"city\": \"Solna\",\n" +
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
        "\n" +
        "      { \"name\": \"Småbolaget\",\n" +
        "        \"employees\": [\n" +
        "        { \"name\": \"Anna\",\n" +
        "          \"address\":\n" +
        "          { \"street\": \"Lilla Gränd 3\",\n" +
        "            \"zip\": \"123 67\",\n" +
        "            \"city\": \"Sollentuna\"\n" +
        "          },\n" +
        "          \"email\": \"anna@smab.com\"\n" +
        "        }]\n" +
        "      }";

    @Test
    public void exampleTest() {
        JavaSlide slide = new JavaSlide();
        assertThat(slide.authors(json), hasItems("Nigel Rees", "Evelyn Waugh", "Herman Melville", "J. R. R. Tolkien"));
        assertThat(slide.cheapBooks(json), hasItems("Sayings of the Century", "Moby Dick"));
        assertThat(slide.numBooks(json), equalTo(4));
    }
}
