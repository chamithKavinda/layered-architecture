package com.example.layeredarchitecture.controller;

import com.example.layeredarchitecture.bo.custom.CustomerBO;
import com.example.layeredarchitecture.bo.custom.impl.CustomerBOImpl;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.view.tdm.CustomerTM;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class ManageCustomersFormController {
    public AnchorPane root;
    public TextField txtCustomerName;
    public TextField txtCustomerId;
    public JFXButton btnDelete;
    public JFXButton btnSave;
    public TextField txtCustomerAddress;
    public TableView<CustomerTM> tblCustomers;
    public JFXButton btnAddNewCustomer;

    CustomerBO customerBO=new CustomerBOImpl();

    public void initialize() {
        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        initUI();

        tblCustomers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtCustomerId.setText(newValue.getId());
                txtCustomerName.setText(newValue.getName());
                txtCustomerAddress.setText(newValue.getAddress());

                txtCustomerId.setDisable(false);
                txtCustomerName.setDisable(false);
                txtCustomerAddress.setDisable(false);
            }
        });

        txtCustomerAddress.setOnAction(event -> btnSave.fire());
        loadAllCustomers();
    }

    private void loadAllCustomers() {
        tblCustomers.getItems().clear();

        try {
            ArrayList<CustomerDTO> allCustomer = customerBO.getAllCustomer();
            for (CustomerDTO dto:allCustomer) {
                tblCustomers.getItems().add(
                        new CustomerTM(
                                dto.getId(),
                                dto.getName(),
                                dto.getAddress()));

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void initUI() {
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerId.setDisable(true);
        txtCustomerName.setDisable(true);
        txtCustomerAddress.setDisable(true);
        txtCustomerId.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    private void navigateToHome(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/com/example/layeredarchitecture/main-form.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txtCustomerId.setDisable(false);
        txtCustomerName.setDisable(false);
        txtCustomerAddress.setDisable(false);
        txtCustomerId.clear();
        txtCustomerId.setText(generateNewId());
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblCustomers.getSelectionModel().clearSelection();
    }


    public void btnSave_OnAction(ActionEvent actionEvent) {
        String id = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();

        if (!name.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtCustomerName.requestFocus();
            return;
        } else if (!address.matches(".{3,}")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtCustomerAddress.requestFocus();
            return;
        }

        if (btnSave.getText().equalsIgnoreCase("save")) {

            try {
                if (existCustomer(id)) {
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }
                boolean isSaved = customerBO.saveCustomer(new CustomerDTO(id, name, address));

                if (isSaved) {
                    tblCustomers.getItems().add(new CustomerTM(id, name, address));
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } else {

            try {
                if (!existCustomer(id)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
                }
                CustomerDTO dto = new CustomerDTO(id,name,address);
                customerBO.updateCustomer(dto);

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + id + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            CustomerTM selectedCustomer = tblCustomers.getSelectionModel().getSelectedItem();
            selectedCustomer.setName(name);
            selectedCustomer.setAddress(address);
            tblCustomers.refresh();
        }

        btnAddNewCustomer.fire();
    }


    boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerBO.existCustomer(id);

    }


    public void btnDelete_OnAction(ActionEvent actionEvent) {

        String id = tblCustomers.getSelectionModel().getSelectedItem().getId();
        try {
            if (!existCustomer(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
            }
            customerBO.deleteCustomer(id);
            tblCustomers.getItems().remove(tblCustomers.getSelectionModel().getSelectedItem());
            tblCustomers.getSelectionModel().clearSelection();
            initUI();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the customer " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String generateNewId() {
        try {
            return customerBO.generateCustomerID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if (tblCustomers.getItems().isEmpty()) {
            return "C00-001";
        } else {
            String id = getLastCustomerId();
            int newCustomerId = Integer.parseInt(id.replace("C", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        }

    }

    private String getLastCustomerId() {
        List<CustomerTM> tempCustomersList = new ArrayList<>(tblCustomers.getItems());
        Collections.sort(tempCustomersList);
        return tempCustomersList.get(tempCustomersList.size() - 1).getId();
    }

}
