package testswitch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application class.
 */
public class Main extends Application {
    
    
    
    @Override
    public void start(Stage stage) throws Exception{
        
        stage.setTitle("Corendon Bagage Systeem");
        stage.setScene(
            createScene(
                loadLogin()
            )
        );

        stage.show();
    }

    /**
     * Loads the main fxml layout.
     * Sets up the vista switching MainNavigator.
     * Loads the first vista into the fxml layout.
     *
     * @return the loaded pane.
     * @throws IOException if the pane could not be loaded.
     */
    public Pane loadLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(MainNavigator.LOGIN));

        return mainPane;
    }

    /**
     * Creates the main application scene.
     *
     * @param mainPane the main application layout.
     * @return the created scene.
     */
    public Scene createScene(Pane mainPane) {
        
        Scene scene = new Scene(mainPane);

        scene.getStylesheets().setAll(
            getClass().getResource("vista.css").toExternalForm()
        );

        return scene;
    }
    /**
     * Loads Panes from the MainNavigator.java
     *
     * @return the loaded pane.
     * @throws IOException if the pane could not be loaded.
     */
    public Pane loadMedewerker() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(MainNavigator.MAINMEDEWERKER));

        MainController mainController = loader.getController();

        MainNavigator.setMainController(mainController);
        MainNavigator.loadVista(MainNavigator.START);

        return mainPane;
    }
    public Pane loadManager() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(MainNavigator.MAINMANAGER));

        MainController mainController = loader.getController();

        MainNavigator.setMainController(mainController);
        MainNavigator.loadVista(MainNavigator.START);

        return mainPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
