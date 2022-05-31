package com.example.lab5.Controllers.Service;

import com.example.lab5.Classes.Service;
import com.example.lab5.DataBase.DataBase;
import com.example.lab5.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ServiceEditController implements Initializable {
    public ComboBox <String> cmbId = new ComboBox();
    public Button btnFound;
    public TextField txtTitle;
    public TextField txtCost;
    public AnchorPane pane;
    public Button btnAdd;
    public Button btnClose;
    public AnchorPane topPane;
    public Label errorLog;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }



    public void btnFoundClick(ActionEvent actionEvent) {
        try {
            if(cmbId.getEditor().getText().length()==0){
                throw new Exception();
            }
        }
        catch (Exception ex){
            errorLog.setText("Вы ввели некорректное значение");
            return;
        }
        var info = DataBase.foundService(cmbId.getEditor().getText());
        if(info == null){
            errorLog.setText("Услуги с таким номером не существует");
            return;
        }
        id = info.getId();
        txtTitle.setText(info.getTitle());
        txtCost.setText(String.valueOf(info.getCost()));
        pane.setVisible(true);
        topPane.setDisable(true);
    }
    int id = 0;
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
            ser.setId(id);

        }
        catch (Exception ex){
            errorLog.setText(ex.getMessage());
        }
        try {
            DataBase.editService(ser);
        } catch (SQLException e) {
            errorLog.setText("Данная услуга уже существует");
        }
        errorLog.setText("Услуга успешно изменена");
        init();
        txtTitle.setText("");
        txtCost.setText("");
        pane.setVisible(false);
        topPane.setDisable(false);
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
    void init(){
        var ids = DataBase.getServiceTitle();
        cmbId.setItems(ids);
        if(ids.size()>=1) {
            cmbId.setValue(ids.get(0));
        }
        else{
            cmbId.setDisable(true);
        }
    }
}
