package lk.ijse.dep.pos.controller;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dep.pos.db.DBConnection;

import java.io.*;

public class MainFormController {

    @FXML
    private AnchorPane root;
    @FXML
    private ImageView imgCustomer;
    @FXML
    private ImageView imgItem;
    @FXML
    private ImageView imgOrder;
    @FXML
    private ImageView imgViewOrders;
    @FXML
    private Label lblMenu;
    @FXML
    private Label lblDescription;

//    public void initialize(URL url, ResourceBundle rb) {
//        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), root);
//        fadeIn.setFromValue(0.0);
//        fadeIn.setToValue(1.0);
//        fadeIn.play();
//    }

    @FXML
    private void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblMenu.setText("Welcome");
            lblDescription.setText("Please select one of above main operations to proceed");
        }
    }

    @FXML
    private void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            switch (icon.getId()) {
                case "imgCustomer":
                    lblMenu.setText("Manage Customers");
                    lblDescription.setText("Click to add, edit, delete, search or lk.ijse.dep.pos.view customers");
                    break;
                case "imgItem":
                    lblMenu.setText("Manage Items");
                    lblDescription.setText("Click to add, edit, delete, search or lk.ijse.dep.pos.view items");
                    break;
                case "imgOrder":
                    lblMenu.setText("Place Orders");
                    lblDescription.setText("Click here if you want to place a new order");
                    break;
                case "imgViewOrders":
                    lblMenu.setText("Search Orders");
                    lblDescription.setText("Click if you want to search orders");
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }


    @FXML
    private void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "imgCustomer":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/pos/view/ManageCustomerForm.fxml"));
                    break;
                case "imgItem":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/pos/view/ManageItemForm.fxml"));
                    break;
                case "imgOrder":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/pos/view/PlaceOrderForm.fxml"));
                    break;
                case "imgViewOrders":
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/ijse/dep/pos/view/SearchOrdersForm.fxml"));
                    root = fxmlLoader.load();
                    break;
            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();

                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }

    public void btnRestore_OnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Let's restore the backup");
        fileChooser.getExtensionFilters().
                add(new FileChooser.ExtensionFilter("SQL File", "*.sql"));
        File file = fileChooser.showOpenDialog(this.root.getScene().getWindow());
        if (file != null) {

        }
    }

    public void btnBackup_OnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save the DB Backup");
        fileChooser.getExtensionFilters().
                add(new FileChooser.ExtensionFilter("SQL File", "*.sql"));
        File file = fileChooser.showSaveDialog(this.root.getScene().getWindow());
        if (file != null) {
            // Now, we have to backup the DB...
            try {
                Process process = Runtime.getRuntime().
                        exec("mysqldump -h" + DBConnection.host + " -u" + DBConnection.username +
                                " -p" + DBConnection.password + " " + DBConnection.db + " --result-file " +
                                file.getAbsolutePath() + ((file.getAbsolutePath().endsWith(".sql")) ? "" : ".sql"));
                int exitCode = process.waitFor();
                // exitCode == 0; success
                // exitCode !=0; kachal
                if (exitCode != 0) {
                    // Let's read the damn error...!
                    InputStream errorStream = process.getErrorStream();
                    InputStreamReader isr = new InputStreamReader(errorStream); // chars
                    BufferedReader br = new BufferedReader(isr);    // string line by line
                    String out = "";
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        out += line + "\n";
                    }
                    System.out.println(out);
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Database backup process has successfully done").show();
                }
                System.out.println(exitCode);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
