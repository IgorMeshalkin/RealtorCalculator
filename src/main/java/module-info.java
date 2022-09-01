module com.example.realtorcalculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.realtorcalculator to javafx.fxml;
    exports com.example.realtorcalculator;
    exports com.example.realtorcalculator.controller;
    opens com.example.realtorcalculator.controller to javafx.fxml;
}