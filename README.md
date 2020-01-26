# Poet
[![](https://jitpack.io/v/limbo56/Poet.svg)](https://jitpack.io/#limbo56/Poet)
[![Build Status](https://travis-ci.org/limbo56/Poet.svg?branch=master)](https://travis-ci.org/limbo56/Poet)

Poet is a file management and serialization tool written in Java. Poet aims to ease the process of serializing files and managing directories and their content.

## Installation

To install Poet through Maven, you will need to add the JitPack repository to your `pom.xml` file:
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

Next, you need to add Poet as a dependency:
```xml
<dependency>
    <groupId>com.github.limbo56</groupId>
    <artifactId>Poet</artifactId>
    <version>master-SNAPSHOT</version>
</dependency>
```

Java 8 is the minimum version required to use this tool

That's it for the installation 🎉

## Usage
This project is a library aimed at easing the process of file handling in Java. 
In the next section, I'm going to be showcasing some of the features of this library.

### Deserialization
The [`Deserializer`](https://javadoc.jitpack.io/com/github/limbo56/Poet/master-SNAPSHOT/javadoc/me/davidrdc/poet/deserializer/Deserializer.html) class is useful when you want a file to be serialized into a certain object type.

To add a deserializer, you must call the [`Deserialize#addSerializer`](https://javadoc.jitpack.io/com/github/limbo56/Poet/master-SNAPSHOT/javadoc/me/davidrdc/poet/deserializer/Deserializer.html#addDeserializer-java.lang.Class-me.davidrdc.poet.poet.deserializer.PoetDeserializer-). For example, let's say we want to add a deserializer that transforms files into a [`JsonObject`](https://www.javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/com/google/gson/JsonObject.html). 
To achieve this, we need to add a deserializer as follows:
```java
Deserialize.addDeserializer(
    JsonObject.class, 
    file -> JsonParser.parseReader(new FileReader(file)).getAsJsonObject()
);
```

After that, to deserialize the file, we would need to call the [`Deserialize#deserializeFile`](https://javadoc.jitpack.io/com/github/limbo56/Poet/master-SNAPSHOT/javadoc/me/davidrdc/poet/deserializer/Deserializer.html#deserializeFile-java.io.File-java.lang.Class-) method:
```java
JsonObject object = Deserializer.deserializeFile(file, JsonObject.class);
```

### Directories
The [`Directory`](https://javadoc.jitpack.io/com/github/limbo56/Poet/master-SNAPSHOT/javadoc/me/davidrdc/poet/directories/Directory.html) class is aimed to ease the handling of directories. 
It contains a variety of methods useful for handling files which extends the [`File`](https://docs.oracle.com/javase/8/docs/api/java/io/File.html) class.

#### Bundles
You can bundle multiple directories using the [`DirectoryBundle`](https://javadoc.jitpack.io/com/github/limbo56/Poet/master-SNAPSHOT/javadoc/me/davidrdc/poet/directories/DirectoryBundle.html) 
class. This class helps when you want to access common files in multiple directories. For example, this would be useful for localization.

## License
This project is licensed under the [`MIT`](https://github.com/limbo56/Poet/blob/master/LICENSE) license. 
Make sure you read the license before using this library.