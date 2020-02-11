package me.davidrdc.poet.bundle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import me.davidrdc.poet.directories.Directory;

/**
 * A base implementation of a {@link DirectoryBundle}
 *
 * @author David Rodriguez
 * @since 1.0
 */
public class BaseBundle implements DirectoryBundle {

  private final List<Directory> directories;

  /**
   * Constructor
   *
   * @param directories to be bundled
   */
  public BaseBundle(List<Directory> directories) {
    this.directories = directories;
  }

  /**
   * Constructor
   *
   * @param directories to be bundled
   */
  public BaseBundle(Directory... directories) {
    this(Arrays.asList(directories));
  }

  @Override
  public Directory getDirectory(String path) throws FileNotFoundException {
    return directories.stream()
        .filter(directory -> directory.getPath().equals(path))
        .findFirst()
        .orElseThrow(() -> new FileNotFoundException("Couldn't find directory at path: " + path));
  }

  @Override
  public boolean contains(String name) {
    return directories.stream().anyMatch(directory -> directory.contains(name));
  }

  @Override
  public File getFile(String name) throws FileNotFoundException {
    return directories.stream()
        .filter(directory -> directory.contains(name))
        .findFirst()
        .orElseThrow(FileNotFoundException::new)
        .getFile(name);
  }

  @Override
  public List<Directory> getDirectories() {
    return Collections.unmodifiableList(directories);
  }
}
