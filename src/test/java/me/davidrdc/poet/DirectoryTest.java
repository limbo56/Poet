package me.davidrdc.poet;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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

  private static void assertArrayEqualsSorted(Object[] array1, Object[] array2, String message) {
    Arrays.sort(array1);
    Arrays.sort(array2);
    assertArrayEquals(array1, array2, message);
  }

  @Test
  @Order(1)
  void getFilesFromDirectoryTest() {
    // Check for files currently in directory
    assertArrayEqualsSorted(
        new String[] {"some.txt", "test.txt"},
        Objects.requireNonNull(testDirectory.list()),
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
    assertArrayEqualsSorted(
        new String[] {"other.yml", "some.txt", "test.txt"},
        Objects.requireNonNull(testDirectory.list()),
        "Add file to directory");
  }

  @Test
  @Order(3)
  public void removeFilesFromDirectoryTest() {
    testDirectory.removeFile("other.yml");

    // Check if file was removed from the directory
    assertArrayEqualsSorted(
        new String[] {"some.txt", "test.txt"},
        Objects.requireNonNull(testDirectory.list()),
        "Remove file from directory");
  }
}
