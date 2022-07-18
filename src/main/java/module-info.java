module com.example.shopping_system {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
requires java.sql;

    opens com.example.shopping_system to javafx.fxml;
    exports com.example.shopping_system;
    exports com.example.shopping_system.TakeOrder_classes;
    opens com.example.shopping_system.TakeOrder_classes to javafx.fxml;
}