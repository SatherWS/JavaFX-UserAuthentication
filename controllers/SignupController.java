package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.sql.PreparedStatement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.ConnectionUtil;

public class SignupController implements Initializable {
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField password_check;
    @FXML
    private Text txt_result;

    PreparedStatement stmnt = null;
    ResultSet rs = null;
    Connection conn = null;

    // Important public no-argument constructor
    public SignupController() {
        conn = ConnectionUtil.connectdb();
    }

    @FXML
    public void goToLogin(MouseEvent event) {
        try {
            Node n = (Node) event.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/frontend/login.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }catch(IOException er) {
            er.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (conn == null)
            txt_result.setText("Database is not connected!");
    }

    // TODO: IF ANY TEXT FIELD IS NULL SEND ERROR
    @FXML
    public void createUser(MouseEvent event) {
        // check if passwords match
        if (!password.getText().contentEquals(password_check.getText()))
            txt_result.setText("Error passwords do not match");
        else {
            // check if user exist
            String sql = "select * from users where email = ?";
            String email = username.getText();
            String passwrd = password.getText();
            String firstname = fname.getText();
            String lastname = lname.getText();

            try {
                stmnt = conn.prepareStatement(sql);
                stmnt.setString(1, email);
                rs = stmnt.executeQuery();

                if (rs.next())
                    txt_result.setText("Error email already exists!");
                else {
                    // insert user into database
                    sql = "insert into users(fname, lname, email, password) values (?, ?, ?, ?)";
                    stmnt = conn.prepareStatement(sql);
                    stmnt.setString(1, firstname);
                    stmnt.setString(2, lastname);
                    stmnt.setString(3, email);
                    stmnt.setString(4, passwrd);
                    stmnt.executeUpdate();
                    txt_result.setFill(Color.GREEN);
                    txt_result.setText("User created, redirecting...");
                    redirectAdmin(event);
                }
            }
            catch(SQLException s) {
                s.printStackTrace();
            }
        }
    }

    public void redirectAdmin(MouseEvent event) {
        try {
            Node n = (Node) event.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/frontend/admin-page.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }catch(IOException er) {
            er.printStackTrace();
        }

    }
}