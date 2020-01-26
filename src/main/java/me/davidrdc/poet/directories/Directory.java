package me.davidrdc.poet.directories;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Objects;

/**
 * This utility class that represents a directory/folder and it provides some utility methods that
 * allow you to read and write to the directory
 *
 * @author David Rodriguez
 */
public class Directory extends File {

  /**
   * Constructor
   *
   * @param file parent directory
   */
  public Directory(File file) {
    this(file.getPath());
  }

  /**
   * Constructor
   *
   * @param path of the directory
   */
  public Directory(String path) {
    super(path);
  }

  /**
   * Adds a file to the current directory
   *
   * @param file to add
   */
  public void addFile(File file) throws IOException {
    File newFile = new File(getPath(), file.getName());
    newFile.getParentFile().mkdirs();
    newFile.createNewFile();
  }

  /**
   * Removes a file from the current directory
   *
   * @param name of the file to remove
   */
  public void removeFile(String name) {
    if (!contains(name)) return;
    new File(getPath(), name).delete();
  }

  /**
   * Moves the current directory and all its contents to a new path
   *
   * <p>This method uses the {@link Files#move(Path, Path, CopyOption...)} method to move the
   * directory and it's contents. The default {@link CopyOption} used is {@link
   * StandardCopyOption#REPLACE_EXISTING}
   *
   * @param path to move directory to
   */
  public void move(String path) throws IOException {
    Files.move(Paths.get(getPath()), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
  }

  /**
   * Moves the current directory and all its contents to a new path
   *
   * <p>This method uses the {@link Files#move(Path, Path, CopyOption...)} method to move the
   * directory and it's contents. The default {@link CopyOption}s used is {@link
   * StandardCopyOption#REPLACE_EXISTING}
   *
   * @param path    to move directory to
   * @param options {@link CopyOption}s to use when calling the {@link Files#move(Path, Path,
   *                CopyOption...)} method
   */
  public void move(String path, CopyOption... options) throws IOException {
    Files.move(Paths.get(getPath()), Paths.get(path), options);
  }

  /**
   * Returns whether this directory contains a certain file
   *
   * @param name of the file to check
   * @return if the file is in the directory
   */
  public boolean contains(String name) {
    return Arrays.stream(Objects.requireNonNull(listFiles()))
        .anyMatch(files -> files.getName().equalsIgnoreCase(name));
  }

  /**
   * Gets the size of this directory
   *
   * @return Directory size in bytes
   * @throws IOException any i/o errors that might occur
   */
  public long getDirectorySize() throws IOException {
    return Files.walk(Paths.get(getPath()))
        .filter(p -> p.toFile().isFile())
        .mapToLong(p -> p.toFile().length())
        .sum();
  }

  /**
   * Gets a certain file contained in this directory
   *
   * @param name of the file
   * @return {@link File} found
   */
  public File getFile(String name) {
    return new File(getPath(), name);
  }
}
