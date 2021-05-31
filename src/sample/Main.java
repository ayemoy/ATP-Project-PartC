package sample;

//import View.MenuBarController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
import javafx.scene.Scene;
        import javafx.stage.Stage;

//import javafx.*;


//import View.MenuBarController;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/MainScreen.fxml"));
        //Scene wellcomeScene = new Scene(root);
        primaryStage.setTitle("Hello To SpongeBob World");
        primaryStage.setScene(new Scene(root, 500, 375));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}

