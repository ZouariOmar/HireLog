/**
 * UserUtil.java
 *
 * User CRUD actions manager
 *
 * <p>NONE</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 28/07/2025
 * @see https://github.com/ZouariOmar/HireLog/blob/main/project/src/main/java/com/mycompany/HireLog/util/UserUtil.java
 */

// `UserUtil` pkg name
package com.mycompany.HireLog.util;

// Java Sql core imports
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// `log4j` java imports
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public final class UserUtil {
  private static final Logger _LOGGER = LogManager.getLogger();

  /**
   * Verify the validity of the given `username` and `password`
   *
   * <p>
   * <h1>About <code>isUser</code> method</h1>
   * NONE
   * </br>
   * <h2>Exceptions</h2>
   * <ul>
   * <li>SQLException</li>
   * <li>Exceptions</li>
   * </ul>
   * </p>
   *
   * @param username {@code final String}
   * @param password {@code final String}
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
  public final static boolean isUser(final String username, final String password) {
    PreparedStatement pstmt = null;
    ResultSet res = null;

    try {
      pstmt = DatabaseConnect.connect().prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
      pstmt.setString(1, username);
      pstmt.setString(2, password);
      res = pstmt.executeQuery();
      _LOGGER.info("`isUser` query executed successfully!");

      return res.next();
    } catch (SQLException e) {
      _LOGGER.error("`isUser` query Failed!");
      e.printStackTrace();
    } finally {
      try {
        if (res != null)
          res.close();
        if (pstmt != null)
          pstmt.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return false;
  }
}
