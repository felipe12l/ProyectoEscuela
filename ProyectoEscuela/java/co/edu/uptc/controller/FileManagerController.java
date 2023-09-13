package co.edu.uptc.controller;
import co.edu.uptc.model.Account;
import co.edu.uptc.model.Person;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.*;
import java.util.HashSet;
/***
 * The FileManagerController class is for create,read,write and modify files.
 * @author Felipe Luna
 *
 */

public class FileManagerController {
    private Gson gson;

    public final String RUTE = "src\\co\\edu\\co.edu.uptc\\persistence\\", EXTENSION = ".json";
    public FileManagerController(){
        gson=new Gson();
    }
    public HashSet<Account> getFromFileAccounts(String fileName) {
        HashSet< Account> auxiliar=new HashSet<>();
        try {
            auxiliar=gson.fromJson(new FileReader(RUTE+fileName+EXTENSION),new TypeToken<HashSet<Account>>(){}.getType());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return auxiliar;
    }
    public HashSet<Person> getFromFilePersons(String fileName){
        HashSet<Person> auxiliar=new HashSet<>();
        try {
            auxiliar=gson.fromJson(new FileReader(RUTE+fileName+EXTENSION),new TypeToken<HashSet<Person>>(){}.getType());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return auxiliar;
    }
    public boolean writeJsonFilePerson(String fileName,HashSet<Person> persons){
        String sJson= gson.toJson(persons);
        try{
            FileWriter fw=new FileWriter(RUTE+fileName+EXTENSION);
            fw.write(sJson);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     *
     * @param fileName this param is used to assign the name of the file
     * @param accounts this param is the accounts in the system
     * @return a boolean value to know if the file has been created or modified
     * successfully
     */
    public boolean writeJsonFileAccounts(String fileName,HashSet<Account> accounts){
        String sJson= gson.toJson(accounts);
        File file =new File("C:\\Users\\Sala7\\Desktop\\ProyectoEscuela\\ProyectoEscuela\\src\\co\\edu\\co.edu.uptc\\persistence\\"+fileName+EXTENSION);
        try{
            FileWriter fw=new FileWriter(file);
            System.out.println(fw.toString());
            fw.write(sJson);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


}


