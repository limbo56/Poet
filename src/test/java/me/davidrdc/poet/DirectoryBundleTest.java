package me.davidrdc.poet;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import me.davidrdc.poet.bundle.DirectoryBundle;
import me.davidrdc.poet.directories.Directories;
import me.davidrdc.poet.directories.Directory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DirectoryBundleTest {

  private static DirectoryBundle testBundle;
  private static Directory resourcesDirectory;
  private static Directory testDirectory;

  @BeforeAll
  static void setUp() {
    resourcesDirectory = Directories.from("src/test/resources");
    testDirectory = Directories.from("src/test/resources/testDirectory");
    testBundle = Directories.combine(resourcesDirectory, testDirectory);
  }

  @Test
  public void containsFileTest() {
    assertAll(
        "Bundle contains files test",
        () -> assertTrue(testBundle.contains("test.txt")),
        () -> assertTrue(testBundle.contains("test.json")));
  }

  @Test
  public void getDirectoryTest() {
    assertAll(
        "Bundle contains directory test",
        () -> assertEquals(resourcesDirectory, testBundle.getDirectory("src/test/resources")),
        () ->
            assertEquals(
                testDirectory, testBundle.getDirectory("src/test/resources/testDirectory")),
        () -> assertThrows(FileNotFoundException.class, () -> testBundle.getDirectory("fail")));
  }

  @Test
  public void getFileTest() {
    assertAll(
        "Get files test",
        () -> assertDoesNotThrow(() -> testBundle.getFile("test.json")),
        () -> assertDoesNotThrow(() -> testBundle.getFile("some.txt")));
  }
}
