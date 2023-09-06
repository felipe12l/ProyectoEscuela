package co.edu.uptc.controller;

import co.edu.uptc.model.Account;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

/***
 * The FileManagerController class is for create,read,write and modify files.
 * @author Felipe Luna
 *
 */

public class FileManagerController {
    Gson gson;
    public final String RUTE = "src\\co\\edu\\uptc\\persistence", EXTENSION = ".json";

    public HashSet<Account> getFromFileAccounts(String fileName) {
        HashSet< Account> auxiliar= new HashSet<>();
        try {
            auxiliar=gson.fromJson(new FileReader(RUTE+fileName+EXTENSION),new TypeToken<HashSet<Account>>(){}.getType());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return auxiliar;
    }

    /**
     *
     * @param fileName this param is used to assign the name of the file
     * @param accounts this param is the accounts in the system
     * @return a boolean value to know if the file has been created succesfully
     */
    public boolean createJsonFileAccounts(String fileName,HashSet<Account> accounts){
        String sJson= gson.toJson(accounts);
        try{
            FileWriter fw=new FileWriter(RUTE+fileName+EXTENSION);
            fw.write(sJson);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}


