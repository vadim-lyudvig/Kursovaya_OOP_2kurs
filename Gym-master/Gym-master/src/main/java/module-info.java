module com.example.lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.lab5 to javafx.fxml;
    exports com.example.lab5;
    exports com.example.lab5.Controllers;
    opens com.example.lab5.Controllers to javafx.fxml;
    exports com.example.lab5.Classes;
    opens com.example.lab5.Classes to javafx.fxml;
    exports com.example.lab5.Controllers.Client;
    opens com.example.lab5.Controllers.Client to javafx.fxml;
    exports com.example.lab5.Controllers.Staff;
    opens com.example.lab5.Controllers.Staff to javafx.fxml;
    exports com.example.lab5.Controllers.Hall;
    opens com.example.lab5.Controllers.Hall to javafx.fxml;
    exports com.example.lab5.Controllers.Service;
    opens com.example.lab5.Controllers.Service to javafx.fxml;
    exports com.example.lab5.Controllers.ClubCard;
    opens com.example.lab5.Controllers.ClubCard to javafx.fxml;
    exports com.example.lab5.Controllers.TaskTable;
    opens com.example.lab5.Controllers.TaskTable to javafx.fxml;
}