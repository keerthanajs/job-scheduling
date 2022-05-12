module org.rec.os {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.rec.os.ui to javafx.fxml;
    exports org.rec.os.ui;
}