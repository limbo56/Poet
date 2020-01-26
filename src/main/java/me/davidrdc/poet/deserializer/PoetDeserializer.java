package me.davidrdc.poet.deserializer;

import java.io.File;
import java.io.IOException;

/**
 * A deserializer that takes in a {@link File} and converts it to an object
 *
 * @param <T> resultant object type
 * @author David Rodriguez
 */
public interface PoetDeserializer<T> {

  /**
   * Serializes a {@link File} and returns an object of type
   *
   * @param file to parse
   * @return The resultant object
   * @throws IOException io errors thrown by method
   */
  T deserialize(File file) throws IOException;
}
