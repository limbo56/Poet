package me.davidrdc.poet.bundle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import me.davidrdc.poet.directories.Directory;

/**
 * Represents a list of {@link Directory} bundled together
 *
 * @author David Rodriguez
 */
public interface DirectoryBundle {

  /**
   * Returns a {@link Directory} from the {@link BaseBundle} based on the path provided
   *
   * @param path where {@link Directory} is located
   * @return {@link Directory} found
   * @throws FileNotFoundException exception thrown if directory isn't found
   */
  Directory getDirectory(String path) throws FileNotFoundException;

  /**
   * Returns whether this {@link BaseBundle} contains a certain file
   *
   * @param name of the file to check
   * @return if the file is in the directory
   */
  boolean contains(String name);

  /**
   * Gets a certain file contained in this {@link BaseBundle}
   *
   * @param name of the file
   * @return {@link File} found
   * @throws java.io.FileNotFoundException if the file wasn't found
   */
  File getFile(String name) throws FileNotFoundException;

  /**
   * Returns a {@link List} with all the {@link Directory} in this bundle
   *
   * @return An unmodifiable {@link List}
   */
  List<Directory> getDirectories();
}
