package com.example.lab5.Controllers.Staff;

import com.example.lab5.Classes.Staff;
import com.example.lab5.DataBase.DataBase;
import com.example.lab5.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StaffAddController implements Initializable {
    public TextField txtName;
    public TextField txtSurname;
    public TextField txtLastname;
    public TextField txtAddress;
    public Button txtAdd;
    public Button txtClose;
    public TextField txtPhone;
    public ComboBox<Integer> cmbExp = new ComboBox<>();
    public Label errorLog;
    public TextField txtSalary;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }
    void init(){
        ObservableList<Integer> salaries = FXCollections.observableArrayList();
        for(int i = 0; i < 16; i++){
            salaries.add(i);
        }
        cmbExp.setItems(salaries);
        cmbExp.setValue(salaries.get(0));
    }

    public void txtAddClick(ActionEvent actionEvent) {
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
        DataBase.addStaff(staff);
        txtPhone.setText("");
        txtSalary.setText("");
        txtAddress.setText("");
        txtLastname.setText("");
        txtSurname.setText("");
        txtName.setText("");
        errorLog.setText("Сотрудник успешно добавлен");
    }

    public void txtCloseClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) txtAdd.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Главная страница");
        stage.setScene(scene);
        totalStage.close();
        stage.show();
    }
}
