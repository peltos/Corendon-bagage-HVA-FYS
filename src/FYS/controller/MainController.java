package FYS.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import FYS.Database;
import FYS.MD5;
import FYS.Main;
import FYS.MainNavigator;

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

    private static String dbUsername, dbPassword, MD5Password ;
    private static int dbID, dbManager;
    
    @FXML
    private Label headerLabel;

    /*
    * Bekijkt of de inlog gegevens goed zijn. geeft error als het fout is
     */
    @FXML
    private void loginCheck(ActionEvent event) throws Exception {
        
        ResultSet result = database.executeQuery("SELECT ID, username, password, "
                + "positie FROM testDatabase.Gebruikers;");
        try {

            while (result.next()) {
                dbID = result.getInt("ID");
                dbUsername = result.getString("username");
                dbPassword = result.getString("password");
                dbManager = result.getInt("positie");
                
                String[] MD5 = {wachtwoord.getText()};
                MD5 encryptie = new MD5();
                
                MD5Password = encryptie.MD5(MD5);

                if (dbUsername.equals(gebruikersnaam.getText())
                        && dbPassword.equals(MD5Password)) {
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
    public static int getID() {
        return dbID;
    }
    public String getPassword() {
        return dbPassword;
    }

    public void setPassword(String dbPassword) {
        MainController.dbPassword = dbPassword;
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
        headerLabel.setText("Add Found");
    }

    @FXML
    private void vermist(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.VERMIST);
        headerLabel.setText("Add Lost");
    }

    @FXML
    private void open(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.OPEN);
        headerLabel.setText("Unsolved");
    }

    @FXML
    private void overeenkomst(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.OVEREENKOMST);
        headerLabel.setText("Matches");
    }

    @FXML
    private void gesloten(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.GESLOTEN);
        headerLabel.setText("Closed");
    }

    @FXML
    private void start(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.START);
        headerLabel.setText("Unsolved");
    }

    @FXML
    private void gebruikerToevoegen(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.GEBRUIKER_TOEVOEGEN);
        headerLabel.setText("Add User");
    }

    @FXML
    private void gebruiker(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.GEBRUIKER);
        headerLabel.setText("User");
    }

    @FXML
    private void logboek(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.LOGBOEK);
        headerLabel.setText("History");
    }
    @FXML
    private void wachtwoordWijzigen(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.WACHTWOORD_WIJZIGEN);
        headerLabel.setText("Change Password");
        
    }

}
