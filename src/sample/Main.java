package sample;

//import View.MenuBarController;
import Model.IModel;
import Model.MyModel;
import View.Controller;
import ViewModel.MyViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

//import javafx.*;


//import View.MenuBarController;


public class Main extends Application {
/*
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/MainScreen.fxml"));
        //Scene wellcomeScene = new Scene(root);
        primaryStage.setTitle("Hello To SpongeBob World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
*/


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/MainScreen.fxml"));
        Parent root = fxmlLoader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));

        IModel model = MyModel.getInstance();
        MyViewModel viewModel = MyViewModel.getInstance();
        model.addObserver(viewModel);
        Controller welcomeController = fxmlLoader.getController();
        viewModel.addObserver(welcomeController);

//        Rectangle2D openingScene = Screen.getPrimary().getBounds();
        primaryStage.setTitle("Welcome to SpongeBob's world !");
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.setResizable(true);
        primaryStage.show();
        setStageCloseEvent(primaryStage, (MyModel) model);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void setStageCloseEvent(Stage primaryStage, MyModel model) {
        primaryStage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) { //want to exit the game
                model.stopServers();
                primaryStage.close();
            } else //doesn't want to exit the game
                event.consume();
        });
    }

}

