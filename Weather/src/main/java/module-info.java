module com.example.weather {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;
    opens com.example.weather to javafx.fxml, com.google.gson;
    exports com.example.weather;
}
