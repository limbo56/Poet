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
import me.davidrdc.poet.poet.deserializer.Deserializer;
import me.davidrdc.poet.poet.deserializer.NoDeserializerFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DeserializerTest {

  @BeforeAll
  static void setUp() {
    // Add json object deserializer
    Deserializer.addDeserializer(
        JsonObject.class, file -> JsonParser.parseReader(new FileReader(file)).getAsJsonObject());
    Deserializer.addDeserializer(
        YamlMapping.class, file -> Yaml.createYamlInput(file).readYamlMapping());
  }

  @AfterAll
  static void cleanUp() {
    // Remove all serializers
    Deserializer.clearDeserializers();
  }

  @Test
  public void serializeFileToJsonObjectTest() {
    try {
      JsonObject object =
          Deserializer.deserializeFile(
              Poet.getFileFromResources("test.json", this.getClass().getClassLoader()),
              JsonObject.class);
      JsonObject contact = object.get("contact").getAsJsonObject();

      assertAll(
          "Json file information",
          () -> assertEquals("John", object.get("firstName").getAsString()),
          () -> assertEquals("Doe", object.get("lastName").getAsString()),
          () -> assertEquals("+13053053005", contact.get("phoneNumber").getAsString()),
          () -> assertEquals("john@doe.com", contact.get("email").getAsString()));
    } catch (NoDeserializerFoundException | IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void serializeFileToYmlObjectTest() {
    try {
      YamlMapping mapping =
          Deserializer.deserializeFile(
              Poet.getFileFromResources("test.yml", this.getClass().getClassLoader()),
              YamlMapping.class);
      YamlMapping contact = mapping.yamlMapping("contact");

      assertAll(
          "Yml file information",
          () -> assertEquals("John", mapping.string("firstName")),
          () -> assertEquals("Doe", mapping.string("lastName")),
          () -> assertEquals("+13053053005", contact.string("phoneNumber")),
          () -> assertEquals("john@doe.com", contact.string("email")));
    } catch (NoDeserializerFoundException | IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void serializeNoSerializerTest() {
    assertThrows(
        NoDeserializerFoundException.class,
        () ->
            Deserializer.deserializeFile(
                Poet.getFileFromResources("test.json", this.getClass().getClassLoader()),
                JsonElement.class),
        "No deserializer found");
  }
}
