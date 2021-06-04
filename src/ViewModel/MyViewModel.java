package ViewModel;
import Model.IModel;
import Model.MyModel;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MyViewModel {

    private static MyViewModel myViewModel;
    private MyModel model;

    public MyViewModel(MyModel model) {

    }


    public void exit() {
        model.stopServers();
    }


}
