/**
 * Database.java
 *
 * Database connection manager
 *
 * <p>NONE</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 28/07/2025
 * @see https://github.com/ZouariOmar/HireLog/blob/main/project/src/main/java/com/mycompany/HireLog/util/Database.java
 */

// `Database` pkg name
package com.mycompany.HireLog.database;

// Java Sql core imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// `log4j` java imports
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Database {
  private static final Logger _LOGGER = LogManager.getLogger();
  private static final String _dbAbsolutePath = "/home/zouari_omar/Documents/Daily/Projects/HireLog/project/database/HireLog.db";

  public static Connection connect() {
    Connection connection = null;
    try {
      Class.forName("org.sqlite.JDBC");
      _LOGGER.info("org.sqlite.JDBC found it!");
      connection = DriverManager.getConnection("jdbc:sqlite:" + _dbAbsolutePath);
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
