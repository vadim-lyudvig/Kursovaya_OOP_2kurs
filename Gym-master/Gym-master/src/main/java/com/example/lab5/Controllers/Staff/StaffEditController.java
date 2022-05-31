package com.example.lab5.Controllers.Staff;

import com.example.lab5.Classes.Staff;
import com.example.lab5.DataBase.DataBase;
import com.example.lab5.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StaffEditController implements Initializable {
    public AnchorPane topPane;
    public ComboBox<Integer> cmbIds = new ComboBox<>();
    public Button btnFound;
    public AnchorPane bottomPane;
    public TextField txtName;
    public TextField txtSurname;
    public TextField txtLastname;
    public TextField txtPhone;
    public TextField txtAddress;
    public Button txtEdit;
    public ComboBox<Integer> cmbExp = new ComboBox<>();
    public TextField txtSalary;
    public Label errorLog;
    public Button txtClose;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }
    void init(){
        var ids = DataBase.getStaffIds();
        cmbIds.setItems(ids);
        if(ids.size()>=1) {
            cmbIds.setValue(ids.get(0));
        }
        else{
            cmbIds.setDisable(true);
        }
        ObservableList<Integer> salaries = FXCollections.observableArrayList();
        for(int i = 0; i < 16; i++){
            salaries.add(i);
        }
        cmbExp.setItems(salaries);
        cmbExp.setValue(salaries.get(0));
    }

    public void txtEditClick(ActionEvent actionEvent) {
        var staff = new Staff();
        if(txtName.getText().length()==0){
            errorLog.setText("Вы не ввели имя");
            return;
        }
        staff.setName(txtName.getText());
        if(txtSurname.getText().length()==0){
            errorLog.setText("Вы не ввели отчество");
            return;
        }
        staff.setPatronymic(txtSurname.getText());
        if(txtLastname.getText().length()==0){
            errorLog.setText("Вы не ввели фамилию");
            return;
        }
        staff.setSurname(txtLastname.getText());
        if(txtAddress.getText().length() == 0){
            errorLog.setText("Вы не ввели адрес");
            return;
        }
        staff.setAddress(txtAddress.getText());
        staff.setWorkExperience(cmbExp.getValue());
        try{
            int salary = Integer.parseInt(txtSalary.getText());
            staff.setSalary(salary);
        }
        catch (Exception e){
            errorLog.setText("Вы ввели некорректное значение зарплаты");
            return;
        }

        try{
            if(txtPhone.getText().length() != 11 && txtPhone.getText().length() != 6){
                throw new Exception();
            }
            int phone = Integer.parseInt(txtPhone.getText());
            staff.setPhoneNumber(String.valueOf(phone));
        }
        catch (Exception e){
            errorLog.setText("Вы ввели некорректное значение телефона");
            return;
        }
        staff.setStaffId(Integer.parseInt(cmbIds.getEditor().getText()));
        DataBase.editStaff(staff);
        txtPhone.setText("");
        txtSalary.setText("");
        txtAddress.setText("");
        txtLastname.setText("");
        txtSurname.setText("");
        txtName.setText("");
        errorLog.setText("Сотрудник успешно изменен");
        topPane.setDisable(false);
        bottomPane.setVisible(false);
    }


    public void btnFoundClick(ActionEvent actionEvent) {
        int id = 0;
        try {
            id = Integer.parseInt(cmbIds.getEditor().getText());
        }
        catch (Exception ex){
            errorLog.setText("Вы ввели некорректное значение");
            return;
        }
        var staff = DataBase.foundStaff(id);
        if(staff == null){
            errorLog.setText("Сотрудника с таким номером не существует");
            return;
        }
        txtAddress.setText(staff.getAddress());
        txtLastname.setText(staff.getSurname());
        txtName.setText(staff.getName());
        txtSalary.setText(String.valueOf(staff.getSalary()));
        txtSurname.setText(staff.getPatronymic());
        txtPhone.setText(staff.getPhoneNumber());
        cmbExp.setValue(staff.getWorkExperience());
        topPane.setDisable(true);
        bottomPane.setVisible(true);
    }

    public void txtCloseClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) txtClose.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Главная страница");
        stage.setScene(scene);
        totalStage.close();
        stage.show();
    }
}
