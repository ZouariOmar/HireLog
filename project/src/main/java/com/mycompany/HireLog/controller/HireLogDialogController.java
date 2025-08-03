/**
 * Clas.java
 *
 * 'HireLogDialog.fxml' Controller Class
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

// HireLogDialogController pkg name
package com.mycompany.HireLog.controller;

// Javafx imports
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

// Java custom imports
import com.mycompany.HireLog.status.HireLogEvents;
import com.mycompany.HireLog.database.HireLogConnector;
import com.mycompany.HireLog.model.HireLog;

public class HireLogDialogController {

  private final int userId;

  public HireLogDialogController(int userId) {
    this.userId = userId;
  }

  @FXML // fx:id="Attachments"
  private Button Attachments; // Value injected by FXMLLoader

  @FXML // fx:id="cancel"
  private Button cancel; // Value injected by FXMLLoader

  @FXML // fx:id="comments"
  private TextArea comments; // Value injected by FXMLLoader

  @FXML // fx:id="date"
  private DatePicker date; // Value injected by FXMLLoader

  @FXML // fx:id="event"
  private ChoiceBox<String> event; // Value injected by FXMLLoader

  @FXML // fx:id="header"
  private Label header; // Value injected by FXMLLoader

  @FXML // fx:id="submit"
  private Button submit; // Value injected by FXMLLoader

  @FXML
  void onSubmitAction(ActionEvent event) {
    HireLogConnector
        .create(new HireLog(
            userId,
            this.event.getValue().toLowerCase(),
            java.sql.Date.valueOf(date.getValue()),
            comments.getText()));
  }

  @FXML // This method is called by the FXMLLoader when initialization is complete
  void initialize() {
    assert Attachments != null : "fx:id=\"Attachments\" was not injected: check your FXML file 'HireLogDialog.fxml'.";
    assert cancel != null : "fx:id=\"cancel\" was not injected: check your FXML file 'HireLogDialog.fxml'.";
    assert comments != null : "fx:id=\"comments\" was not injected: check your FXML file 'HireLogDialog.fxml'.";
    assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'HireLogDialog.fxml'.";
    assert event != null : "fx:id=\"event\" was not injected: check your FXML file 'HireLogDialog.fxml'.";
    assert header != null : "fx:id=\"header\" was not injected: check your FXML file 'HireLogDialog.fxml'.";
    assert submit != null : "fx:id=\"submit\" was not injected: check your FXML file 'HireLogDialog.fxml'.";

    // Set `event` items
    event.setItems(FXCollections.observableArrayList(
        HireLogEvents.APPLIED.getEventName(),
        HireLogEvents.INTERVIEWED.getEventName(),
        HireLogEvents.HIRED.getEventName(),
        HireLogEvents.REJECTED.getEventName(),
        HireLogEvents.OTHER.getEventName()));
  }
} // HireLogDialogController class
