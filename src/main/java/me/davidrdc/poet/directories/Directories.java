package me.davidrdc.poet.directories;

import java.io.File;
import java.nio.file.Path;
import me.davidrdc.poet.bundle.BaseBundle;
import me.davidrdc.poet.bundle.DirectoryBundle;

/**
 * Static factory methods to create {@link Directory} and {@link DirectoryBundle}
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
   * Creates a {@link Directory} based on the path provided
   *
   * @param file {@link File} which is the directory
   * @return {@link Directory}
   */
  public static Directory from(File file) {
    return new Directory(file);
  }

  /**
   * Combines a list of {@link Directory} as one
   *
   * @param directory {@link Directory} to combine
   * @return {@link DirectoryBundle}
   */
  public static DirectoryBundle combine(Directory... directory) {
    return new BaseBundle(directory);
  }
}
