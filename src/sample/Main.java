package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//import javafx.*;
import java.lang.module.FindException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import Model.IModel;
import Model.MyModel;
import View.MyViewController;
//import View.MenuBarController;
import ViewModel.MyViewModel;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/MyView.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

/*

package Observable_Example;

public class Main {
    public static void main(String[] args) {
        A a = new A();
        B b = new B(a, null, null);
        // inherited from Observable
        // add b to a's list of Observers
        a.addObserver(b);

        a.setXY(5, 5);
    }
}
*/