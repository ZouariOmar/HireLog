package com.mycompany.HireLog.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public final class UserUtil {
  private static final Logger LOGGER = LogManager.getLogger();

  /**
   * Brief summary of the method.
   *
   * <p>
   * More detailed description or notes.
   * </p>
   *
   * @param username {@code final String}
   * @param password {@code final String}
   *
   * @throws FileNotFoundException - when we can't find the database path
   * @throws URISyntaxException    - when we parsed URL as a URI reference.
   * 
   * @return {@code boolean}
   *
   * @see https://www.tutorialspoint.com/sqlite/sqlite_java.htm
   *
   *      <pre>
   * {@code
   * if (UserUtil.isUser("admin", "admin"))
   *  System.out.println("Passed!");
   * else
   *  System.err.println(":-)");
   * }</pre>
   */
  public final static boolean isUser(final String username, final String password)
      throws FileNotFoundException, URISyntaxException {
    Connection connection = null;
    PreparedStatement pstmt = null;
    ResultSet res = null;

    try {
      Class.forName("org.sqlite.JDBC");
      System.out.println("[INFO] org.sqlite.JDBC found it!");

      connection = DriverManager.getConnection(
          "jdbc:sqlite:" + new File(UserUtil.class.getResource("/secure/HireLog.db").toURI()).getAbsolutePath());
      LOGGER.info("Connection to SQLite database established successfully!");

      pstmt = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
      pstmt.setString(1, username);
      pstmt.setString(2, password);
      res = pstmt.executeQuery();
      System.out.println("[INFO] `isUser` query executed successfully!");

      return res.next();
    } catch (ClassNotFoundException e) {
      System.err.printf("[ERROR] %s\n", e.getMessage());
    } catch (SQLException e) {
      System.err.printf("[ERROR] %s\n", e.getMessage());
    } finally {
      try {
        if (res != null)
          res.close();
        if (pstmt != null)
          pstmt.close();
        if (connection != null)
          connection.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return false;
  }
}
