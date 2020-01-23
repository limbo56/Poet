package me.davidrdc.poet;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.IOException;
import me.davidrdc.poet.poet.Poet;
import me.davidrdc.poet.poet.serializer.NoSerializerFoundException;
import me.davidrdc.poet.poet.serializer.Serializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SerializerTest {

  @BeforeAll
  static void setUp() {
    // Add json object serializer
    Serializer.addSerializer(
        JsonObject.class, file -> JsonParser.parseReader(new FileReader(file)).getAsJsonObject());
    Serializer.addSerializer(
        YamlMapping.class, file -> Yaml.createYamlInput(file).readYamlMapping());
  }

  @AfterAll
  static void cleanUp() {
    // Remove all serializers
    Serializer.clearSerializers();
  }

  @Test
  public void serializeFileToJsonObjectTest() {
    try {
      JsonObject object =
          Serializer.serializeFile(
              Poet.getFileFromResources("test.json", this.getClass().getClassLoader()),
              JsonObject.class);
      JsonObject contact = object.get("contact").getAsJsonObject();

      assertAll(
          "Json file information",
          () -> assertEquals("John", object.get("firstName").getAsString()),
          () -> assertEquals("Doe", object.get("lastName").getAsString()),
          () -> assertEquals("+13053053005", contact.get("phoneNumber").getAsString()),
          () -> assertEquals("john@doe.com", contact.get("email").getAsString()));
    } catch (NoSerializerFoundException | IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void serializeFileToYmlObjectTest() {
    try {
      YamlMapping mapping =
          Serializer.serializeFile(
              Poet.getFileFromResources("test.yml", this.getClass().getClassLoader()),
              YamlMapping.class);
      YamlMapping contact = mapping.yamlMapping("contact");

      assertAll(
          "Yml file information",
          () -> assertEquals("John", mapping.string("firstName")),
          () -> assertEquals("Doe", mapping.string("lastName")),
          () -> assertEquals("+13053053005", contact.string("phoneNumber")),
          () -> assertEquals("john@doe.com", contact.string("email")));
    } catch (NoSerializerFoundException | IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void serializeNoSerializerTest() {
    assertThrows(
        NoSerializerFoundException.class,
        () ->
            Serializer.serializeFile(
                Poet.getFileFromResources("test.json", this.getClass().getClassLoader()),
                JsonElement.class),
        "No serializer found");
  }
}
