package me.davidrdc.poet;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import me.davidrdc.poet.poet.Poet;
import me.davidrdc.poet.poet.directories.Directory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DirectoryTest {
  private static Directory testDirectory;

  @BeforeAll
  static void setUp() {
    DirectoryTest.testDirectory =
        new Directory(
            Poet.getFileFromResources("testDirectory", DirectoryTest.class.getClassLoader()));
  }

  @Test
  @Order(1)
  void getFilesFromDirectoryTest() {
    // Check for files currently in directory
    assertEquals(
        Arrays.asList("some.txt", "test.txt"),
        Arrays.asList(Objects.requireNonNull(testDirectory.list())),
        "Files in directory");
  }

  @Test
  @Order(2)
  public void addFilesToDirectoryTest() {
    try {
      testDirectory.addFile(new File("other.yml"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Check if file was added to directory
    assertEquals(
        Arrays.asList("other.yml", "some.txt", "test.txt"),
        Arrays.asList(Objects.requireNonNull(testDirectory.list())),
        "Add file to directory");
  }

  @Test
  @Order(3)
  public void removeFilesFromDirectoryTest() {
    testDirectory.removeFile("other.yml");

    // Check if file was removed from the directory
    assertEquals(
        Arrays.asList("some.txt", "test.txt"),
        Arrays.asList(Objects.requireNonNull(testDirectory.list())),
        "Remove file from directory");
  }

  @Test
  @Order(4)
  public void getDirectorySizeTest() {
    assertDoesNotThrow(
        () ->
            assertEquals(
                6365, new Directory("src/test/resources").getDirectorySize(), "Directory size"));
  }
}
