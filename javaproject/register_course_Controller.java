package javaproject;

import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class register_course_Controller implements Initializable {



    @FXML
    private void getAddView(javafx.scene.input.MouseEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("courseTableview_add.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(register_course_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getDeleteView(javafx.scene.input.MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("courseTableview_delete.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(register_course_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getSearchView(javafx.scene.input.MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("courseTableview_search.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(register_course_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getUpdateView(javafx.scene.input.MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("courseTableview_update.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(register_course_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

   


     @FXML
    private void Exit(javafx.scene.input.MouseEvent event) {
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
