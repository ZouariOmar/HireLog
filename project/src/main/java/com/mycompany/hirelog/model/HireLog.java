/**
 * HireLog.java
 *
 * `hire_logs` model descriper
 *
 * <p>None</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 31/07/2025
 * 
 * <a href="https://github.com/ZouariOmar/HireLog/tree/main/project/src/main/java/com.mycompany.hirelog/model/UserLog.java"/>
 *  HireLog.java
 * </a>
 */

// `HireLog` pkg name
package com.mycompany.hirelog.model;

public record HireLog(int userId, String eventType, java.sql.Date date, String comments) {
} // HireLog record
