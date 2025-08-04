/**
 * HireLogEvents.java
 *
 * HireLog `event_type`
 *
 * <p>None</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 04/08/2025
 * 
 * @see com.mycompany.hirelog.model#HireLog
 * 
 * <a href="https://github.com/ZouariOmar/HireLog/tree/main/project/src/main/java/com/mycompany/HireLog/flag/HireLogEvents.java">
 *  HireLogEvents.java
 * </a>
 */

// `HireLogEvents` pkg name
package com.mycompany.hirelog.flag;

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
