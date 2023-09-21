package co.edu.uptc.controller;
import co.edu.uptc.model.Account;
import co.edu.uptc.model.Person;
import co.edu.uptc.model.persontypes.Administrator;
import co.edu.uptc.model.persontypes.Professor;
import co.edu.uptc.model.persontypes.Student;
import co.edu.uptc.utilities.JsonStorageUtilities;
import java.util.ArrayList;
import java.util.List;


/**
     * The loginController class is responsible for
     * verifying the data entered, validates and saves
     * the user in the loggedAcount attribute.
     * and the person that the account belongs
     * on the atribute loggedPerson.
     * @author Samuel Gonzalez Zambrano
     * @version  1.1
     * 
 */
public class LoginController {

    private PersonController personController;
    private JsonStorageUtilities jsonStorageUtilities;
    Account loggedAcount;
    Person loggedPerson;
    AccountController acc;

    public LoginController(){
        this.loggedAcount = new Account();
        this.loggedPerson = new Person();
        this.jsonStorageUtilities = new JsonStorageUtilities();
        this.personController = new PersonController(jsonStorageUtilities);
        this.acc = new AccountController(jsonStorageUtilities);
        this.personController.loadPeople();
        this.acc.loadAccounts(this.personController.getAccounts());
    }

    /**
     * Main method, check the name and count if it matches
     * any user, it brings it and saves it in loggedAcount.
     * also 
     * @param nameUser,password both entered by user
     * @return If the credentials entered are valid,
     * save the user and return true.
     */
    public  boolean login(String nameUser, String password){

        if(acc.findAccount(nameUser, password) != null){
            loggedAcount = acc.findAccount(nameUser, password);
            loggedPerson = personController.findPersonById(loggedAcount.getId());
            return true;
        }
        return false;
    }

    /**
     * Allows the user who entered to change their password, only allows
     * it if they are already logged in. You must enter the first password
     * and then there if you proceed to the change if you enter the original
     * password wrong, the change is canceled
     * @param oldpassword,newpassword both entered by user
     * @return If the credentials entered are valid, change the password.
    */
    public boolean changePassword(String oldpassword, String newPassword){
        try {

            if(oldpassword.equals(newPassword)){

                return false;

            } else if(loggedAcount != null && loggedAcount.getPassword().equals(oldpassword)){

                boolean methodAnswer = acc.setNewPassword(loggedAcount.getUserName(),oldpassword, newPassword );

                if (methodAnswer){
                    loggedAcount = acc.findAccount(loggedAcount.getUserName(), newPassword);
                    this.personController.assingAccount(loggedPerson.getId(), this.loggedAcount);
                    this.personController.updateInformationFile();
                    this.acc.updateInformationFile();
                }
                return methodAnswer;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Log out, therefore the loggedAcount attribute becomes null
     * @return boolean.
    * 
 */

    public boolean logout(){
        if(loggedAcount != null){
            loggedAcount = null;
            return true;
        }
        return false;
    }

    /**
     * User registration, requires all the base elements,
     * then they are validated, creates the people
     * @param name,lastname user's full name
     * @param id user document
     * @param role Specify the role of the user to create
     * @return control boolean, if something fails the process is canceled.
    * 
 */
    public boolean signin(String name, String lastName, String id, String role){
       if(personController.addPerson(id, name, lastName, role)){
            Person person = personController.findPersonById(id);
            if (acc.addAccount(person.getId(), person.getName(), person.getLastname(), role)) {
                Account account = acc.findAccount(acc.getUsername(), acc.getPassword());
                personController.assingAccount(person.getId(), account);
                this.personController.updateInformationFile();
                return true;
            }
       }
        return false;
    }
      /**
       * getUserName returns the generated user
       * @return String with generated user
       */
    public String getUserName(){
        return acc.getUsername();
    }
    /**
     * getPassword returns the generate password
     * @return String with password
     */
    public String getPassword(){
        return acc.getPassword();
    }

    public String getName(){
        return this.loggedPerson.getName();
    }
    /**
     * Method to view the information of the account that is logged in
     * @return String with all the account information.
    * 
 */

    public String showInfoLoggedAcount(){
        if(loggedAcount != null){
            return loggedAcount.toString();
        }
        return "No ha inciado sesion";
    }
    
    /**
     *Method to view Role of person logging on
     * @return String the role of the account.
    * 
    */

    public String showRol(){
        if(loggedAcount != null){
            return loggedAcount.getRole();
        }
        return "No ha inciado sesion";
    }

      /**
       * This method displays all existing accounts so far
       * @return a String with all the accounts
       */

    public String showAccounts(){
        return acc.showAccounts();
    }

    public Person getLoggedPerson(){return this.loggedPerson;}

    public PersonController getPersonController() {
        return personController;
    }
}
