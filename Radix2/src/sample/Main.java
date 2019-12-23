package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Radix.fxml"));
        primaryStage.setTitle("Radix");
        Scene scene = new Scene(root, 384, 608);
        scene.getStylesheets().add(getClass().getResource("breeze.css").toExternalForm());
        primaryStage.setScene(scene);
        //primaryStage.setScene(new Scene(root, 384, 608));

        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
