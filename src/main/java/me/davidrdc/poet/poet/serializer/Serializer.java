package me.davidrdc.poet.poet.serializer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class containing utility methods to serialize {@link File}s into a certain type
 *
 * @author David Rodriguez
 */
public class Serializer {
  private static final Map<Class<?>, PoetSerializer<?>> SERIALIZERS = new HashMap<>();

  /**
   * Adds a serializer to the serializer directory
   *
   * @param clazz of the resultant object
   * @param serializer function that converts the {@link File} to a type {@link T}
   * @param <T> type of the resultant object
   */
  public static <T> void addSerializer(Class<T> clazz, PoetSerializer<T> serializer) {
    SERIALIZERS.put(clazz, serializer);
  }

  /**
   * Removes a serializer from the serializer directory
   *
   * @param clazz of the type to remove
   */
  public static void removeSerializer(Class<?> clazz) {
    SERIALIZERS.remove(clazz);
  }

  /**
   * Parses a {@link File} object and converts it to an object of type {@link T}
   *
   * <p>This method uses the {@link Serializer#findBestMatch(Class)} function if the class type
   * passed as a parameter is not in the {@link Serializer#SERIALIZERS} map
   *
   * @param file to parse
   * @param clazz to find serializer
   * @param <T> resultant object
   * @return {@link T} parsed from a {@link File}
   */
  public static <T> T serializeFile(File file, Class<T> clazz)
      throws NoSerializerFoundException, IOException {
    return (T) getSerializer(clazz).serialize(file);
  }

  /** Clears the map of serializers */
  public static void clearSerializers() {
    SERIALIZERS.clear();
  }

  private static PoetSerializer<?> getSerializer(Class<?> clazz) throws NoSerializerFoundException {
    if (!SERIALIZERS.containsKey(clazz)) {
      return SERIALIZERS.get(findBestMatch(clazz));
    }
    return SERIALIZERS.get(clazz);
  }

  private static Class<?> findBestMatch(Class<?> clazz) throws NoSerializerFoundException {
    return SERIALIZERS.keySet().stream()
        .filter(classes -> classes.isAssignableFrom(clazz) || classes.isInstance(clazz))
        .findFirst()
        .orElseThrow(
            () ->
                new NoSerializerFoundException("No serializer found for type " + clazz.getName()));
  }
}
