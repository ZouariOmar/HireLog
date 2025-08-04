/**
 * DatabaseManager.java
 *
 * Database connection manager
 *
 * <p>None</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 28/07/2025
 *
 * <a href="https://github.com/ZouariOmar/HireLog/blob/main/project/src/main/java/com.mycompany.hirelog/util/DatabaseManager.java">
 *  DatabaseManager.java
 * </a>
*/

// `DatabaseManager` pkg name
package com.mycompany.hirelog.dao;

// Core.sql java imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Log4j java imports
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseManager {

  private static final Logger _LOGGER = LogManager.getLogger();

  private static final String _JDBC = "jdbc:sqlite:/home/zouari_omar/Documents/Daily/Projects/HireLog/project/database/hirelog.db";

  private static final String _SQLITE_CLASS_NAME = "org.sqlite.JDBC";

  public static Connection connect() {
    Connection connection = null;
    try {
      Class.forName(_SQLITE_CLASS_NAME);
      _LOGGER.info("{} found it!", _SQLITE_CLASS_NAME);
      connection = DriverManager.getConnection(_JDBC);
      _LOGGER.info("Connection to SQLite database established successfully!");
    } catch (ClassNotFoundException e) {
      _LOGGER.error("org.sqlite.JDBC Not found!");
      e.printStackTrace();
    } catch (SQLException e) {
      _LOGGER.error("SQLITE coneection error!");
      e.printStackTrace();
    }
    return connection;
  }
} // Database class
