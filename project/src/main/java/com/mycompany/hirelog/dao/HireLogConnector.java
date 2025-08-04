/**
 * HireLogConnector.java
 *
 * `hire_logs` DAO
 *
 * <p>`hire_logs` CRUD</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 03/08/2025
 *
 * <a href="https://github.com/ZouariOmar/HireLog/tree/main/project/src/main/java/com/mycompany/HireLog/dao/HireLogConnector.java">
 *  HireLogConnector.java
 * </a>
 */

// `HireLogConnector` pkg name
package com.mycompany.hirelog.dao;

// Java Sql core imports
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Log4j java imports
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Custom java imports²
import com.mycompany.hirelog.model.HireLog;
import com.mycompany.hirelog.view.LogTableUi;

// JavaFx imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HireLogConnector {

  private static final Logger _LOGGER = LogManager.getLogger();

  private static final String _CTREATE_QUERY = "INSERT INTO hire_logs (user_id, event_type, event_timestamp, comments) VALUES(?, ?, ?, ?)";

  private static final String _FETCH_ALL_QUERY = "SELECT * FROM hire_logs WHERE user_id = ?";

  public static void create(final HireLog hireLog) {
    PreparedStatement pstmt = null;

    try {
      pstmt = DatabaseManager.connect().prepareStatement(_CTREATE_QUERY);

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
      pstmt = DatabaseManager.connect().prepareStatement(_FETCH_ALL_QUERY);

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
