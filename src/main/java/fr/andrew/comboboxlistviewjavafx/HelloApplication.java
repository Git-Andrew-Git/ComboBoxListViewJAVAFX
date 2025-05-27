package fr.andrew.comboboxlistviewjavafx;

import fr.andrew.comboboxlistviewjavafx.model.Country;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.stage.Stage;



import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();

        FileInputStream imageFileInputStream =
                new FileInputStream(getClass().getResource("media/up_arrow.png").getPath());
        Image image = new Image(imageFileInputStream);
        ImageView imageView = new ImageView(image);

        FileInputStream imageFileInputStream1 =
                new FileInputStream(getClass().getResource("media/down_arrow.png").getPath());
        Image image1 = new Image(imageFileInputStream1);
        ImageView imageView1 = new ImageView(image1);

        GridPane gridPane = new GridPane();

        ObservableList<Country> comboBoxList = FXCollections.observableArrayList(getCountriesList());

        comboBoxList.addListener(new ListChangeListener<Country>() {
            @Override
            public void onChanged(Change<? extends Country> country) {
                System.out.println("Il s'est passé un truc, là.");
            }
        });

        double[] columnWidths = {
                120, // Column 0 - Pays disponibles / ComboBox
                90, // Column 1 - (same as column 0)
                75,  // Column 2 - Buttons (>, >>, <, <<)
                50, // Column 3 - ListView area
                80,  // Column 4 - "Haut" button
                50   // Column 5 - "Bas" button
        };

        for (double width : columnWidths) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setMinWidth(width);
            cc.setPrefWidth(width);
            cc.setMaxWidth(width);
            gridPane.getColumnConstraints().add(cc);
        }

        double[] rowHeights = {
                40,   // Row 0 - Label
                40,   // Row 1 - ComboBox and button
                40,   // Row 2 - Button
                20,   // Row 3 - Empty or spacing
                40,   // Row 4 - Button
                40,   // Row 5 - Button
                35    // Row 6 - Quitter button
        };

        for (double height : rowHeights) {
            RowConstraints rc = new RowConstraints();
            rc.setMinHeight(height);
            rc.setPrefHeight(height);
            rc.setMaxHeight(height);
            gridPane.getRowConstraints().add(rc);
        }
//        gridPane.setGridLinesVisible(true);


        Label label = new Label("Pays disponibles :");
        gridPane.add(label, 0, 0, 2, 1);
        GridPane.setMargin(label, new Insets(10));
        GridPane.setHalignment(label, HPos.LEFT);
        GridPane.setValignment(label, VPos.CENTER);

        ComboBox<Country> comboBox = new ComboBox<>(comboBoxList);
        comboBox.setPromptText("choose your Country");
        comboBox.setMinWidth(200);
        GridPane.setMargin(comboBox, new Insets(10));


        ObservableList<Country> listViewList = FXCollections.observableArrayList();
        ListView<Country> listView = new ListView<>(listViewList);
        gridPane.add(listView, 3,1, 3, 5);

        gridPane.add(comboBox, 0, 1,2,1);
        GridPane.setHalignment(comboBox, HPos.LEFT);
        GridPane.setValignment(comboBox, VPos.CENTER);

        Button button1 = new Button(">");
        gridPane.add(button1, 2, 1);
        button1.setMinWidth(40);
        GridPane.setHalignment(button1, HPos.CENTER);
        GridPane.setValignment(button1, VPos.CENTER);
        button1.setOnAction(actionEvent -> {
            Country country = comboBox.getValue();
            if (country != null) {
                listViewList.add(country);
                comboBoxList.remove(comboBox.getValue());
            }
        });


        Button button2 = new Button(">>");
        gridPane.add(button2, 2, 2);
        button2.setMinWidth(40);
        GridPane.setHalignment(button2, HPos.CENTER);
        GridPane.setValignment(button2, VPos.CENTER);

        Button button3 = new Button("<");
        gridPane.add(button3, 2, 4);
        button3.setMinWidth(40);
        GridPane.setHalignment(button3, HPos.CENTER);
        GridPane.setValignment(button3, VPos.CENTER);
        button3.setOnAction(actionEvent -> {
            Country country = listView.getSelectionModel().getSelectedItem();
            if (country != null) {
                comboBoxList.add(country);
                listViewList.remove(listView.getSelectionModel().getSelectedItem());
            }
        });

        Button button4 = new Button("<<");
        gridPane.add(button4, 2, 5);
        button4.setMinWidth(40);
        GridPane.setHalignment(button4, HPos.CENTER);
        GridPane.setValignment(button4, VPos.CENTER);
        button4.setOnAction(actionEvent -> {
            comboBoxList.addAll(listViewList);
            listViewList.clear();
            if (listViewList.size() == 0) {
                button4.setDisable(true);
            } else {
                button4.setDisable(false);
            }
            if (comboBoxList.size() == 0) {
                button2.setDisable(true);
            } else {
                button2.setDisable(false);
            }
        });
        button2.setOnAction(actionEvent -> {
            listViewList.addAll(comboBoxList);
            comboBoxList.clear();
            if (comboBoxList.size() == 0) {
                button2.setDisable(true);
            } else {
                button2.setDisable(false);
            }
            if (listViewList.size() == 0) {
                button4.setDisable(true);
            } else {
                button4.setDisable(false);
            }        });

        Button button5 = new Button("",imageView);
