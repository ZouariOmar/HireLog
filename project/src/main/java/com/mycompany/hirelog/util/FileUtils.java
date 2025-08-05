/**
 * FileUtils.java
 *
 * Attachment utils
 *
 * <p>Detailed explanation of the class, its responsibilities, and usage.</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 05/08/2025
 * @see RelatedClassOrDocumentation
 */

package com.mycompany.hirelog.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

  public static final byte[] fileToByteArray(String filePath) {
    try {
      Path path = Paths.get(filePath);
      return Files.readAllBytes(path);
    } catch (final IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static final String getFileExtension(String filePath) {
    String extension = "";
    int i = filePath.lastIndexOf('.');

    if (i > 0)
      extension = filePath.substring(i + 1);

    return extension;
  }
} // FileUtils class
