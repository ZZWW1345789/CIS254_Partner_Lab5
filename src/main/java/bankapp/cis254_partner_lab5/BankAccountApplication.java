package bankapp.cis254_partner_lab5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class will load fxml file and show the window
 */
public class BankAccountApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {

        //create fxmlLoader and load fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(BankAccountApplication.class.getResource("BankInterface.fxml"));

        //create and set a new Scene
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        //name the window
        stage.setTitle("Rich Bank");

        //put Scene to window
        stage.setScene(scene);

        //show the window
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}