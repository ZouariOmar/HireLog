/**
 * LogTableUi.java
 *
 * Dashboard table descriper
 *
 * <p>None</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 08/06/2025
 * @see com.mycompany.hirelog.controller#DashboardController
 */

// `LogTableUi` pkg name
package com.mycompany.hirelog.view;

// Core Java imports
import java.sql.Date;

// JavaFx imports
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class LogTableUi {
  private final SimpleBooleanProperty selected;
  private final SimpleStringProperty event;
  private final SimpleObjectProperty<Date> date;
  private final SimpleStringProperty comments;
  private final SimpleIntegerProperty logId;
  private final SimpleStringProperty attachments;

  public LogTableUi(int logId, String event, Date date, String comments) {
    this.selected = new SimpleBooleanProperty(false);
    this.event = new SimpleStringProperty(event);
    this.date = new SimpleObjectProperty<>(date);
    this.comments = new SimpleStringProperty(comments);
    this.logId = new SimpleIntegerProperty(logId);
    this.attachments = new SimpleStringProperty("");
  }

  public boolean isSelected() {
    return selected.get();
  }

  public SimpleBooleanProperty selectedProperty() {
    return selected;
  }

  public String getEvent() {
    return event.get();
  }

  public SimpleStringProperty eventProperty() {
    return event;
  }

  public Date getDate() {
    return date.get();
  }

  public SimpleObjectProperty<Date> dateProperty() {
    return date;
  }

  public String getComments() {
    return comments.get();
  }

  public SimpleStringProperty commentsProperty() {
    return comments;
  }

  public int getLogId() {
    return logId.get();
  }

  public SimpleIntegerProperty logIdProperty() {
    return logId;
  }

  public String getAttachments() {
    return attachments.get();
  }

  public SimpleStringProperty attachmentsProperty() {
    return attachments;
  }
}
