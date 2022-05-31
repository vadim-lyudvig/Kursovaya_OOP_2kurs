package com.example.lab5.Controllers.Client;

import com.example.lab5.Classes.Client;
import com.example.lab5.Classes.Staff;
import com.example.lab5.DataBase.DataBase;
import com.example.lab5.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientAddController implements Initializable {
    public TextField txtName;
    public TextField txtSurname;
    public TextField txtLastname;
    public TextField txtPhone;
    public TextField txtAddress;
    public Button btnAdd;
    public Button btnClose;
    public Label errorLog;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    public void btnAddClick(ActionEvent actionEvent) {
        var p = txtPhone.getText().length();
        var client = new Client();
        if(txtName.getText().length()==0){
            errorLog.setText("Вы не ввели имя");
            return;
        }
        client.setName(txtName.getText());
        if(txtSurname.getText().length()==0){
            errorLog.setText("Вы не ввели отчество");
            return;
        }
        client.setPatronymic(txtSurname.getText());
        if(txtLastname.getText().length()==0){
            errorLog.setText("Вы не ввели фамилию");
            return;
        }
        client.setSurname(txtLastname.getText());
        if(txtAddress.getText().length() == 0){
            errorLog.setText("Вы не ввели адрес");
            return;
        }
        client.setAddress(txtAddress.getText());

        try{
            if(txtPhone.getText().length() != 10 && txtPhone.getText().length() != 6){
                throw new Exception();
            }
            int phone = Integer.parseInt(txtPhone.getText());
            client.setPhoneNumber(String.valueOf(phone));
        }
        catch (Exception e){
            errorLog.setText("Вы ввели некорректное значение телефона");
            return;
        }
        DataBase.addClients(client);
        txtPhone.setText("");
        txtAddress.setText("");
        txtLastname.setText("");
        txtSurname.setText("");
        txtName.setText("");
        errorLog.setText("Клиент успешно добавлен");
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
}
