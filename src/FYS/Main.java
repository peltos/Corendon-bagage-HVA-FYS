package FYS;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.layout.BorderPane;

/**
 * Main application class.
 */
public class Main extends Application {

    private static final Database database = new Database(
        "testDatabase",
        "ronpelt.synology.me:3306",
        "root",
        "kGjMtEO06BPiu2u4"
    );
    
    public static Database getDatabase() {
        return Main.database;
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Corendon Baggage System");
        stage.setScene(
                createScene(
                        loadLogin()
                )
        );

        stage.show();
    }

    /**
     * Loads the main fxml layout. Sets up the vista switching MainNavigator.
     * Loads the first vista into the fxml layout.
     *
     * @return the loaded pane.
     * @throws IOException if the pane could not be loaded.
     */
    public static Pane loadLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(Main.class.getResourceAsStream(MainNavigator.LOGIN));

        return mainPane;
    }

    /**
     * Creates the main application scene.
     *
     * @param mainPane the main application layout.
     * @return the created scene.
     */
    public static Scene createScene(Pane mainPane) {

        Scene scene = new Scene(mainPane);

        scene.getStylesheets().setAll(
                Main.class.getResource("vista.css").toExternalForm()
        );

        return scene;
    }

    /**
     * Loads Panes from the MainNavigator.java
     *
     * @return the loaded pane.
     * @throws IOException if the pane could not be loaded.
     */
    public static Pane loadMedewerker() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(Main.class.getResourceAsStream(MainNavigator.MAINMEDEWERKER));

        FYS.controller.MainController mainController = loader.getController();
        mainController.setRoot((BorderPane) mainPane);

        MainNavigator.setMainController(mainController);
        MainNavigator.loadVista(MainNavigator.START);

        return mainPane;
    }

    public static Pane loadManager() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(Main.class.getResourceAsStream(MainNavigator.MAINMANAGER));

        FYS.controller.MainController mainController = loader.getController();
        mainController.setRoot((BorderPane) mainPane);

        MainNavigator.setMainController(mainController);
        MainNavigator.loadVista(MainNavigator.START);

        return mainPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
