module fr.andrew.comboboxlistviewjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.andrew.comboboxlistviewjavafx to javafx.fxml;
    exports fr.andrew.comboboxlistviewjavafx;
}