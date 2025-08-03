/**
 * DashboardController.java
 *
 * `Dashboard.fxml` Controller Class
 *
 * <p>
 * None
 * </p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 07/30/2025
 * @see RelatedClassOrDocumentation
 */

package com.mycompany.HireLog.controller;

import java.io.IOException;
import java.sql.Date;

import com.mycompany.HireLog.database.HireLogConnector;

import javafx.collections.ObservableList;
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
import javafx.stage.Stage;

import com.mycompany.HireLog.ui.LogTableUi;

public class DashboardController {

  private final int userId;

  public DashboardController(final int userId) {
    this.userId = userId;
  }

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

  @FXML
  void onAddLogBtnAction(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HireLogDialog.fxml"));
    loader.setControllerFactory(controllerClass -> {
      return new HireLogDialogController(userId);
    });
    Parent root = loader.load();
    Stage stage = new Stage();
    stage.setTitle("Add New Job");
    stage.setScene(new Scene(root));
    stage.show();
  }

  @FXML // This method is called by the FXMLLoader when initialization is complete
  void initialize() {
    assert addLogBtn != null : "fx:id=\"addLogBtn\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert editLogBtn != null : "fx:id=\"editLogBtn\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert logTable != null : "fx:id=\"logTable\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert removeLogBtn != null : "fx:id=\"removeLogBtn\" was not injected: check your FXML file 'Dashboard.fxml'.";
    assert searchField != null : "fx:id=\"searchField\" was not injected: check your FXML file 'Dashboard.fxml'.";

    // Setup cell value factories
    logIdCol.setCellValueFactory(new PropertyValueFactory<>("logId"));
    eventCol.setCellValueFactory(new PropertyValueFactory<>("event"));
    dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    commentsCol.setCellValueFactory(new PropertyValueFactory<>("comments"));
    attachmentsCol.setCellValueFactory(new PropertyValueFactory<>("attachments"));
    selectCol.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
    selectCol.setCellFactory(CheckBoxTableCell.forTableColumn(selectCol));

    // Then set the items
    ObservableList<LogTableUi> logs = HireLogConnector.fetchAll(userId);
    logTable.setItems(logs);
  }
}
