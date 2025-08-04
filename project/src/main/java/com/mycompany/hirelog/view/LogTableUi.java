
package com.mycompany.hirelog.view;

import java.sql.Date;

import javafx.beans.property.*;

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
