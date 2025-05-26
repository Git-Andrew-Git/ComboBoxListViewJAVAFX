package fr.andrew.comboboxlistviewjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();

        GridPane gridPane = new GridPane();

        Label label = new Label("Pays disponibles :");
        gridPane.add(label, 0, 0);

        ComboBox comboBox = new ComboBox<>();
        gridPane.add(comboBox, 0, 1);

        Button button1 = new Button(">");
        gridPane.add(button1, 1, 1);
        Button button2 = new Button(">>");
        gridPane.add(button2, 1, 2);
        Button button3 = new Button("<");
        gridPane.add(button3, 1, 4);
        Button button4 = new Button("<<");
        gridPane.add(button4, 1, 5);
        Button button5 = new Button("Haut");
        gridPane.add(button5, 3, 0);
        Button button6 = new Button("Bas");
        gridPane.add(button6, 4, 0);
        Button button7 = new Button("Quitter");
        gridPane.add(button7, 3, 6, 1, 1);

        Scene scene = new Scene(gridPane);
        stage.setTitle("Liste payes");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}