package me.davidrdc.poet.poet.serializer;

/**
 * An exception that is thrown whenever someone tries to serialize a certain file and there's no
 * matching serializer for that type of file
 *
 * @author David Rodriguez
 */
public class NoSerializerFoundException extends Exception {

  /**
   * Constructor
   *
   * @param message to display in stack trace
   */
  public NoSerializerFoundException(String message) {
    super(message);
  }
}
