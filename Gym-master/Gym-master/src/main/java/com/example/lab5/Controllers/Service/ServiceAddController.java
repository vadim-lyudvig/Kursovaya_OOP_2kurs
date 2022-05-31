package com.example.lab5.Controllers.Service;

import com.example.lab5.Classes.Service;
import com.example.lab5.DataBase.DataBase;
import com.example.lab5.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ServiceAddController implements Initializable {
    public TextField txtTitle;
    public TextField txtCost;
    public Button btnAdd;
    public Button btnClose;
    public Label errorLog;

    public void btnAddClick(ActionEvent actionEvent) {
        var ser = new Service();
        try{
            ser.setTitle(txtTitle.getText());
            try{
                int cost = Integer.parseInt(txtCost.getText());
                ser.setCost(cost);
            }
            catch (Exception e){
                errorLog.setText("Вы ввели некорректное значение стоимости");
                return;
            }
            errorLog.setText("Услуга успешно добавлена");
            txtTitle.setText("");
            txtCost.setText("");
        }
        catch (Exception ex){
            errorLog.setText(ex.getMessage());
        }
        try {
            DataBase.addService(ser);
        } catch (SQLException e) {
            errorLog.setText("Данная услуга уже существует");
        }
    }

    public void btnCloseClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnAdd.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Главная страница");
        stage.setScene(scene);
        totalStage.close();
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
