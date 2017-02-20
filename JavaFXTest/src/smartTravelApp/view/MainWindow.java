/*
 * Written by Rafael Lopez <lopez.rafael08@gmail.com>, 2016
 */
package smartTravelApp.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Rafael Lopez
 */
public class MainWindow extends Application {

    private Scene scene;
    private MyBrowser myBrowser;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Google Maps API Demo");
        myBrowser = new MyBrowser();
        
        VBox mainbox = new VBox();
        mainbox.getChildren().add(myBrowser);
        
        scene = new Scene(mainbox, 1200, 680);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }    
}
