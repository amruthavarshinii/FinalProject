/*
 * Written by Rafael Lopez <lopez.rafael08@gmail.com>, 2016
 */
package javafxtest;

import java.util.Vector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author abism
 */
public class JavaFXTest extends Application {

    private Scene scene;
    private MyBrowser myBrowser;
    //private LocationClickHandler handler;
    private Vector<Button> buttons;
    //private Moderator moderator;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        buttons = new Vector<Button>();
        //moderator = new Moderator();
        //handler = new LocationClickHandler();

        primaryStage.setTitle("Google Maps API Demo");

        myBrowser = new MyBrowser();
        //moderator.registerAddIconListener(myBrowser);
        VBox mainbox = new VBox();
        mainbox.getChildren().add(myBrowser);

        // mainbox.getChildren().add(generateEntitiesButtonBox());
//  mainbox.getChildren().add(generateSensorsButtonBox());
        scene = new Scene(mainbox, 1200, 680);

        primaryStage.setScene(scene);

        primaryStage.show();

    }
}
