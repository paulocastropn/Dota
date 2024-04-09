module dota.dota {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires com.google.gson;
    requires javafx.media;

    opens dota.dota to javafx.fxml;
    exports dota.dota;
    exports dota.dota.controller;

    opens dota.dota.integration.dto to com.google.gson;
    exports dota.dota.integration;
}