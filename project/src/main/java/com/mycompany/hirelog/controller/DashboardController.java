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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

// Log4j java imports
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Custom java imports
import com.mycompany.hirelog.dao.HireLogConnector;
import com.mycompany.hirelog.view.LogTableUi;
import com.mycompany.hirelog.view.ViewUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

  @FXML // fx:id="commentsCol"
  private TableColumn<LogTableUi, String> commentsCol; // Value injected by FXMLLoader

  @FXML // fx:id="dateCol"
  private TableColumn<LogTableUi, String> dateCol; // Value injected by FXMLLoader

  @FXML // fx:id="deleteBtn"
  private Button deleteBtn; // Value injected by FXMLLoader

  @FXML // fx:id="deleteImg"
  private ImageView deleteImg; // Value injected by FXMLLoader

  @FXML // fx:id="editBtn"
  private Button editBtn; // Value injected by FXMLLoader

  @FXML // fx:id="eventCol"
  private TableColumn<LogTableUi, String> eventCol; // Value injected by FXMLLoader

  @FXML // fx:id="jobTitleCol"
  private TableColumn<LogTableUi, String> jobTitleCol; // Value injected by FXMLLoader

  @FXML // fx:id="logTable"
  private TableView<LogTableUi> logTable; // Value injected by FXMLLoader

  @FXML // fx:id="refrechBtn"
  private Button refrechBtn; // Value injected by FXMLLoader

  @FXML // fx:id="refrechImg"
  private ImageView refrechImg; // Value injected by FXMLLoader

  @FXML // fx:id="searchField"
  private TextField searchField; // Value injected by FXMLLoader

  @FXML // fx:id="selectCol"
  private TableColumn<LogTableUi, Boolean> selectCol; // Value injected by FXMLLoader

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
  void onRefrechBtnAction(ActionEvent e) {
    ViewUtils.disableButton(refrechBtn, 1);
    ViewUtils.playGifAnimation(refrechImg, "/assets/icons8-refresh.gif", 1);

    // Fetch user data
    ObservableList<LogTableUi> items = HireLogConnector.fetchAll(userId);

    // Set Delete/Edit buttons event listeners for the new items
    ViewUtils.updateButtonStates(items, editBtn, deleteBtn);

    // Set the items
    logTable.setItems(items);

    // Disable edit and delete buttons
    deleteBtn.setDisable(true);
    editBtn.setDisable(true);
  }

  @FXML
  void onDeleteBtnAction(ActionEvent e) {
    // Store the `log_id`'s that are selected
    List<Integer> identifiers = new ArrayList<>();
    ObservableList<LogTableUi> selectedItems = FXCollections.observableArrayList();
    for (final LogTableUi item : logTable.getItems()) {
      if (item.isSelected()) {
        identifiers.add(item.getLogId());
        selectedItems.add(item);
      }
    }

    // Lance `DELETE` Query
    HireLogConnector.delete(userId, identifiers);

    // Remove the selected items from the table
    logTable.getItems().removeAll(selectedItems);

    // Disable edit and delete buttons
    deleteBtn.setDisable(true);
    editBtn.setDisable(true);

    // Play delete anaimation
    ViewUtils.playGifAnimation(deleteImg, "/assets/icons8-delete.gif", 1);
  }

  @FXML
  void onEditBtnAction(ActionEvent e) throws IOException {
    for (LogTableUi item : logTable.getItems()) {
      if (item.isSelected()) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HireLogForm.fxml"));
        loader.setControllerFactory(_ -> {
          return new HireLogFormController(userId, item);
        });
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Add New Job");
        stage.setScene(new Scene(root));
        stage.show();
        break;
      }
    }
  }

  @FXML
  void onAboutMenuItemAction(ActionEvent e) {

  }

  @FXML // This method is called by the FXMLLoader when initialization is complete
  void initialize() {
    assert addLogBtn != null : "fx:id=\"addLogBtn\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert commentsCol != null : "fx:id=\"commentsCol\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert dateCol != null : "fx:id=\"dateCol\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert deleteBtn != null : "fx:id=\"deleteBtn\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert deleteImg != null : "fx:id=\"deleteImg\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert editBtn != null : "fx:id=\"editBtn\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert eventCol != null : "fx:id=\"eventCol\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert jobTitleCol != null : "fx:id=\"jobTitleCol\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert logTable != null : "fx:id=\"logTable\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert refrechBtn != null : "fx:id=\"refrechBtn\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert refrechImg != null : "fx:id=\"refrechImg\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert searchField != null : "fx:id=\"searchField\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert selectCol != null : "fx:id=\"selectCol\" was not injected: check your FXML file 'Dashboard.fxml'.";

    // Setup cell value factories
    selectCol.setCellValueFactory(new PropertyValueFactory<>("selected"));
    selectCol.setCellFactory(CheckBoxTableCell.forTableColumn(selectCol));
    jobTitleCol.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
    eventCol.setCellValueFactory(new PropertyValueFactory<>("event"));
    dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    commentsCol.setCellValueFactory(new PropertyValueFactory<>("comments"));

    // Fetch user data
    ObservableList<LogTableUi> items = HireLogConnector.fetchAll(userId);

    // Set Delete/Edit buttons event listeners
    ViewUtils.updateButtonStates(items, editBtn, deleteBtn);

    // Set the items
    logTable.setItems(items);

    _LOGGER.info("{} loaded sucssefully!", location);
  }
}
