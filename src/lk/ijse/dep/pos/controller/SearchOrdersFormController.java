package lk.ijse.dep.pos.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.pos.util.OrderTM;

import java.io.IOException;
import java.net.URL;

public class SearchOrdersFormController {

    public TextField txtSearch;
    public TableView<OrderTM> tblOrders;
    public AnchorPane root;

    public void initialize() {

        tblOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        tblOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblOrders.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));

        ObservableList<OrderTM> olOrders = tblOrders.getItems();

        ObservableList<OrderTM> olAllOrders =
                FXCollections.observableArrayList(olOrders);

        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                String searchText = txtSearch.getText();

                ObservableList<OrderTM> tempOrders = FXCollections.observableArrayList();

                for (OrderTM order : olAllOrders) {
                    if (order.getOrderId().contains(searchText) ||
                            order.getOrderDate().contains(searchText) ||
                            order.getCustomerId().contains(searchText) ||
                            order.getCustomerName().contains(searchText)) {
                        tempOrders.add(order);
                    }
                }

                tblOrders.setItems(tempOrders);
            }
        });

    }

    @FXML
    private void navigateToHome(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/dep/pos/view/MainForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }
}
