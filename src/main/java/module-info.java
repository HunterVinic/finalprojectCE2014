module com.example.finalprojectce2014 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.finalprojectce2014 to javafx.fxml;
    exports com.example.finalprojectce2014;
}