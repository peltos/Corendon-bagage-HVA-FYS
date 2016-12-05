package testswitch;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Main controller class for the entire layout.
 */
public class MainController {
    
    Database database = Main.getDatabase();

    /**
     * Holder of a switchable vista.
     */
    //@FXML
    //private StackPane vistaHolder;
    @FXML
    private Pane mainPane;

    /**
     * Replaces the vista displayed in the vista holder with a new vista.
     *
     * @param node the vista node to be swapped in.
     */
    public void setVista(Node node) {
        //vistaHolder.getChildren().setAll(node);
        root.setCenter(node);
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }
    
    private Stage stage;
    private BorderPane root;

    @FXML
    private TextField gebruikersnaam;
    @FXML
    private TextField wachtwoord;
    @FXML
    private Button loginButton;
    @FXML
    private Button afmeldenButton;
    @FXML
    private Label error;

    private String dbUsername, dbPassword ;
    private int dbManager;
    
    @FXML
    private Label headerLabel;

    /*
    * Bekijkt of de inlog gegevens goed zijn. geeft error als het fout is
     */
    @FXML
    private void loginCheck(ActionEvent event) throws Exception {
        
        ResultSet result = database.executeQuery("SELECT username, password, "
                + "positie FROM testDatabase.Gebruikers;");
        
        try {

            while (result.next()) {
                dbUsername = result.getString("username");
                dbPassword = result.getString("password");
                dbManager = result.getInt("positie");

                if (dbUsername.equals(gebruikersnaam.getText())
                        && dbPassword.equals(wachtwoord.getText())) {
                    if (dbManager == 1) {
                        stage = (Stage) loginButton.getScene().getWindow();
                        
                        stage.setScene(
                                Main.createScene(
                                    Main.loadManager()
                                )
                        );
                        stage.centerOnScreen();
                        
                        return;
                    }
                    stage = (Stage) loginButton.getScene().getWindow();
                    stage.setScene(
                            Main.createScene(
                                    Main.loadMedewerker()
                            )
                    );
                    stage.centerOnScreen();
                    return;
                }
                error.setText("Wrong username or password.");
                
            }
        } catch (SQLException ex) {

        }
        wachtwoord.clear();
    }

    @FXML
    private void afmelden(ActionEvent event) throws IOException {
        Main Main = new Main();
        stage = (Stage) afmeldenButton.getScene().getWindow();

        stage.setScene(
                Main.createScene(
                        Main.loadLogin()
                )
        );
        stage.centerOnScreen();
    }

    @FXML
    private void gevonden(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.GEVONDEN);
        headerLabel.setText("Gevonden");
    }

    @FXML
    private void vermist(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.VERMIST);
        headerLabel.setText("Vermiste");
    }

    @FXML
    private void open(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.OPEN);
        headerLabel.setText("Open");
    }

    @FXML
    private void overeenkomst(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.OVEREENKOMST);
        headerLabel.setText("Overeenkomst");
    }

    @FXML
    private void gesloten(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.GESLOTEN);
        headerLabel.setText("Gesloten");
    }

    @FXML
    private void start(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.START);
        headerLabel.setText("Open");
    }

    @FXML
    private void gebruikerToevoegen(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.GEBRUIKER_TOEVOEGEN);
        headerLabel.setText("Gebruiker Toevoegen");
    }

    @FXML
    private void gebruiker(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.GEBRUIKER);
        headerLabel.setText("Gebruiker");
    }

    @FXML
    private void logboek(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.LOGBOEK);
        headerLabel.setText("Logboek");
    }
    @FXML
    private void wachtwoordWijzigen(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.WACHTWOORD_WIJZIGEN);
        headerLabel.setText("Wachtwoord Wijzigen");
        
    }

}
