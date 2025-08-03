/**
 * HireLogHelper.java
 *
 * `hire_log` helper
 *
 * <p>`hire_log` CRUD</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 03/08/2025
 * @see None
 */

// HireLogHelper pkg name
package com.mycompany.HireLog.database;

// Java Sql core imports
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// `log4j` java imports
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.mycompany.HireLog.model.HireLog;
import com.mycompany.HireLog.ui.LogTableUi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HireLogConnector {
  private static final Logger _LOGGER = LogManager.getLogger();

  public static void create(final HireLog hireLog) {
    PreparedStatement pstmt = null;

    try {
      pstmt = Database.connect()
          .prepareStatement(
              "INSERT INTO hire_logs (user_id, event_type, event_timestamp, comments) VALUES(?, ?, ?, ?)");
      pstmt.setInt(1, hireLog.userId());
      pstmt.setString(2, hireLog.event());
      pstmt.setDate(3, hireLog.date());
      pstmt.setString(4, hireLog.comments());
      pstmt.executeUpdate();
      _LOGGER.info("`HireLogHelper#create` query executed successfully!");
    } catch (SQLException e) {
      _LOGGER.error("`HireLogHelper#create` query Failed!");
      e.printStackTrace();
    } finally {
      try {
        if (pstmt != null)
          pstmt.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static final ObservableList<LogTableUi> fetchAll(final int userId) {
    ObservableList<LogTableUi> hireLogs = FXCollections.observableArrayList();
    PreparedStatement pstmt = null;
    ResultSet res = null;

    try {
      pstmt = Database.connect()
          .prepareStatement(
              "SELECT * FROM hire_logs WHERE user_id = ?");
      pstmt.setInt(1, userId);
      res = pstmt.executeQuery();
      _LOGGER.info("`HireLogHelper#fetch` query executed successfully!");

      while (res.next()) {
        LogTableUi hireLog = new LogTableUi(
            res.getInt("log_id"),
            res.getString("event_type"),
            res.getDate("event_timestamp"),
            res.getString("comments"));
        hireLogs.add(hireLog);
      }

    } catch (SQLException e) {
      _LOGGER.error("`HireLogHelper#create` query Failed!");
      e.printStackTrace();
    } finally {
      try {
        if (pstmt != null)
          pstmt.close();
        if (res != null)
          res.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return hireLogs;
  }
}
