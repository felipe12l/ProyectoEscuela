
package co.edu.uptc.test;

import co.edu.uptc.controller.CovenantController;
import co.edu.uptc.controller.LoginController;
import co.edu.uptc.utilities.InputLibrary;

/**
 * This class is to show the menu with its functions so
 * that it can be instantiated without having to make changes in the logic.
 * @Author Edwin Martinez
 * @Author Samuel Gonzalez
 * @Author Nicolas Sarmiento
 */
public class LoginView {

    final InputLibrary util;
    final LoginController loginController;
    private final String[] roles;
    private final String errorMessage;
     final CovenantController cc;

    public LoginView(){
        cc=new CovenantController();
        this.util = new InputLibrary();
        this.loginController = new LoginController();
        this.roles = new String[]{"Student", "Professor", "Secretary", "Administrator"};
        this.errorMessage = "Invalid input. Try again";
    }
    /**
     *This is the main method that shows the menu
     */
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        loginView.principal();
    }
        public void principal(){
        LoginView portal = new LoginView();
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
            decision = portal.util.inputInt("Selection --------> ", "Invalid input. Try again");
            switch (decision) {
                case 1 :
                    userName = portal.util.inputString("\tUsername: ", "Invalid Input. Try again");
                    password = portal.util.inputString("\tPassword: ", "Invalid input. Try again");
                    if (portal.loginController.login(userName, password)) {
                        portal.login();
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
    public void login(){
        System.out.println(this.loginController.showInfoLoggedAcount());
        String role = loginController.showRol();

        if (role.equals("ADMINISTRATOR")) {
            this.optionsGeneralsLogin();
            return;
        }

        this.optionsBasic();
    }

    /**
     * This method shows the option for Administrator to show all accounts and register new accounts
     */
    public  void optionsGeneralsLogin(){
        String loginMessage = """
                ========================
                |\t1.Sing in accounts\t|
                |\t2.see accounts\t\t|
                |\t3.change password\t|
                |\t4.add agreements\t|
                |\t5.Show agreements\t|
                |\t0.logout\t\t\t|
                ========================""";
        int decision,role;
        do{
            System.out.println(loginMessage);
            decision = this.util.inputInt("Selection -----> ", this.errorMessage);
            switch (decision){
                case 1 :
                    System.out.println(this.singInAccounts() ?  "you successfully registered\n" +
                            "your user name is: "+loginController.getUserName()+"\n your password is: "+loginController.getPassword(): "Error with your data");
                break;
                case 2:
                    System.out.println(loginController.showAccounts());
                break;
                case 3:
                    System.out.println(this.changePassword() ? "password change was successful":"Error");
                break;
                case 4:
                    int option=9;
                    System.out.println("Show categories"+cc.showDataCategoryNames()+"\nSelect: " );
                    option=this.util.inputInt("Selection --------> ", "Invalid input. Try again",0,6);
                    System.out.println("Input name of the covenant");
                    String r=this.util.inputStringWithS("Input: ");
                    System.out.println("Input contact");
                    String r1=this.util.inputStringWithS("Input: ");
                    System.out.println("Input name of the contact");
                    String r2=this.util.inputStringWithS("Input: ");
                    System.out.println("Input description");
                    String r3=this.util.inputStringWithS("Input: ");
                    System.out.println("Input link");
                    String r4=this.util.inputStringWithS("Input: ");
                    cc.addCovenant(r,r1,r2,r3,r4,option);
                    System.out.println("agreement added");
                    break;
                case 5:
                    System.out.println("Covenants"+cc.showCovenant());
                    break;
                case 0:
                    System.out.println(this.logOut() ? "Logging out" : "Error. Try again");
                break;
                default:
                    System.out.println("Invalid option, try again.");
                break;
            }
        }while(decision!=0);
    }

    public boolean singInAccounts(){
        String names = "", lastNames = "", id = "";
        int roleIndex = 0;

        roleIndex = this.util.inputInt("\tType your role in the university\n\t1.Student\n\t2.Professor\n\t3.Secretary\n\t-> ", errorMessage, 1,3);
        names = this.util.inputString("\tType their names: ", this.errorMessage);
        lastNames = this.util.inputString("\tType your last name: ", errorMessage);
        id = this.util.inputString("\tType your identification: ", errorMessage);

        return loginController.signin(names,lastNames,id,this.roles[roleIndex-1]);
    }

    public boolean changePassword(){
        String oldPassword = "", newPassword = "";
        oldPassword = this.util.inputString("\tType old password: ", errorMessage);
        newPassword = this.util.inputString("\t Your password will include 2 Uppercase characters, 2 Lowercase characters, 2 numbers. At least 6 characters.\n\tType new password: ", errorMessage);
        return this.loginController.changePassword(oldPassword, newPassword);
    }

    public boolean logOut(){
        return this.loginController.logout();
    }


    /**
     * This method shows the basic options for people who do not have special permissions
     */
    public  void optionsBasic(){
        String message = """
                ========================
                |\t1.change password\t|
                |\t2.Show Covenants\t|
                |\t0.logout\t\t\t|
                ========================""";

        int decision = 0;
        do{
            System.out.println(message);
            decision = this.util.inputInt("Selection -----> ", this.errorMessage);
            switch (decision){
                case 1:
                    System.out.println(this.changePassword() ? "password change was successful": "Error");
                break;
                case 2:
                    System.out.println("Show Covenants"+cc.showCovenant()+
                            "\n---------------------------------------" +
                            "\n"+cc.showAllC());
                    break;
                case 0:
                    System.out.println(this.logOut() ? "Logging out" : "Error. Try again");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
                break;
            }

        }while(decision!=0);
    }
}
