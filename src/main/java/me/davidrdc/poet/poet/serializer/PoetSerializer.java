package me.davidrdc.poet.poet.serializer;

import java.io.File;
import java.io.IOException;

/**
 * A serializer that takes in a {@link File} and converts it to an object of type {@link T}
 *
 * @param <T> resultant object type
 * @author David Rodriguez
 */
public interface PoetSerializer<T> {

  /**
   * Serializes a {@link File} and returns an object of type {@link T}
   *
   * @param file to parse
   * @return {@link T} parsed object
   * @throws IOException io errors thrown by method
   */
  T serialize(File file) throws IOException;
}
