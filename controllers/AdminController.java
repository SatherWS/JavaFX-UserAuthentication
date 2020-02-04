package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import utils.ConnectionUtil;
import utils.ModelTable;

public class AdminController implements Initializable {
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet rs = null;
    private ObservableList<ObservableList> data;
    @FXML
    private TableView table;
    @FXML
    private TableColumn<ModelTable, String> col_id;


    public AdminController() {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conn = ConnectionUtil.connectdb();
    }

    public void buildData() {
        data = FXCollections.observableArrayList();
        try {
            String sql = "select * from applicants";
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}