package com.example.lab5.Controllers;

import com.example.lab5.Classes.Hall;
import com.example.lab5.Classes.Staff;
import com.example.lab5.Classes.Service;
import com.example.lab5.Classes.TaskTable;
import com.example.lab5.Classes.ClubCard;
import com.example.lab5.Classes.Client;
import com.example.lab5.DataBase.DataBase;
import com.example.lab5.HelloApplication;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    //Работа с клиентом
    public TableView<Client> tvlClient;
    public TableColumn<Integer, Client> tcClientId;
    public TableColumn<String, Client> tcName;
    public TableColumn<String, Client> tcPatronymic;
    public TableColumn<String, Client> tcSurname;
    public TableColumn<String, Client> tcAddress;
    public TableColumn<String, Client> tcPhoneNumber;
    public Button btnClientAdd;
    public Button btnClientEdit;
    public Button btnClientDelete;
    
    //Работа с абониментом
    public TableView<ClubCard> tvClubCard;
    public TableColumn<Integer, ClubCard> tcCardId;
    public TableColumn<Integer, ClubCard> tcServiceClubId;
    public TableColumn<Integer, ClubCard> tcClientCardId;
    public TableColumn<Integer, ClubCard> tcPrice;
    public TableColumn<LocalDate, ClubCard> tcStartDate;
    public TableColumn<LocalDate, ClubCard> tcEndCard;
    public Button btnCardAdd;
    public Button btnCardEdit;
    public Button btnCardDelete;
    
    //Работа с расписанием
    public TableView<TaskTable> tvTaskTable;
    public TableColumn<Integer, TaskTable> tcNumber;
    public TableColumn<Integer, TaskTable> tcCodeStaff;
    public TableColumn<Integer, TaskTable> tcCodeClient;
    public TableColumn<Integer, TaskTable> tcCodeHall;
    public TableColumn<LocalDate, TaskTable> tcDate;
    public TableColumn<Integer, TaskTable> tcDuration;
    public Button btnTaskAdd;
    public Button btnTaskDelete;

    //Работа с услугами
    public TableView<Service> tvService;
    public TableColumn<Integer, Service> tcServiceId;
    public TableColumn<Integer, Service> tcServiceTitle;
    public TableColumn<Integer, Service> tcServiceCost;
    public Button btnServiceAdd;
    public Button btnServiceDelete;
    public Button btnServiceEdit;

    //Работа с залами
    //Таблица с залами
        public TableView<Hall> tvHall;
    //Столбец с id залов
    public TableColumn<Hall, Integer> tcHall;
    //Столбец с названиями залов
    public TableColumn<Hall, String> tcHallName;
    //Кнопка добавить зал
    public Button btnHallAdd;
    //Кнопка удалить зал
    public Button btnHallDelete;
    //Кнопка редактировать зал

    //Работа с сотрудниками
    public TableView<Staff> tvStaff;
    public TableColumn<Integer, Staff> tcStaffId;
    public TableColumn<String, Staff>  tcStaffName;
    public TableColumn<String, Staff>  tcStaffPatronymic;
    public TableColumn<String, Staff>  tcStaffSurname;
    public TableColumn<String, Staff>  tcStaffAddress;
    public TableColumn<String, Staff>  tcStaffPhone;
    public TableColumn<Integer, Staff> tcStaffExp;
    public TableColumn<Integer, Staff> tcStaffSalary;
    public Button btnStaffAdd;
    public Button btnStaffEdit;
    public Button btnStaffDelete;
    public TextField txtClientNameQuery;
    public TextField txtClubCardService;
    public DatePicker dbTaskTable;
    public TextField txtStaffLastName;
    public TextArea txtClubCardInfo;
    public TextArea txtTaskTableInfo;
    public TextField txtServiceQuery;
    public TextArea txtServiceInfo;
    public TextField txtStaffQuery;
    public TextArea txtStaffInfo;
    public TextField txtHallQuery;
    public TextArea txtHallInfo;
    public Button btnDateDel;


    public void btnClientAddClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnHallAdd.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("clientAdd.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Добавить клиента");
        stage.setScene(scene);
        totalStage.close();
        stage.show();
    }
    Hall hallDel;
    ClubCard clubCardDel;
    Client clientDel;
    Service serviceDel;
    Staff staffDel;
    TaskTable taskTableDel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
        TableView.TableViewSelectionModel<ClubCard> selectionModel = tvClubCard.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<ClubCard>() {
            @Override
            public void changed(ObservableValue<? extends ClubCard> observableValue, ClubCard clubCard, ClubCard t1) {
                if(t1!=null){
                    clubCardDel = t1;
                    txtClubCardInfo.setText(t1.toString());
                }
            }
        });
        TableView.TableViewSelectionModel<Hall> selectionModelHall = tvHall.getSelectionModel();
        selectionModelHall.selectedItemProperty().addListener(new ChangeListener<Hall>() {
            @Override
            public void changed(ObservableValue<? extends Hall> observableValue, Hall hall, Hall t1) {
                if(t1 != null){
                    hallDel = t1;
                }
            }
        });
        TableView.TableViewSelectionModel<TaskTable> selectionModelTT = tvTaskTable.getSelectionModel();
        selectionModelTT.selectedItemProperty().addListener(new ChangeListener<TaskTable>() {
            @Override
            public void changed(ObservableValue<? extends TaskTable> observableValue, TaskTable taskTable, TaskTable t1) {
                if(t1!=null){
                    taskTableDel = t1;
                    txtTaskTableInfo.setText(t1.toString());
                }
            }
        });
        TableView.TableViewSelectionModel<Client> selectionModelClient = tvlClient.getSelectionModel();
        selectionModelClient.selectedItemProperty().addListener(new ChangeListener<Client>() {
            @Override
            public void changed(ObservableValue<? extends Client> observableValue, Client client, Client t1) {
                if(t1!= null){
                    clientDel = t1;
                }
            }
        });
        TableView.TableViewSelectionModel<Service> selectionModelService = tvService.getSelectionModel();
        selectionModelService.selectedItemProperty().addListener(new ChangeListener<Service>() {
            @Override
            public void changed(ObservableValue<? extends Service> observableValue, Service client, Service t1) {
                if(t1!=null){
                    serviceDel = t1;
                }
            }
        });
        TableView.TableViewSelectionModel<Staff> selectionModelStaff = tvStaff.getSelectionModel();
        selectionModelStaff.selectedItemProperty().addListener(new ChangeListener<Staff>() {
            @Override
            public void changed(ObservableValue<? extends Staff> observableValue, Staff staff, Staff t1) {
                if(t1!=null){
                    staffDel = t1;
                }
            }
        });
    }

    void loadTable(){
        tcClientId.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcPatronymic.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        tcSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tcPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tvlClient.setItems(DataBase.getClient());

        tcCardId.setCellValueFactory(new PropertyValueFactory<>("cardId"));
        tcServiceClubId.setCellValueFactory(new PropertyValueFactory<>("serviceInfo"));
        tcClientCardId.setCellValueFactory(new PropertyValueFactory<>("clientInfo"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tcStartDate.setCellValueFactory(new PropertyValueFactory<>("startCard"));
        tcEndCard.setCellValueFactory(new PropertyValueFactory<>("endCard"));
        tvClubCard.setItems(DataBase.getClubCard());

        tcNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        tcCodeStaff.setCellValueFactory(new PropertyValueFactory<>("staffInfo"));
        tcCodeClient.setCellValueFactory(new PropertyValueFactory<>("clientInfo"));
        tcCodeHall.setCellValueFactory(new PropertyValueFactory<>("hallInfo"));
        tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tcDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        tvTaskTable.setItems(DataBase.getTaskTable());

        tcServiceId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcServiceTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcServiceCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        tvService.setItems(DataBase.getService());

        //Создаю фабрику для сборки данных по залу
        //для id хола указываю параметр с класса Hall, который он берет(вызывает геттер)
        tcHall.setCellValueFactory(new PropertyValueFactory<>("hallId"));
        tcHallName.setCellValueFactory(new PropertyValueFactory<>("hallName"));
        //закидываю в таблицу список зала(специальный список из JavaFX)
        tvHall.setItems(DataBase.getHall());

        tcStaffId.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        tcStaffName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcStaffPatronymic.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        tcStaffSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        tcStaffAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tcStaffPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tcStaffExp.setCellValueFactory(new PropertyValueFactory<>("workExperience"));
        tcStaffSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tvStaff.setItems(DataBase.getStaff());
    }

    public void btnClientEditClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnHallAdd.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("clientEdit.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Изменить клиента");
        stage.setScene(scene);
        totalStage.close();
        stage.show();
    }

    public void btnClientDeleteClick(ActionEvent actionEvent) throws IOException {
        if(clientDel!=null){
            DataBase.deleteClient(clientDel.getClientId());
            clientDel = null;
            loadTable();
        }
    }

    public void btnCardAddClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnHallAdd.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ClubCardAdd.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Добавить абонемент");
        stage.setScene(scene);
        totalStage.close();
        stage.show();
    }

    public void btnCardEditClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnHallAdd.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ClubCardEdit.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Изменить абонемент");
        stage.setScene(scene);
        totalStage.close();
        stage.show();
    }

    public void btnCardDeleteClick(ActionEvent actionEvent) throws IOException {
        if(clubCardDel!=null){
            DataBase.deleteClubCard(clubCardDel.getCardId());
            clubCardDel = null;
            loadTable();
        }
    }

    public void btnTaskAddClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnHallAdd.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TaskTableAdd.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Добавить занятие");
        stage.setScene(scene);
        totalStage.close();
        stage.show();

    }

    public void btnTaskDeleteClick(ActionEvent actionEvent) throws IOException {
        if(taskTableDel!=null){
            DataBase.deleteTaskTable(taskTableDel.getNumber());
            taskTableDel = null;
            loadTable();
        }
    }

    public void btnServiceAddClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnHallAdd.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ServiceAdd.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Добавить услугу");
        stage.setScene(scene);
        totalStage.close();
        stage.show();
    }

    public void btnServiceDeleteClick(ActionEvent actionEvent) throws IOException {
        if(serviceDel!=null){
            DataBase.deleteService(serviceDel.getId());
            serviceDel = null;
            loadTable();
        }
    }

    public void btnServiceEditClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnHallAdd.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ServiceEdit.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Изменить услугу");
        stage.setScene(scene);
        totalStage.close();
        stage.show();
    }

    public void btnHallAddClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnHallAdd.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hallAdd-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Добавить зал");
        stage.setScene(scene);
        totalStage.close();
        stage.show();

    }

    public void btnHallDeleteClick(ActionEvent actionEvent) throws IOException {
        if(hallDel != null){
            DataBase.deleteHall(hallDel.getHallId());
            hallDel = null;
            loadTable();
        }
    }

    public void btnStaffAddClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnHallAdd.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("stuffAdd.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Добавить сотрудника");
        stage.setScene(scene);
        totalStage.close();
        stage.show();
    }

    public void btnStaffEditClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnHallAdd.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("staffEdit.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Изменить сотрудника");
        stage.setScene(scene);
        totalStage.close();
        stage.show();
    }

    public void btnStaffDeleteClick(ActionEvent actionEvent) throws IOException {
        if(staffDel != null){
            DataBase.deleteStaff(staffDel.getStaffId());
            staffDel = null;
            loadTable();
        }
    }

    public void txtClientNameQueryPress(KeyEvent actionEvent) {
        var c = DataBase.findClientQuery(txtClientNameQuery.getText());
        tvlClient.setItems(c);
    }

    public void txtClubCardServicePress(KeyEvent keyEvent) {
        var cc = DataBase.findClubCardQuery(txtClubCardService.getText());
        tvClubCard.setItems(cc);
    }

    public void dbTaskTableClick(ActionEvent actionEvent) {
        if(dbTaskTable.getValue()!=null) {
            var tt = DataBase.findTaskTableQuery(dbTaskTable.getValue().toString(), txtStaffLastName.getText());
            tvTaskTable.setItems(tt);
        }
    }

    public void txtStaffLastNamePress(KeyEvent keyEvent) {
        if(dbTaskTable.getValue() != null) {
            var tt = DataBase.findTaskTableQuery(dbTaskTable.getValue().toString(), txtStaffLastName.getText());
            tvTaskTable.setItems(tt);
        }
        else {
            var tt = DataBase.findTaskTableQuery("", txtStaffLastName.getText());
            tvTaskTable.setItems(tt);
        }
    }

    public void txtServiceQueryPress(KeyEvent keyEvent) {
        if(txtServiceQuery.getText().length()==0){
            txtServiceInfo.setText("");
            return;
        }
        var cc = DataBase.findServiceTableQuery(txtServiceQuery.getText());
        var ans = "";
        for(int i = 0; i < cc.size(); i++){
            ans+="Номер карты:" + cc.get(i).getCardId() +"\n" +
                    "Номер клиента: " + cc.get(i).getClientId()+"\n" +
                    "Фамилия: " + DataBase.foundClient(cc.get(i).getClientId()).getSurname()+"\n"+
                    "Имя : " + DataBase.foundClient(cc.get(i).getClientId()).getName()+"\n"+
                    "Отчество: " + DataBase.foundClient(cc.get(i).getClientId()).getPatronymic()+"\n";
            if(i!=cc.size()-1) {
                ans+="------------\n";
            }
        }
        txtServiceInfo.setText(ans);
    }

    public void txtStaffQueryPress(KeyEvent keyEvent) {
        if(txtStaffQuery.getText().length()==0){
            txtStaffInfo.setText("");
            return;
        }
        var staff = DataBase.findStaffTableQuery(txtStaffQuery.getText());
        var ans = "";
        for(int i = 0; i < staff.size(); i++){
            ans+="Номер клиента: " + staff.get(i).getCodeClient()+"\n" +
                    "" + DataBase.foundClient(staff.get(i).getCodeClient()) + "\n" +
                    "Номер зала: " + staff.get(i).getCodeHall()+"\n" +
                    "Дата: " + staff.get(i).getDate()+"\n";
            if(i!=staff.size()-1) {
                ans+="------------\n";
            }
        }
        txtStaffInfo.setText(ans);
    }

    public void txtHallQueryPress(KeyEvent keyEvent) {
        if(txtHallQuery.getText().length()==0){
            txtHallInfo.setText("");
            return;
        }
        var halls = DataBase.findHallTableQuery(txtHallQuery.getText());
        var ans = "";
        for(int i = 0; i < halls.size(); i++){
            ans+="Номер сотрудника: " + halls.get(i).getCodeStaff()+"\n" +
                    "Фамилия сотрудника: " + DataBase.foundStaff(halls.get(i).getCodeStaff()).getSurname() + "\n"+
                    "Номер клиента: " + halls.get(i).getCodeClient()+"\n" +
                    "Фамилия клиента: " + DataBase.foundClient(halls.get(i).getCodeClient()).getSurname() + "\n"+
                    "Дата: " + halls.get(i).getDate() + "\n" +
                    "Продолжительность: " + halls.get(i).getDuration()+"\n";
            if(i!=halls.size()-1) {
                ans+="------------\n";
            }
            txtHallInfo.setText(ans);
        }
    }

    public void btnDateDelClick(ActionEvent actionEvent) {
        dbTaskTable.setValue(null);
        var tt = DataBase.findTaskTableQuery("", txtStaffLastName.getText());
        tvTaskTable.setItems(tt);
    }
}