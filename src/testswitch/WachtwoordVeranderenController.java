package testswitch;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Daan
 */
public class WachtwoordVeranderenController implements Initializable {
    
    @FXML TextField FXOudWachtwoord, FXNieuwWachtwoord, FXBevestigWachtwoord;
    @FXML Button gevondenButton;
    @FXML Label error;
    
    Database database = Main.getDatabase();
    MainController controller = new MainController();
    
    
    
    @FXML
    private void writeToDB() throws SQLException {
        
        String query = "UPDATE testDatabase.Gebruikers SET "
                + "Password=? "
                + "WHERE ID=" + MainController.getID() + ";";

        PreparedStatement statement = database.prepareStatement(query);
        
        if ((FXOudWachtwoord.getText().equals("")) && (FXNieuwWachtwoord.getText().equals("")) && (FXBevestigWachtwoord.getText().equals(""))) {
            error.setText("You left one or more textfields empty");
        }else{
            if (!FXOudWachtwoord.getText().equals(controller.getPassword())){
                error.setText("Old password is wrong");
            }
            else if ((FXNieuwWachtwoord.getText().equals(""))){
                error.setText("You left one or more textfields empty");
            }
            else if ((!FXNieuwWachtwoord.getText().equals(FXBevestigWachtwoord.getText()))){
                error.setText("Your new passwords arent the same");
            }
            else if ((FXOudWachtwoord.getText().equals(FXNieuwWachtwoord.getText()))){
                error.setText("You can't use the same password again for your new password");
            }
            else{
                try {
                    statement.setString(1, FXNieuwWachtwoord.getText());
                    statement.executeUpdate();
                    MainNavigator.loadVista(MainNavigator.START);
                    
                    controller.setPassword(FXNieuwWachtwoord.getText());
                    System.out.println(controller.getPassword());
                    
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        
    }
    
    @FXML
    private void cancel() {
        MainNavigator.loadVista(MainNavigator.START);
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
}