//        button5.setMinSize(30, 15);
        imageView.setFitHeight(30);
        imageView.setFitHeight(17);
        imageView.setPreserveRatio(true);

        gridPane.add(button5, 4, 0);
        button5.setMinWidth(40);
        GridPane.setHalignment(button5, HPos.RIGHT);
        GridPane.setValignment(button5, VPos.CENTER);
        button5.setOnAction(actionEvent -> {
            Country country = listView.getSelectionModel().getSelectedItem();
            int index = listViewList.indexOf(country);
            if (country != null && index > 0) {
                listViewList.remove(country);
                listViewList.add(index-1, country);
                listView.getSelectionModel().select(index-1);
            }
        });

        Button button6 = new Button("", imageView1);
        gridPane.add(button6, 5, 0);
        imageView1.setFitHeight(30);
        imageView1.setFitHeight(17);
        imageView1.setPreserveRatio(true);
        button6.setMinWidth(40);
        GridPane.setHalignment(button6, HPos.RIGHT);
        GridPane.setValignment(button6, VPos.CENTER);
        button6.setOnAction(actionEvent -> {
            Country country = listView.getSelectionModel().getSelectedItem();
            int index = listViewList.indexOf(country);
            if (country != null && index < listViewList.size()-1) {
                listViewList.remove(country);
                listViewList.add(index+1, country);
                listView.getSelectionModel().select(index+1);
            }
        });

        Button button7 = new Button("Quitter");
        button7.setOnAction(actionEvent -> {
            stage.close();
        });
        gridPane.add(button7, 3, 6, 3, 1);
        GridPane.setHalignment(button7, HPos.RIGHT);
        GridPane.setValignment(button7, VPos.BOTTOM);
        button7.setMinWidth(90);



        BorderPane borderPane = new BorderPane(gridPane);
        BorderPane.setMargin(gridPane, new Insets(8));

        Scene scene = new Scene(borderPane);
        stage.setTitle("Liste payes");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static ArrayList<Country> getCountriesList() {
        ArrayList<Country> countries = new ArrayList<>();

        String[] countryCodes = Locale.getISOCountries();


        for (String countryCode : countryCodes) {
            Locale obj = new Locale("", countryCode);
            countries.add(new Country(obj.getDisplayCountry(), obj.getISO3Country()));
        }

        return countries;
    }

    public static void main(String[] args) {
        launch();
    }
}

