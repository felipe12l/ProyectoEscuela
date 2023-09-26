package co.edu.uptc.view;

import co.edu.uptc.controller.CovenantController;
import co.edu.uptc.controller.LoginController;
import co.edu.uptc.controller.SuggestionController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * This class represents the main application view for the login functionality.
 * It manages the login panel, dashboard, sign-in view, and other related views.
 */
public class LoginView extends Application implements EventHandler<ActionEvent> {

    LoginPanel loginPanel;
    LoginDashBoard loginDashBoard;
    SingInView singInView;
    Stage stage;
    LoginController controller;
    CovenantController covenantController;
    LoginListUsers loginListUsers;
    ManageCovenants manageCovenants;
    SuggestionController suggestionController;
    ManageSuggestion manageSuggestion;
    ListCovenants listCovenants;
    SuggestionsView suggestionsView;
    ChangePasswordOptionConfig changePassword;
    Button home;
    /**
     * Constructs a new LoginView instance and initializes its components.
     */
    public LoginView(){

        this.controller = new LoginController();
        this.covenantController = new CovenantController();
        this.suggestionController = new SuggestionController();
        this.loginPanel = new LoginPanel(this);
        this.home = new Button();
        this.home.setOnAction(this);
        this.singInView = new SingInView(this,this.home);
        this.loginListUsers = new LoginListUsers(this, home);
        this.changePassword = new ChangePasswordOptionConfig(this,home);
        this.loginDashBoard = new LoginDashBoard(this);
        this.manageCovenants = new ManageCovenants(this, this.home);
        this.listCovenants = new ListCovenants(this,this.home);
        this.manageSuggestion = new ManageSuggestion(this, this.home);
        this.suggestionsView=new SuggestionsView(this,this.home);
    }
    /**
     * The entry point for the JavaFX application.
     *
     * @param stage The primary stage for this application.
     * @throws Exception If an exception occurs during application start.
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.stage.setScene(this.loginPanel.login());
        //this.stage.setScene(this.singInView.singIn());
        this.stage.setTitle("Login UPTC");
        this.stage.show();
    }
    /**
     * The main method to launch the JavaFX application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * Sets the scene for the primary stage.
     *
     * @param sc The Scene object to set as the scene for the stage.
     * */
    public void setScene(Scene sc){
        this.stage.setScene(sc);
   }
    /**
     * Handles various events triggered within the application.
     *
     * @param e The ActionEvent representing the event.
     */
    @Override
    public void handle(ActionEvent e) {

        if (e.getSource() == this.loginPanel.getBtn()){

            boolean response = this.controller.login(this.loginPanel.getUsername().getText(), this.loginPanel.getPassword().getText());
            if (response){

                this.stage.setTitle("Dashboard UPTC");
                this.setScene(loginDashBoard.dashBoard());
            }else {
                this.loginPanel.getUsername().setText("");
                this.loginPanel.getPassword().setText("");
                this.loginPanel.setMessage("Invalid user or password login");
                this.loginPanel.setVisibleErrorMessage(true);
            }
        }
        if (e.getSource() == this.changePassword.getConfirmButton()){
            String old = changePassword.getOldPasswordField().getText();
            String newPassword = "";
            if(old.equals(controller.getLoggedPerson().getAccount().getPassword())){
                newPassword = changePassword.getNewPasswordField().getText();
                if(newPassword.equals(changePassword.getNewPasswordFieldSecond().getText())){
                    if(controller.changePassword(old, newPassword)){
                        changePassword.setNewPasswordField("");
                        changePassword.setNewPasswordFieldSecond("");
                        changePassword.setOldPasswordField("");
                        changePassword.passwordChangeSuccesfully();
                    }
                }
            }else{
                this.changePassword.getIncorrectPassword().setVisible(true);
            }
        }

        if (e.getSource() == this.loginDashBoard.btnOption1){
            this.stage.setTitle("Crear Cuentas");
            this.setScene(this.singInView.singIn());
        }

        if(e.getSource() == this.loginDashBoard.btnOption2){
            this.stage.setTitle("Cuentas");
            this.stage.setScene(loginListUsers.loginListUsers());
        }

        if(e.getSource() == this.loginDashBoard.btnOption5){
            this.stage.setTitle("Manage Convenios");
            this.stage.setScene(manageCovenants.loginListCovenants());
        }
        if (e.getSource()==this.loginDashBoard.btnOption7){
            this.stage.setTitle("Read Sugerencias");
            this.stage.setScene(manageSuggestion.ListSuggestions());
        }
        if(e.getSource() == this.loginDashBoard.btnOption6){
            this.stage.setTitle("ver convenios");
            this.stage.setScene(listCovenants.loginListCovenants());
        }

        if(e.getSource() == this.loginDashBoard.btnOption3){
            this.stage.setTitle("Cambiar contraseña");
            this.stage.setScene(changePassword.settingInfoContainer());
        }

        if(e.getSource() == this.loginDashBoard.btnOption4){
            this.stage.setTitle("Login UPTC");
            this.stage.setScene(loginPanel.login());
        }

        if(e.getSource() == this.home){
            this.stage.setScene(loginDashBoard.dashBoard());
        }
        if(e.getSource()==this.loginDashBoard.btnOption8){
            this.stage.setTitle("Haciendo Sugerencias");

            this.stage.setScene(suggestionsView.View());
        }
    }
}
