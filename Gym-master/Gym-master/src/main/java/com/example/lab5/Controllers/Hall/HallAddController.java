package com.example.lab5.Controllers.Hall;

import com.example.lab5.Classes.Hall;
import com.example.lab5.DataBase.DataBase;
import com.example.lab5.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HallAddController implements Initializable {
    public TextField tfHallTitle;
    public Button btnHallAdd;
    public Button btnHallClose;
    public Text errorLog;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btnHallAddClick(ActionEvent actionEvent) {
        var hall = new Hall();
        try {
            hall.setHallName(tfHallTitle.getText());
            tfHallTitle.setText("");
            errorLog.setText("Зал добавлен!");
        } catch (Exception e) {
            errorLog.setText(e.getMessage());
        }
        try {
            DataBase.addHall(hall.getHallName());
        } catch (SQLException e) {
            errorLog.setText("Данный зал уже существует");
        }
    }

    public void btnHallCloseClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnHallAdd.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Главная страница");
        stage.setScene(scene);
        totalStage.close();
        stage.show();
    }
}
