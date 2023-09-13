package co.edu.uptc.test;
import co.edu.uptc.controller.old.LoginController;
import co.edu.uptc.controller.old.SuggestionController;
import co.edu.uptc.model.Account;
import co.edu.uptc.model.Suggestion;
import co.edu.uptc.utilities.InputLibrary;

import java.util.List;
import java.util.Scanner;

/**
 * The SuggestionTest class represents a test class that simulates the interaction with the application
 * for managing student suggestions. It provides methods to perform actions such as logging in, making suggestions,
 * changing passwords, and viewing account information and suggestions. The test class interacts with instances of
 * LoginController, LoginView, SuggestionController, Account, and other utility classes to simulate the application flow.
 * The purpose of this class is to demonstrate the functionality of the suggestion management system and test various
 * scenarios.
 *
 * @author Diego Combariza
 * @version 1.0.0
 */

public class SuggestionTest {
    private LoginController loginController;
    private LoginView loginView;
    final InputLibrary util;
    private final String errorMessage;
    SuggestionController sC;
    Scanner sc = new Scanner(System.in);

    /**
     * Constructs a new SuggestionTest object. It initializes instances of LoginController, LoginView,
     * and SuggestionController for testing purposes. It also initializes utility classes and sets the error message.
     */
    public SuggestionTest() {
        loginController = new LoginController();
        loginView = new LoginView();
        sC = new SuggestionController();
        this.util = new InputLibrary();
        this.errorMessage = "Invalid input. Try again";
    }
    public static void main(String[] args) {
        SuggestionTest mySt = new SuggestionTest();
        mySt.principal();
    }
    //Copia de ingresar datos
    public void principal(){
        int decision;
        String message = """
                ============================= 
                |\t\t\tUPTC\t\t\t|
                |\t\t1.Login\t\t\t\t|
                |\t\t2.Exit\t\t\t\t|
                =============================""";
        String password = "",userName = "";
        do {
            System.out.println(message);
            decision = util.inputInt("Selection --------> ", "Invalid input. Try again");
            switch (decision) {
                case 1 :
                    userName = util.inputString("\tUsername: ", "Invalid Input. Try again");
                    password = util.inputString("\tPassword: ", "Invalid input. Try again");
                    if (loginController.login(userName, password)) {
                        loginS();
                    } else {
                        System.out.println("User name or password incorrect");
                    }
                    break;
                case 2 :
                    System.out.println("Coming out....");
                    break;
                default:
                    System.out.println("Invalid option. Try again");
            }
        }while (decision!=2);
    }
    /**
     * This method shows the options after login
     */
    public void loginS(){
        System.out.println(this.loginController.showInfoLoggedAcount());
        String role = loginController.showRol();
        if (role.equals("ADMINISTRATOR")) {
            this.optionsGeneralsLogin();
            return;
        }else if(role.equals(("STUDENT"))){
            this.optionsStudenT();
            return;
        }
        this.optionsBasic();
    }
    /**
     * This method shows the option for Student to make a suggestion
     */
    public  void optionsStudenT(){
        String message = """
                ========================
                |\t1.change password\t|
                |\t2.logout\t\t\t|
                |\t3.make a suggestion\t|
                ========================""";
        int decision = 0;
        do{
            System.out.println(message);
            decision = this.util.inputInt("Selection -----> ", this.errorMessage);
            switch (decision){
                case 1:
                    System.out.println(loginView.changePassword() ? "password change was successful": "Error");
                    break;
                case 2:
                    System.out.println(loginView.logOut() ? "Logging out" : "Error. Try again");
                    break;
                case 3:
                    System.out.println("Creating a suggestion...");
                    System.out.println("Please input the date");
                    String date = sc.next();
                    sc.nextLine();
                    System.out.println("Please input the suggestion");
                    String suggestion = sc.nextLine();
                    Account currentAccount = loginController.getCurrentAccount();
                    sC.createSuggestion(suggestion, date,currentAccount);
                    break;
                default:
                    System.out.println("Invalid option, try again.");
                    break;
            }
        }while(decision!=2);
    }
    /**
     * This method shows the option for Administrator to show all accounts, register new accounts and show suggestions
     */
    public  void optionsGeneralsLogin(){
        String loginMessage = """
                ========================
                |\t1.Sing in accounts\t|
                |\t2.see accounts\t\t|
                |\t3.change password\t|
                |\t4.show suggestions\t|
                |\t5.logout\t\t\t|
                ========================""";
        int decision,role;
        do{
            System.out.println(loginMessage);
            decision = this.util.inputInt("Selection -----> ", this.errorMessage);
            switch (decision){
                case 1 :
                    System.out.println(loginView.singInAccounts() ?  "you successfully registered\n" +
                            "your user name is: "+loginController.getUserName()+"\n your password is: "+loginController.getPassword(): "Error with your data");
                    break;
                case 2:
                    System.out.println(loginController.showAccounts());
                    break;
                case 3:
                    System.out.println(loginView.changePassword() ? "password change was successful":"Error");
                    break;
                case 4:
                    System.out.println("Suggestions");
                    System.out.println(sC.getSuggestions().toString()+ "\n");
                    List<Suggestion> suggestions = sC.getSuggestions();
                    for (Suggestion suggestion : suggestions) {
                        System.out.println(suggestion.toString());
                    }
                    System.out.println();
                    break;
                case 5:
                    System.out.println(loginView.logOut() ? "Logging out" : "Error. Try again");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
                    break;
            }
        }while(decision!=5);
    }
    /**
     * This method shows the basic options for people who do not have special permissions
     */
    public  void optionsBasic(){
        String message = """
                ========================
                |\t1.change password\t|
                |\t2.logout\t\t\t|
                ========================""";
        int decision = 0;
        do{
            System.out.println(message);
            decision = this.util.inputInt("Selection -----> ", this.errorMessage);
            switch (decision){
                case 1:
                    System.out.println(loginView.changePassword() ? "password change was successful": "Error");
                    break;
                case 2:
                    System.out.println(loginView.logOut() ? "Logging out" : "Error. Try again");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
                    break;
            }
        }while(decision!=2);
    }
}