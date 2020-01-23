package me.davidrdc.poet.poet.directories;

import java.nio.file.Path;

/**
 * Class containing utility methods for the {@link Directory} class.
 *
 * @author David Rodriguez
 */
public class Directories {

  /**
   * Creates a {@link Directory} based on the path provided
   *
   * @param path {@link Path} were the directory is located
   * @return {@link Directory}
   */
  public static Directory from(Path path) {
    return new Directory(path.toFile());
  }

  /**
   * Creates a {@link Directory} based on the path provided
   *
   * @param path {@link String} were directory is located
   * @return {@link Directory}
   */
  public static Directory from(String path) {
    return new Directory(path);
  }

  /**
   * Combines a list of {@link Directory} as one
   *
   * @param directory {@link Directory} to combine
   * @return {@link DirectoryBundle} bundle of directories
   */
  public static DirectoryBundle combine(Directory... directory) {
    return new DirectoryBundle(directory);
  }
}
