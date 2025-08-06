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
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

// Log4j java imports
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Custom java imports
import com.mycompany.hirelog.dao.HireLogAttachmentConnector;
import com.mycompany.hirelog.dao.HireLogConnector;
import com.mycompany.hirelog.flag.HireLogEvents;
import com.mycompany.hirelog.model.HireLog;
import com.mycompany.hirelog.model.HireLogAttachment;
import com.mycompany.hirelog.util.FileUtils;
import com.mycompany.hirelog.view.ViewUtils;

// Javafx imports
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HireLogFormController {

  private static final Logger _LOGGER = LogManager.getLogger();

  private final int userId;

  private List<File> selectedFiles;

  @FXML // ResourceBundle that was given to the FXMLLoader
  private ResourceBundle resources;

  @FXML // URL location of the FXML file that was given to the FXMLLoader
  private URL location;

  @FXML // fx:id="attachments"
  private Button attachments; // Value injected by FXMLLoader

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

  @FXML // fx:id="status"
  private Label status; // Value injected by FXMLLoader

  /**
   * @param userId
   */
  public HireLogFormController(final int userId) {
    this.userId = userId;
    selectedFiles = null;
  }

  /**
   * Create new #HireLogFormController(final HireLog, final HireLogAttachment)
   * object
   *
   * <p>
   * Note: We use this constructor only for EDIT event
   * </p>
   *
   * @param jobInfo     {@code HireLog}
   * @param attachments {@code HireLogAttachment}
   *
   * @see DashboardController
   */
  public HireLogFormController(final HireLog jobInfo, final HireLogAttachment attachments) {
    this.userId = jobInfo.userId();
    // ...
  }

  @FXML
  void onSubmitAction(final ActionEvent e) {

    HireLogConnector // Insert `hire_logs` data (1 query)
        .create(new HireLog(
            userId,
            event.getValue().toLowerCase(),
            java.sql.Date.valueOf(date.getValue()),
            comments.getText()));

    final int lastHireLogId = HireLogConnector.getLastInsertedId(); // Get the inserted `hire_logs` `log_id`

    if (selectedFiles != null) {
      for (final File file : selectedFiles) { // Insert `hire_log_attachments` data (TODO: Set attachments number
                                              // limmiter)
        final byte[] fileData = FileUtils.fileToByteArray(file.getAbsolutePath());
        final String fileName = file.getName();
        HireLogAttachmentConnector.create(new HireLogAttachment(
            lastHireLogId,
            fileName,
            fileData));
      }
    }

    ViewUtils.showStatusMsg(status, "Job track added successfully!", Color.GREEN);
    _LOGGER.info("`com.mycompany.hirelog.controller#onSubmitAction()` passed!");
  }

  @FXML
  void onAttachmentAction(final ActionEvent event) {
    final FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select file(s) to upload(s)");
    fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))); // Open `user/home/` directory
    fileChooser.getExtensionFilters().addAll( // Add file(s) filter(s)
        new FileChooser.ExtensionFilter("All Files", "*.*"));
    selectedFiles = fileChooser.showOpenMultipleDialog(new Stage());
  }

  @FXML // This method is called by the FXMLLoader when initialization is complete
  void initialize() {
    assert attachments != null : "fx:id=\"Attachments\" was not injected: check your FXML file 'HireLogDialog.fxml'.";
    assert cancel != null : "fx:id=\"cancel\" was not injected: check your FXML file 'HireLogDialog.fxml'.";
    assert comments != null : "fx:id=\"comments\" was not injected: check your FXML file 'HireLogDialog.fxml'.";
    assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'HireLogDialog.fxml'.";
    assert event != null : "fx:id=\"event\" was not injected: check your FXML file 'HireLogDialog.fxml'.";
    assert header != null : "fx:id=\"header\" was not injected: check your FXML file 'HireLogDialog.fxml'.";
    assert submit != null : "fx:id=\"submit\" was not injected: check your FXML file 'HireLogDialog.fxml'.";
    assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'HireLogForm.fxml'.";

    // Set `event` items
    event.setItems(FXCollections.observableArrayList(
        HireLogEvents.APPLIED.getEventName(),
        HireLogEvents.INTERVIEWED.getEventName(),
        HireLogEvents.HIRED.getEventName(),
        HireLogEvents.REJECTED.getEventName(),
        HireLogEvents.OTHER.getEventName()));
    event.setValue(HireLogEvents.INTERVIEWED.getEventName()); // Set `Interviewed` as default option

    date.setValue(LocalDate.now());
  }
} // HireLogDialogController class
