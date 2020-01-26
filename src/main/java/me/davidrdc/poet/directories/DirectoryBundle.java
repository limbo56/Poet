package me.davidrdc.poet.directories;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * A class that groups {@link Directory} and combines them as one
 *
 * @author David Rodriguez
 */
@Getter
@RequiredArgsConstructor
public class DirectoryBundle {

  private final List<Directory> directories;

  /**
   * Constructor
   *
   * @param directories to be bundled
   */
  public DirectoryBundle(Directory... directories) {
    this(Arrays.asList(directories));
  }

  /**
   * Returns a {@link Directory} from the {@link DirectoryBundle} based on the path provided
   *
   * @param path where {@link Directory} is located
   * @return {@link Directory} found
   * @throws FileNotFoundException exception thrown if directory isn't found
   */
  public Directory getDirectory(String path) throws FileNotFoundException {
    return directories.stream()
        .filter(directory -> directory.getPath().equals(path))
        .findFirst()
        .orElseThrow(() -> new FileNotFoundException("Couldn't find directory at path: " + path));
  }

  /**
   * Returns whether this {@link DirectoryBundle} contains a certain file
   *
   * @param name of the file to check
   * @return if the file is in the directory
   */
  public boolean contains(String name) {
    return directories.stream().anyMatch(directory -> directory.contains(name));
  }

  /**
   * Gets a certain file contained in this {@link DirectoryBundle}
   *
   * @param name of the file
   * @return {@link File} found
   * @throws java.io.FileNotFoundException if the file wasn't found
   */
  public File getFile(String name) throws FileNotFoundException {
    return directories.stream()
        .filter(directory -> directory.contains(name))
        .findFirst()
        .orElseThrow(FileNotFoundException::new)
        .getFile(name);
  }
}
