/**
 * DatabaseConnect.java
 *
 * Database connection manager
 *
 * <p>NONE</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 28/07/2025
 * @see https://github.com/ZouariOmar/HireLog/blob/main/project/src/main/java/com/mycompany/HireLog/util/DatabaseConnect.java
 */

// `DatabaseConnect` pkg name
package com.mycompany.HireLog.util;

// Java I/O core imports
import java.io.File;
import java.io.FileNotFoundException;

// Java net core imports
import java.net.URISyntaxException;
import java.net.URL;

// Java Sql core imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// `log4j` java imports
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class DatabaseConnect {
  private static final Logger _LOGGER = LogManager.getLogger();

  public static Connection connect() {
    Connection connection = null;

    try {
      Class.forName("org.sqlite.JDBC");
      _LOGGER.info("org.sqlite.JDBC found it!");

      URL resource = UserUtil.class.getResource("/secure/HireLog.db");
      if (resource == null)
        throw new FileNotFoundException("Can't find `/secure/HireLog.db`!");
      _LOGGER.info("`/secure/HireLog.db` found it!");

      connection = DriverManager.getConnection(
          "jdbc:sqlite:" + new File(resource.toURI()));
      _LOGGER.info("Connection to SQLite database established successfully!");

    } catch (ClassNotFoundException e) {
      _LOGGER.error("org.sqlite.JDBC Not found!");
      e.printStackTrace();
    } catch (URISyntaxException e) {
      _LOGGER.error("Cant't parse URL to URI");
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      _LOGGER.error("Can't find the absolute path of `HireLog.db`");
      e.printStackTrace();
    } catch (SQLException e) {
      _LOGGER.error("SQLITE coneection error!");
      e.printStackTrace();
    }
    return connection;
  }
}
