package me.davidrdc.poet.deserializer;

/**
 * An exception that is thrown whenever someone tries to serialize a certain file and there's no
 * matching deserializer for that type of file
 *
 * @author David Rodriguez
 * @since 1.0
 */
public class NoDeserializerFoundException extends Exception {

  /**
   * Constructor
   *
   * @param message to display in stack trace
   */
  public NoDeserializerFoundException(String message) {
    super(message);
  }
}
