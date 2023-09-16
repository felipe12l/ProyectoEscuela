package co.edu.uptc.controller;

import co.edu.uptc.controller.FileManagerController;
import co.edu.uptc.model.Account;
import co.edu.uptc.model.Covenant;
import co.edu.uptc.utilities.AccountUtilities;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * The Account Controller class allows to manage a collection of accounts
 * that will be available in the execution of the program, as well as
 * the creation, deletion and editing of passwords.
 * @author Nicolas Sarmiento : Nicolas-Sarmiento
 * @version  1.0.0
 */

public class AccountController {
    private FileManagerController fmc;
    private AccountUtilities utility;
    private Gson gson;
    private static final String fileName = "accounts";
    private String username = "";
    private String password = "";



    public AccountController(){
        this.utility = new AccountUtilities();
        this.fmc=new FileManagerController();
        gson = new Gson();
    }

    public void loadAdminAccount(){
        fmc.writeJsonFile(fileName, new Account("16585938","laura.castillo","jaklsBJ832","ADMINISTRATOR","laura.castillo@uptc.edu.co"), false);
    }

    /**
     * The AddAccount method allows the creation and addition
     * of an account to the collection of accounts, validating the input parameters.
     * @param id institution member code
     * @param name Institution member names
     * @param lastName Last name of the member of the institution
     * @param role Role of the member in the institution
     * @return true if the account was added successfully, false if it already exists or the parameters are invalid
     */
    public boolean addAccount(String id, String name, String lastName, String role){
        String email = "";
        String content = fmc.read(fileName);
        ArrayList<Account> accounts = new ArrayList<>(Arrays.asList(gson.fromJson(content,Account[].class)));

        id = id.toLowerCase();
        name = this.utility.cleanNames(name);
        lastName = this.utility.cleanNames(lastName);
        role = role.toUpperCase();
        this.username = this.utility.generateUser(name, lastName, this.getUsernames());
        if(this.username.equals(" ")) return false;
        email = this.utility.generateEmail(username);

        this.password = this.utility.genNewPassword(this.getAllpasswords());

        if (!this.utility.validateId(id) || !this.utility.validateName(name) || !this.utility.validateName(lastName) || !this.utility.validatePassword(this.password)
                || !this.utility.validateRole(role)) return false;

        Account newAccount = new Account(id, username, password, role, email);
        if (accounts.contains(newAccount)) return false;

        return fmc.writeJsonFile(fileName,newAccount, false);
    }

    /**
     * getUserName returns the generated user
     * @return String with generated user
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * getPassword returns the generate password
     * @return String with password
     */
    public String getPassword(){
        return this.password;
    }
    /**
     * findAccount allows you to search for an account based on username and password
     * @param username
     * @param password
     * @return Object of type Account, said object will be null, if it is not found in the collection
     */
    public Account findAccount(String username, String password){
        String content = fmc.read(fileName);
        Account[] accounts = gson.fromJson(content,Account[].class);
        for (Account acc : accounts){
            if (acc.getUserName().equals(username) && acc.getPassword().equals(password)) return acc;
        }
        return null;
    }
    public Account findAccountById(String id){
        String content = fmc.read(fileName);
        Account[] accounts = gson.fromJson(content,Account[].class);
        for(Account acc : accounts){
            if (acc.getId().equals(id)){
                return acc;
            }
        }
        return null;
    }

    /**
     * Allows you to remove an account from the collection based on username and password
     * @param username
     * @param password
     * @return true if the account was deleted, false if it was not deleted or not found
     */
    public boolean removeAccount(String username, String password){
        String content = fmc.read(fileName);
        ArrayList<Account> accounts = new ArrayList<>(Arrays.asList(gson.fromJson(content,Account[].class)));
        Account accountToRemove = this.findAccount(username, password);

        if (accountToRemove == null) return false;

        accounts.remove(accountToRemove);

        JsonArray array = gson.toJsonTree(accounts).getAsJsonArray();

        return fmc.writeJsonFile(fileName, array,true);
    }

    /**
     * Allows changing an account password from username and password
     * @param username account user to change password
     * @param password old password
     * @param newPassword new password
     * @return true if the change was successful, false if the account was not found or the new password is invalid
     */
    public boolean setNewPassword(String username, String password, String newPassword){
        String content = fmc.read(fileName);
        ArrayList<Account> accounts = new ArrayList<>(Arrays.asList(gson.fromJson(content,Account[].class)));
        Account accountSetPassword = this.findAccount(username, password);
        if (accountSetPassword == null) return false;
        if (!this.utility.validatePassword(newPassword)) return false;

        for (Account acc : accounts){
            if (acc.equals(accountSetPassword)){
                Account tempAccount = this.cloneAccount(acc);
                tempAccount.setPassword(newPassword);
                accounts.remove(acc);
                accounts.add(tempAccount);
            };
        }
        JsonArray array = gson.toJsonTree(accounts).getAsJsonArray();
        return fmc.writeJsonFile(fileName,array, true);
    }
    /**
     * Small utility that is used in changing the password,
     * because it requires creating a new object so that a new hash code
     * is generated in the collection
     * @param account account to be cloned
     * @return Account object with the attributes of the entered account
     */
    private Account cloneAccount(Account account){
        return new Account(account.getId(), account.getUserName(), account.getPassword(), account.getRole(), account.getEmail());
    }

    /**
     * Returns the users in an array, this method is used for user generation
     * @return String[] returns all users in the account collection
     * @Author  Nicolas Sarmiento : Nicolas-Sarmiento
     */
    public String[] getUsernames(){
        String content = fmc.read(fileName);
        ArrayList<Account> accounts = new ArrayList<>(Arrays.asList(gson.fromJson(content,Account[].class)));
        String[] usernames = new String[accounts.size()];
        ArrayList<Account> listAccount = new ArrayList<>(accounts);
        for (int i = 0; i < usernames.length; i++){
            usernames[i] = listAccount.get(i).getUserName();
        }
        return usernames;
    }

    public String showAccounts(){
        String content = fmc.read(fileName);
        Account[] accounts = gson.fromJson(content,Account[].class);
        String accountsInfo = "{";
        for (Account acc : accounts){
            accountsInfo += acc.toString() + "\n";
        }
        accountsInfo += "}";
        return accountsInfo;
    }

    private String[] getAllpasswords(){
        String content = fmc.read(fileName);
        ArrayList<Account> accounts = new ArrayList<>(Arrays.asList(gson.fromJson(content,Account[].class)));
        String[] passwords = new String[accounts.size()];
        ArrayList<Account> listAccount = new ArrayList<>(accounts);
        for (int i = 0; i < passwords.length; i++){
            passwords[i] = listAccount.get(i).getPassword();
        }

        return passwords;
    }


}
