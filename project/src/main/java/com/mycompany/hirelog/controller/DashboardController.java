/**
 * DashboardController.java
 *
 * `Dashboard.fxml` controller class
 *
 * <p>None</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 07/30/2025
 *
 *  <a href="https://github.com/ZouariOmar/HireLog/tree/main/project/src/main/java/com/mycompany/HireLog/controller/DashboardController.java">
 *  DashboardController.java
 *  </a>
 */

// `DashboardController` pkg name
package com.mycompany.hirelog.controller;

// Core java imports
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

// Log4j java imports
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Custom java imports
import com.mycompany.hirelog.dao.HireLogConnector;
import com.mycompany.hirelog.view.LogTableUi;
import com.mycompany.hirelog.view.ViewUtils;

// JavaFx imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DashboardController {

  private static final Logger _LOGGER = LogManager.getLogger();

  private final int userId; // User id | Must be come from `SignUpController`

  @FXML // ResourceBundle that was given to the FXMLLoader
  private ResourceBundle resources;

  @FXML // URL location of the FXML file that was given to the FXMLLoader
  private URL location;

  @FXML // fx:id="addLogBtn"
  private Button addLogBtn; // Value injected by FXMLLoader

  @FXML // fx:id="editLogBtn"
  private Button editLogBtn; // Value injected by FXMLLoader

  @FXML // fx:id="logTable"
  private TableView<LogTableUi> logTable; // Value injected by FXMLLoader

  @FXML // fx:id="attachmentsCol"
  private TableColumn<LogTableUi, String> attachmentsCol; // Value injected by FXMLLoader

  @FXML // fx:id="commentsCol"
  private TableColumn<LogTableUi, String> commentsCol; // Value injected by FXMLLoader

  @FXML // fx:id="dateCol"
  private TableColumn<LogTableUi, Date> dateCol; // Value injected by FXMLLoader

  @FXML // fx:id="eventCol"
  private TableColumn<LogTableUi, String> eventCol; // Value injected by FXMLLoader

  @FXML // fx:id="logIdCol"
  private TableColumn<LogTableUi, Integer> logIdCol; // Value injected by FXMLLoader

  @FXML // fx:id="selectCol"
  private TableColumn<LogTableUi, Boolean> selectCol; // Value injected by FXMLLoader

  @FXML // fx:id="removeLogBtn"
  private Button removeLogBtn; // Value injected by FXMLLoader

  @FXML // fx:id="searchField"
  private TextField searchField; // Value injected by FXMLLoader

  @FXML // fx:id="refrechImg"
  private ImageView refrechImg; // Value injected by FXMLLoader

  @FXML // fx:id="removeLogBtn"
  private Button refrechBtn; // Value injected by FXMLLoader

  public DashboardController(final int userId) {
    this.userId = userId;
  }

  @FXML
  void onAddLogBtnAction(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HireLogForm.fxml"));
    loader.setControllerFactory(_ -> {
      return new HireLogFormController(userId);
    });
    Parent root = loader.load();
    Stage stage = new Stage();
    stage.setTitle("Add New Job");
    stage.setScene(new Scene(root));
    stage.show();
  }

  @FXML
  void onRefrechBtnAction(ActionEvent event) {
    ViewUtils.disableButton(refrechBtn, 1);
    ViewUtils.playGifAnimation(refrechImg, "/assets/icons8-refresh.gif", 1);
    logTable.setItems(HireLogConnector.fetchAll(userId)); // Fetch all items agin :(
  }

  @FXML // This method is called by the FXMLLoader when initialization is complete
  void initialize() {
    assert addLogBtn != null : "fx:id=\"addLogBtn\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert attachmentsCol != null : "fx:id=\"attachmentsCol\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert commentsCol != null : "fx:id=\"commentsCol\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert dateCol != null : "fx:id=\"dateCol\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert editLogBtn != null : "fx:id=\"editLogBtn\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert eventCol != null : "fx:id=\"eventCol\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert logIdCol != null : "fx:id=\"logIdCol\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert logTable != null : "fx:id=\"logTable\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert removeLogBtn != null : "fx:id=\"removeLogBtn\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert searchField != null : "fx:id=\"searchField\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert selectCol != null : "fx:id=\"selectCol\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert refrechImg != null : "fx:id=\"refrechImg\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert refrechBtn != null : "fx:id=\"refrechBtn\" was not injected: check your FXML file 'Dashboard.fxml'.";

    // Setup cell value factories
    logIdCol.setCellValueFactory(new PropertyValueFactory<>("logId"));
    eventCol.setCellValueFactory(new PropertyValueFactory<>("event"));
    dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    commentsCol.setCellValueFactory(new PropertyValueFactory<>("comments"));
    attachmentsCol.setCellValueFactory(new PropertyValueFactory<>("attachments"));
    selectCol.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
    selectCol.setCellFactory(CheckBoxTableCell.forTableColumn(selectCol));

    // Set the items
    logTable.setItems(HireLogConnector.fetchAll(userId));

    _LOGGER.info("{} loaded sucssefully!", location);
  }
}
