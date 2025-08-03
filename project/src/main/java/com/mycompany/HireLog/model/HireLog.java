/**
 * HireLog.java
 *
 * `hire_log` model
 *
 * <p>
 * None
 * </p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 31/07/2025
 * @see <a href=
 *      "https://github.com/ZouariOmar/HireLog/tree/main/project/src/main/java/com/mycompany/HireLog/model/UserLog.java"/>HireLog.java
 *      </a>
 */

// HireLog pkg name
package com.mycompany.HireLog.model;

public record HireLog(int userId, String event, java.sql.Date date, String comments, Integer logId) {
  public HireLog(int userId, String event, java.sql.Date date, String comments) {
    this(userId, event, date, comments, null);
  }
}
// HireLog record
