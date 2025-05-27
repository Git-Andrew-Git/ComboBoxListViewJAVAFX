package fr.andrew.comboboxlistviewjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class UpDownListView<T> extends GridPane {
    private Button upBtn;
    private Button downBtn;
    private ListView<T> listView;

    private ObservableList<T> observableList;

    public UpDownListView() throws FileNotFoundException {
        this.observableList = FXCollections.observableArrayList();
        this.listView = new ListView<>();
        listView.setItems(observableList);
        FileInputStream imageFileInputStream =
                new FileInputStream(getClass().getResource("media/up_arrow.png").getPath());
        Image image = new Image(imageFileInputStream);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(30);
        imageView.setFitHeight(17);
        imageView.setPreserveRatio(true);

        FileInputStream imageFileInputStream1 =
                new FileInputStream(getClass().getResource("media/down_arrow.png").getPath());
        Image image1 = new Image(imageFileInputStream1);
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitWidth(30);
        imageView1.setFitHeight(17);
        imageView1.setPreserveRatio(true);

        this.upBtn = new Button("", imageView);
        this.downBtn = new Button("", imageView1);
        this.listView = new ListView<T>();



    }

    @Override
    public String toString() {
        return "UpDownListView{" +
                "upBtn=" + upBtn +
                ", downBtn=" + downBtn +
                ", listView=" + listView +
                ", observableList=" + observableList +
                '}';
    }

    public Button getUpBtn() {
        return upBtn;
    }

    public void setUpBtn(Button upBtn) {
        this.upBtn = upBtn;
    }

    public Button getDownBtn() {
        return downBtn;
    }

    public void setDownBtn(Button downBtn) {
        this.downBtn = downBtn;
    }

    public ListView<T> getListView() {
        return listView;
    }

    public void setListView(ListView<T> listView) {
        this.listView = listView;
    }

    public ObservableList<T> getObservableList() {
        return observableList;
    }

    public void setObservableList(ObservableList<T> observableList) {
        this.observableList=observableList;
        listView.setItems(this.observableList);
    }
}
