package me.davidrdc.poet.poet;

import java.io.File;
import java.util.Objects;

/**
 * Class containing utility methods aimed to the access of files
 *
 * @author David Rodriguez
 */
public class Poet {

  /**
   * Gets a {@link File} from the resources folder based on a certain {@link ClassLoader}
   *
   * @param name of the file to get
   * @param loader containing the resources folder
   * @return {@link File}
   */
  public static File getFileFromResources(String name, ClassLoader loader) {
    return new File(Objects.requireNonNull(loader.getResource(name)).getFile());
  }

  /**
   * Gets the home directory of current client
   *
   * @return {@link String} home directory path
   */
  public static String getHomeDirectory() {
    return System.getProperty("user.home");
  }
}
