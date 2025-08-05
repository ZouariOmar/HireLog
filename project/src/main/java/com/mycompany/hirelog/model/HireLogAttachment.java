/**
 * HireLogAttachment.java
 *
 * `hire_log_attachments` model describer
 *
 * <p>None</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 05/08/2025
 * @see com.mycompany.hirelog.model#HireLog
 */

// `HireLogAttachment` pkg name
package com.mycompany.hirelog.model;

public record HireLogAttachment(int logId, String name, byte[] data) {
} // HireLogAttachment record
