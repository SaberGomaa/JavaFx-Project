/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ali Tahoon
 */
public class courseInputController implements Initializable {

    @FXML
    private TextField txtID;
    @FXML
    private TextField txtName;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClear;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    models.register student = null;

    //get autoID number
    public String autoNumber(String tableName, String columName) {
        try {
            setConnection();
            Statement stat = con.createStatement();
            String autonum = "select max(" + columName + ")+1 as autonum" + " from " + tableName;
            stat.executeQuery(autonum);
            String num = "";
            while (stat.getResultSet().next()) {
                num = stat.getResultSet().getString("autonum");
            }
            con.close();
            if (num == null || "".equals(num)) {
                return "1";
            } else {
                return num;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "0";
        }
    }

    @FXML
    private void onsave(javafx.scene.input.MouseEvent event) {
        System.out.println("javaproject.StudentinputController.onsave()");
        int id = Integer.parseInt(autoNumber("registerd", "courseID"));
        String name = txtName.getText();
        if (name.isEmpty() || name==null ||id == 0 ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All Data");
            alert.showAndWait();
        } else {
            try {
                String q = "INSERT INTO registerd VALUES ("
                        + "'" + name + "'" + "," + id + ")";
                System.out.println(q);
                runNonQuery(q);
                txtName.setText(null);
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }

        }
        txtID.setText(autoNumber("registerd", "courseID"));
        
    }

    @FXML
    private void onClear(javafx.scene.input.MouseEvent event) {
        txtName.setText(null);
    }
    private static com.mysql.jdbc.Connection con;
    private static String url = " ";
    private static String dbName = "";

    //get connction with database
    private void setConnection() {
        try {
            setURL();
            con = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, "root", "");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //set  database link
    private void setURL() {
        url = "jdbc:mysql://localhost:3306/courserigistartiosystem"
                + "?useUnicode=true&characterEncoding=UTF-8";
    }

    //excute database query
    private boolean runNonQuery(String sqlStatement) {
        setConnection();
        Statement stmt;
        try {
            stmt = con.createStatement();
            stmt.execute(sqlStatement);
            con.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @FXML
    private void onclear(MouseEvent event) {

    }

    @FXML
    private void Exit(javafx.scene.input.MouseEvent event) {
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtID.setText(autoNumber("registerd", "courseID"));
    }

//    private void getQuery() {
//        
//    }
//
//    private void insertQuery() {
//    }
}
