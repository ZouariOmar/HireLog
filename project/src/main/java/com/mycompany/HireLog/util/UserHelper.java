/**
 * UserHelper.java
 *
 * User CRUD actions manager
 *
 * <p>Note: This class only manage user-database part</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 28/07/2025
 * @see https://github.com/ZouariOmar/HireLog/blob/main/project/src/main/java/com/mycompany/HireLog/util/UserHelper.java
 */

// `UserHelper` pkg name
package com.mycompany.HireLog.util;

// Java Sql core imports
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

// `log4j` java imports
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javafx.scene.chart.PieChart.Data;

import org.apache.logging.log4j.LogManager;

public final class UserHelper {
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
   * if (UserHelper.isUser("admin", "admin"))
   *  System.out.println("Passed!");
   * else
   *  System.err.println(":-)");
   * }</pre>
   */
  public final static boolean isUser(final String username, final String password) {
    PreparedStatement pstmt = null;
    ResultSet res = null;

    // if (BCrypt.checkpw(candidate_password, stored_hash))
    // System.out.println("It matches");
    // else
    // System.out.println("It does not match");

    try {
      pstmt = Database.connect().prepareStatement("SELECT * FROM users WHERE username = ?");
      pstmt.setString(1, username);
      res = pstmt.executeQuery();
      _LOGGER.info("`isUser` query executed successfully!");
      return res.next() ? BCrypt.checkpw(password, res.getString("password")) : false;
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

  public static final boolean isExistedEmail(String email) {
    PreparedStatement pstmt = null;

    try {
      pstmt = Database.connect().prepareStatement("SELECT email from users WHERE email = ?");
      pstmt.setString(1, email);
      ResultSet res = pstmt.executeQuery();
      _LOGGER.info("`createUser` query executed successfully!");
      return res.next();
    } catch (SQLException e) {
      _LOGGER.error("`createUser` query Failed!");
      e.printStackTrace();
    } finally {
      try {
        if (pstmt != null)
          pstmt.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  public static final String createUser(String prename, String name, String email, String password) {
    PreparedStatement pstmt = null;
    String username = prename + name + Integer.toString(getLastAvailbleId());

    try {
      pstmt = Database.connect().prepareStatement("INSERT INTO users (username, password, email) VALUES(?, ?, ?)");
      pstmt.setString(1, username); // If `username` null, it will be catch it by `SQLException`
      pstmt.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
      pstmt.setString(3, email);
      pstmt.executeUpdate(); // Use executeUpdate() — not executeQuery() — for INSERT, UPDATE, or DELETE.
      _LOGGER.info("`createUser` query executed successfully!");
      return username;
    } catch (SQLException e) {
      _LOGGER.error("`createUser` query Failed!");
      e.printStackTrace();
    } finally {
      try {
        if (pstmt != null)
          pstmt.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  private static final int getLastAvailbleId() {
    try (ResultSet rs = Database.connect().prepareStatement("SELECT IFNULL(MAX(user_id), 0) + 1 AS next_id FROM users")
        .executeQuery()) {
      if (rs.next())
        return rs.getInt("next_id");
    } catch (SQLException e) {
      _LOGGER.error("`getLastAvailbleId` query Failed!");
      e.printStackTrace();
    }
    return -1;
  }

  /**
   * Verify the validity of the passed `email`
   *
   * <p>
   * None
   * </p>
   *
   * @param email {@code String}
   * @return {@code boolean}
   *
   * @see isPassword
   * 
   *      <pre>
   * {@code
   * if (UserHelper.isEmail("test@test.test")
   *  // Passed
   * else
   *  // :)
   * }</pre>
   */
  public static final boolean isEmail(String email) {
    return Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
        .matcher(email).matches();
  }

  /**
   * Verify the validity of the passed `password`
   *
   * <p>
   * ^ # start-of-string
   * (?=.*[0-9]) # a digit must occur at least once
   * (?=.*[a-z]) # a lower case letter must occur at least once
   * (?=.*[A-Z]) # an upper case letter must occur at least once
   * (?=.*[@#$%^&+=]) # a special character must occur at least once
   * (?=\S+$) # no whitespace allowed in the entire string
   * .{8,} # anything, at least eight places though
   * $ # end-of-string
   * </p>
   *
   * @param password {@code String}
   * @return {@code boolean}
   *
   * @see isEmail
   * 
   *      <pre>
   * {@code
   * if (UserHelper.isPassword("test")
   *  // Passed
   * else
   *  // :)
   * }</pre>
   */
  public static final boolean isPassword(String password) {
    return Pattern
        .compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", Pattern.CASE_INSENSITIVE)
        .matcher(password).matches();
  }
} // UserHelper class
