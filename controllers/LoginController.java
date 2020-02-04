package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.ConnectionUtil;

public class LoginController implements Initializable{
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Text txt_result;
    @FXML
    private ComboBox<String> role;
    @FXML
    private Button btnSignIn;

    Connection conn = null;
    PreparedStatement stmnt = null;
    ResultSet rs = null;

    // Important public no-argument constructor
    public LoginController() {
        conn = ConnectionUtil.connectdb();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (conn == null) {
            txt_result.setFill(Color.RED);
            txt_result.setText("Database sever is not connected");
        }
    }

    @FXML
    public void goToSignup(MouseEvent event) {
        try {
            Node n = (Node) event.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/frontend/signup.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean testLogin() {
        String sql = "select * from users where email = ? and password = ?";
        String email = username.getText();
        String pswd = password.getText();
        if (email.isEmpty() || pswd.isEmpty()) {
            txt_result.setFill(Color.RED);
            txt_result.setText("Email and password fields cannot be empty");
        }
        else {
            try {
                stmnt = conn.prepareStatement(sql);
                stmnt.setString(1, email);
                stmnt.setString(2, pswd);
                rs = stmnt.executeQuery();
                if (!rs.next()) {
                    txt_result.setFill(Color.RED);
                    txt_result.setText("Incorrect Email or Password");
                }
                else {
                    // show success message & return true
                    txt_result.setFill(Color.GREEN);
                    txt_result.setText("Correct Credentials!");
                    return true;
                }
            }catch (Exception x){
                x.printStackTrace();
            }
        }
        return false;
    }

    @FXML
    public void login(MouseEvent event) {
        if (event.getSource() == btnSignIn) {
            if (testLogin()) {
                try {
                    Node n = (Node) event.getSource();
                    Stage stage = (Stage) n.getScene().getWindow();
                    stage.close();
                    //Parent root = FXMLLoader.load(getClass().getResource("/application/admin-panel.fxml"));
                    Parent root = FXMLLoader.load(getClass().getResource("/frontend/admin-page.fxml"));
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
                    stage.setScene(scene);
                    stage.show();
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}