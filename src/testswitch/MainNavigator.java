package testswitch;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * Utility class for controlling navigation between vistas.
 *
 * All methods on the navigator are static to facilitate
 * simple access from anywhere in the application.
 */
public class MainNavigator {

    /**
     * Convenience constants for fxml layouts managed by the navigator.
     */
    
    public static final String LOGIN = "testswitch.fxml.login";
    public static final String MAINMEDEWERKER = "testswitch.fxml.mainMedewerker";
    public static final String MAINMANAGER = "testswitch.fxml.mainManager";
    public static final String START = "testswitch.fxml.start";
    public static final String GEVONDEN = "testswitch.fxml.gevonden";
    public static final String VERMIST = "testswitch.fxml.vermist";
    public static final String OPEN = "testswitch.fxml.start";
    public static final String OVEREENKOMST = "testswitch.fxml.overeenkomst";
    public static final String GESLOTEN = "testswitch.fxml.gesloten";
    public static final String LOGBOEK = "testswitch.fxml.logboek";
    public static final String GEBRUIKER = "testswitch.fxml.gebruiker";
    public static final String GEBRUIKER_TOEVOEGEN = "testswitch.fxml.gebruikerToevoegen";
    public static final String EDIT_GEVONDEN = "testswitch.fxml.editGevonden";
    public static final String EDIT_VERMIST = "testswitch.fxml.editVermist";
    public static final String EDIT_GEBRUIKER = "testswitch.fxml.editGebruiker";
    public static final String WACHTWOORD_WIJZIGEN = "testswitch.fxml.wachtwoordWijzigen";
    
    
    

    /** The main application layout controller. */
    private static testswitch.controller.MainController mainController;

    /**
     * Stores the main controller for later use in navigation tasks.
     *
     * @param mainController the main application layout controller.
     */
    public static void setMainController(testswitch.controller.MainController mainController) {
        MainNavigator.mainController = mainController;
    }

    /**
     * Loads the vista specified by the fxml file into the
     * vistaHolder pane of the main application layout.
     *
     * Previously loaded vista for the same fxml file are not cached.
     * The fxml is loaded anew and a new vista node hierarchy generated
     * every time this method is invoked.
     *
     * A more sophisticated load function could potentially add some
     * enhancements or optimizations, for example:
     *   cache FXMLLoaders
     *   cache loaded vista nodes, so they can be recalled or reused
     *   allow a user to specify vista node reuse or new creation
     *   allow back and forward history like a browser
     *
     * @param fxml the fxml file to be loaded.
     */
    public static void loadVista(String fxml) {
        try {
            mainController.setVista(FXMLLoader.load(MainNavigator.class.getResource(
                        fxml
                    )
                )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}