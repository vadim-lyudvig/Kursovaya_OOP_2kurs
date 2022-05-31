package com.example.lab5.Controllers.ClubCard;

import com.example.lab5.Classes.ClubCard;
import com.example.lab5.Classes.Service;
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
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ClubCardEditController implements Initializable {
    public ComboBox<Integer> cmbIds = new ComboBox<>();
    public Label errorLog;
    public Button btnFound;
    public ComboBox<String> cmbService = new ComboBox<>();
    public DatePicker dateStart;
    public DatePicker dateEnd;
    public ComboBox<Integer> cmbPrice = new ComboBox<>();
    public Button btnAdd;
    public Label errorLog1;
    public Button btnClose;
    public AnchorPane topPane;
    public AnchorPane bottomPane;
    public Label lblClientId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }
    void init(){
        var ids = DataBase.getClientIds();
        cmbIds.setItems(ids);
        if(ids.size()>=1) {
            cmbIds.setValue(ids.get(0));
        }
        else{
            cmbIds.setDisable(true);
        }
        var ids1 = DataBase.getServiceTitle();
        cmbService.setItems(ids1);
        if(ids1.size()>=1) {
            cmbService.setValue(ids1.get(0));
        }
        else{
            cmbService.setDisable(true);
        }
        ObservableList<Integer> prices = FXCollections.observableArrayList();
        for(int i = 500; i <= 5000; i+=500){
            prices.add(i);
        }
        cmbPrice.setItems(prices);
        cmbPrice.setValue(prices.get(0));
    }


    public void btnAddClick(ActionEvent actionEvent) {
        var clubCard = new ClubCard();
        if(dateStart.getValue() == null){
            errorLog.setText("Вы не ввели дату начала");
            return;
        }
        if(dateEnd.getValue() == null){
            errorLog.setText("Вы не ввели дату окончания");
            return;
        }
        var startDate = dateStart.getValue();
        var endDate = dateEnd.getValue();
        var sDay = startDate.getDayOfMonth();
        var sMonth = startDate.getMonth().getValue();
        var sYear = startDate.getYear();
        var eDay = endDate.getDayOfMonth();
        var eMonth = endDate.getMonth().getValue();
        var eYear = endDate.getYear();
        if(sYear>=eYear && sMonth>=eMonth && sDay>=eDay){
            errorLog.setText("Дата начала больше даты окончания");
            return;
        }
        var serviceId = 0;
        Service service = DataBase.foundService(cmbService.getEditor().getText());
        if(service == null){
            errorLog.setText("Услуги с таким названием не существует");
            return;
        }
        serviceId = service.getId();
        clubCard.setEndCard(Date.valueOf(endDate));
        clubCard.setStartCard(Date.valueOf(startDate));
        clubCard.setPrice(cmbPrice.getValue());
        clubCard.setClientId(clientId);
        clubCard.setServiceId(serviceId);
        clubCard.setCardId(Integer.parseInt(cmbIds.getEditor().getText()));
        DataBase.editClubCard(clubCard);
        errorLog.setText("Абонемент успешно изменен");
        topPane.setDisable(false);
        bottomPane.setVisible(false);
    }

    public void btnCloseClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnClose.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Главная страница");
        stage.setScene(scene);
        totalStage.close();
        stage.show();
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
        var cc = DataBase.foundClubCard(id);
        if(cc == null){
            errorLog.setText("Абонемента с таким номером не существует");
            return;
        }
        clientId = cc.getClientId();
        lblClientId.setText(DataBase.foundClient(cc.getClientId()).toString());
        cmbService.setValue(DataBase.foundService(cc.getServiceId()).getTitle());
        dateEnd.setValue(LocalDate.parse(cc.getEndCard().toString()));
        dateStart.setValue(LocalDate.parse(cc.getStartCard().toString()));
        cmbPrice.setValue(cc.getPrice());
        topPane.setDisable(true);
        bottomPane.setVisible(true);
    }
    int clientId = 0;
}
