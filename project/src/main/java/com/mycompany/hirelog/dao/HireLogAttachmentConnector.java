/**
 * HireLogAttachmentConnector.java
 *
 * `hire_log_attachments` DAO
 *
 * <p>`hire_log_attachments` CRUD</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 05/08/2025
 * @see com.mycompany.hirelog.controller#HireLogFormController
 */

// `HireLogAttachmentConnector` pkg name
package com.mycompany.hirelog.dao;

// Log4j java imports
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Core java imports
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Custom java imports
import com.mycompany.hirelog.model.HireLogAttachment;

public class HireLogAttachmentConnector {

  private static final Logger _LOGGER = LogManager.getLogger();

  private static final String _CREATE_HIRE_LOG_ATTACHMENT_QUERY = "INSERT INTO hire_log_attachments (log_id, file_name, file_data) VALUES(?, ?, ?)";

  /**
   * @param hireLog
   */
  public static void create(final HireLogAttachment hireLogAttachment) {
    PreparedStatement pstmt = null;

    try {
      pstmt = DatabaseManager.connect().prepareStatement(_CREATE_HIRE_LOG_ATTACHMENT_QUERY);

      pstmt.setInt(1, hireLogAttachment.logId());
      pstmt.setString(2, hireLogAttachment.name());
      pstmt.setBytes(3, hireLogAttachment.data());

      pstmt.executeUpdate();
      _LOGGER.info("`HireLogAttachmentConnector#create` query executed successfully!");

    } catch (final SQLException e) {
      _LOGGER.error("`HireLogAttachmentConnector#create` query Failed!");
      e.printStackTrace();

    } finally {
      try {
        if (pstmt != null)
          pstmt.close();
      } catch (final SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
