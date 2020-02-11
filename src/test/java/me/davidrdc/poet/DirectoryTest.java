package me.davidrdc.poet;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import me.davidrdc.poet.directories.Directories;
import me.davidrdc.poet.directories.Directory;
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
            FileUtils.getFileFromResources("testDirectory", DirectoryTest.class.getClassLoader()));
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
    // Add other.yml to directory
    assertDoesNotThrow(
        () -> testDirectory.addFile(new File("other.yml")),
        "Add other.yml to testDirectory directory");

    // Check if file was added to directory
    assertTrue(testDirectory.contains("other.yml"));
  }

  @Test
  @Order(3)
  public void moveFilesTest() {
    // Create new directory
    File testFile = new File(testDirectory.getPath(), "test");
    testFile.mkdir();
    Directory directory = Directories.from(testFile);

    // Move some.txt to test
    assertDoesNotThrow(
        () -> testDirectory.moveFile("some.txt", directory.getPath()),
        "Moving some.txt to test directory");

    // Check if the directory contains the file
    assertDoesNotThrow(() -> assertTrue(directory.contains("some.txt")), "Contains file test");

    // Move file back to testDirectory
    assertDoesNotThrow(
        () -> directory.moveFile("some.txt", testDirectory.getPath()),
        "Moving some.txt to testDirectory class");
  }

  @Test
  @Order(4)
  public void removeFilesFromDirectoryTest() {
    // Remove files from directory test
    assertTrue(testDirectory.removeFile("test"), "File test was removed");
    assertTrue(testDirectory.removeFile("other.yml"), "File other.yml was removed");

    // Check if file was removed from the directory
    assertArrayEqualsSorted(
        new String[]{"some.txt", "test.txt"},
        Objects.requireNonNull(testDirectory.list()),
        "Files were removed");
  }
}
