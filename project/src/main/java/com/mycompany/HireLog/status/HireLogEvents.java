/**
 * ClassName.java
 *
 * Short description of this class or file.
 *
 * <p>Detailed explanation of the class, its responsibilities, and usage.</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since YYYY-MM-DD
 * @deprecated Reason and alternative
 * @see RelatedClassOrDocumentation
 * @serial Serialization details (if applicable)
 */

package com.mycompany.HireLog.status;

public enum HireLogEvents {
  APPLIED("Applied"),
  INTERVIEWED("Interviewed"),
  HIRED("Hired"),
  REJECTED("Rejected"),
  OTHER("Other");

  private final String eventName;

  private HireLogEvents(String eventName) {
    this.eventName = eventName;
  }

  public String getEventName() {
    return eventName;
  }
}
