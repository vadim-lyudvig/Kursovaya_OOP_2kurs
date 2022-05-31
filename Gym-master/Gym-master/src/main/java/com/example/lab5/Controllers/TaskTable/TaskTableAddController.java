package com.example.lab5.Controllers.TaskTable;

import com.example.lab5.Classes.TaskTable;
import com.example.lab5.DataBase.DataBase;
import com.example.lab5.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TaskTableAddController implements Initializable {
    public ComboBox<Integer> cmbStaff = new ComboBox<>();
    public ComboBox<String> cmbHall = new ComboBox<>();
    public TextArea txtStaffInfo;
    public TextArea txtHallInfo;
    public TextArea txtClientInfo;
    public ComboBox<Integer> cmbClient = new ComboBox<>();
    public DatePicker dpDate;
    public Button btnAdd;
    public Button btnClose;
    public Label errorLog;
    public ComboBox<Integer> cmbDuration = new ComboBox<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }
    void init(){
        var ids = DataBase.getClientIds();
        cmbClient.setItems(ids);
        if(ids.size()>=1) {
            cmbClient.setValue(ids.get(0));
            txtClientInfo.setText(DataBase.foundClient(cmbClient.getValue()).toString());
        }
        else{
            txtClientInfo.setText("Нет клиентов");
            cmbClient.setDisable(true);
        }
        var ids1 = DataBase.getStaffIds();
        cmbStaff.setItems(ids1);
        if(ids1.size()>=1) {
            cmbStaff.setValue(ids1.get(0));
            txtStaffInfo.setText(DataBase.foundStaff(cmbStaff.getValue()).toString());
        }
        else{
            txtStaffInfo.setText("Нет персонала");
            cmbStaff.setDisable(true);
        }
        var ids2 = DataBase.getHallTitle();
        cmbHall.setItems(ids2);
        if(ids2.size()>=1) {
            cmbHall.setValue(ids2.get(0));
            txtHallInfo.setText(DataBase.foundHall(cmbHall.getValue()).toString());
        }
        else{
            txtHallInfo.setText("Нет залов");
            cmbHall.setDisable(true);
        }
        ObservableList<Integer> duration = FXCollections.observableArrayList();
        for (int i = 30; i <= 120; i +=15){
            duration.add(i);
        }
        cmbDuration.setItems(duration);
        cmbDuration.setValue(duration.get(0));
    }

    public void cmbStaffSwitch(ActionEvent actionEvent) {
        var info = DataBase.foundStaff(cmbStaff.getValue());
        txtStaffInfo.setText(info.toString());
    }

    public void cmbHallSwitch(ActionEvent actionEvent) {
        var info = DataBase.foundHall(cmbHall.getValue());
        txtHallInfo.setText(info.toString());
    }

    public void cmbClientSwitch(ActionEvent actionEvent) {
        var info = DataBase.foundClient(cmbClient.getValue());
        txtClientInfo.setText(info.toString());
    }

    public void btnAddClick(ActionEvent actionEvent) {
        var tt = new TaskTable();
        if(dpDate.getValue() == null){
            errorLog.setText("Вы не ввели дату");
            return;
        }
        var nDay = LocalDate.now().getDayOfMonth();
        var nMonth = LocalDate.now().getMonth().getValue();
        var nYear = LocalDate.now().getYear();
        var uDay = dpDate.getValue().getDayOfMonth();
        var uMonth = dpDate.getValue().getMonth().getValue();
        var uYear = dpDate.getValue().getYear();
        if(nYear>=uYear && nMonth>=uMonth && nDay>uDay){
            errorLog.setText("Дата занятия меньше сегодняшней даты");
            return;
        }
        if(DataBase.foundHall(cmbHall.getEditor().getText()) == null){
            return;
        }
        try{
            if(DataBase.foundClient(Integer.parseInt(cmbClient.getEditor().getText())) == null){
                return;
            }
            if(DataBase.foundStaff(Integer.parseInt(cmbStaff.getEditor().getText())) == null){
                return;
            }
        }
        catch (Exception ex){
            return;
        }
        tt.setDuration(cmbDuration.getValue());
        tt.setDate(Date.valueOf(dpDate.getValue()));
        tt.setCodeStaff(Integer.parseInt(cmbStaff.getEditor().getText()));
        tt.setCodeHall(DataBase.foundHall(cmbHall.getEditor().getText()).getHallId());
        tt.setCodeClient(Integer.parseInt(cmbClient.getEditor().getText()));
        DataBase.addTaskTable(tt);
        errorLog.setText("Занятие успешно добавлено");
    }

    public void btnClose(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnClose.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Главная страница");
        stage.setScene(scene);
        totalStage.close();
        stage.show();
    }

    public void cmbStaffPress(KeyEvent keyEvent) {
        errorLog.setText("");
        var id = 0;
        try {
            if(cmbStaff.getEditor().getText().length() != 0) {
                id = Integer.parseInt(cmbStaff.getEditor().getText());
            }
            else{
                return;
            }
        }
        catch (Exception ex){
            errorLog.setText("Вы ввели не корректное значение номера сотрудника");
            return;
        }
        var info = DataBase.foundStaff(id);
        if(info == null){
            txtStaffInfo.setText("Сотрудника с таким номером не существует");
            return;
        }
        txtStaffInfo.setText(info.toString());
    }

    public void cmbHallPress(KeyEvent keyEvent) {
        errorLog.setText("");
        var info = DataBase.foundHall(cmbHall.getEditor().getText());
        if(info == null){
            txtHallInfo.setText("Клиента с таким номером не существует");
            return;
        }
        txtHallInfo.setText(info.toString());
    }

    public void cmbClientPress(KeyEvent keyEvent) {
        errorLog.setText("");
        var id = 0;
        try {
            if(cmbClient.getEditor().getText().length() != 0) {
                id = Integer.parseInt(cmbClient.getEditor().getText());
            }
            else{
                return;
            }
        }
        catch (Exception ex){
            errorLog.setText("Вы ввели не корректное значение номера клиента");
            return;
        }
        var info = DataBase.foundClient(id);
        if(info == null){
            txtClientInfo.setText("Клиента с таким номером не существует");
            return;
        }
        txtClientInfo.setText(info.toString());
    }
}
