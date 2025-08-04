/**
 * HireLogFormController.java
 *
 * 'HireLogDialog.fxml' controller class
 *
 * <p>None</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 04/08/2025
 *
 * <a href="https://github.com/ZouariOmar/HireLog/tree/main/project/src/main/java/com/mycompany/HireLog/controller/HireLogFormController.java">
 *  HireLogFormController.java
 * </a>
 */

// `HireLogDialogController` pkg name
package com.mycompany.hirelog.controller;

// Core java imports
import java.net.URL;
import java.util.ResourceBundle;

// Custom java imports
import com.mycompany.hirelog.dao.HireLogConnector;
import com.mycompany.hirelog.flag.HireLogEvents;
import com.mycompany.hirelog.model.HireLog;

// Javafx imports
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class HireLogFormController {

  private final int userId;

  @FXML // ResourceBundle that was given to the FXMLLoader
  private ResourceBundle resources;

  @FXML // URL location of the FXML file that was given to the FXMLLoader
  private URL location;

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

  public HireLogFormController(final int userId) {
    this.userId = userId;
  }

  @FXML
  void onSubmitAction(final ActionEvent event) {
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
