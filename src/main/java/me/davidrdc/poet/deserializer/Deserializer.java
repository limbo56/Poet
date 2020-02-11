package me.davidrdc.poet.deserializer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class containing utility methods to deserialize a {@link File} into a certain object
 *
 * @author David Rodriguez
 * @since 1.0
 */
public class Deserializer {

  private static final Map<Class<?>, PoetDeserializer<?>> DESERIALIZERS = new HashMap<>();

  /**
   * Adds a deserializer to the {@link Deserializer#DESERIALIZERS} map
   *
   * @param clazz of the resultant object
   * @param deserializer {@link PoetDeserializer}
   * @param <T> type of the resultant object
   */
  public static <T> void addDeserializer(Class<T> clazz, PoetDeserializer<T> deserializer) {
    DESERIALIZERS.put(clazz, deserializer);
  }

  /**
   * Removes a deserializer from the {@link Deserializer#DESERIALIZERS} map
   *
   * @param clazz of the type to remove
   */
  public static void removeDeserializer(Class<?> clazz) {
    DESERIALIZERS.remove(clazz);
  }

  /**
   * Deserializes a {@link File} into an object
   *
   * <p>This method uses the {@link Deserializer#findBestMatch(Class)} function if the class type
   * passed as a parameter is not in the {@link Deserializer#DESERIALIZERS} map
   *
   * @param file to parse
   * @param clazz to find deserializer
   * @param <T> resultant object
   * @return The serialized file
   * @throws NoDeserializerFoundException if no deserializer was found
   * @throws IOException if an error occurred accessing the file
   */
  public static <T> T deserializeFile(File file, Class<T> clazz)
      throws NoDeserializerFoundException, IOException {
    return (T) getSerializer(clazz).deserialize(file);
  }

  /** Clears the map of deserializers */
  public static void clearDeserializers() {
    DESERIALIZERS.clear();
  }

  private static PoetDeserializer<?> getSerializer(Class<?> clazz)
      throws NoDeserializerFoundException {
    if (!DESERIALIZERS.containsKey(clazz)) {
      return DESERIALIZERS.get(findBestMatch(clazz));
    }
    return DESERIALIZERS.get(clazz);
  }

  private static Class<?> findBestMatch(Class<?> clazz) throws NoDeserializerFoundException {
    return DESERIALIZERS.keySet().stream()
        .filter(classes -> classes.isAssignableFrom(clazz) || classes.isInstance(clazz))
        .findFirst()
        .orElseThrow(
            () ->
                new NoDeserializerFoundException(
                    "No deserializer found for type " + clazz.getName()));
  }
}
