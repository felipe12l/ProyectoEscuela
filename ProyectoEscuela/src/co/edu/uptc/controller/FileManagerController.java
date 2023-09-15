package co.edu.uptc.controller;
import co.edu.uptc.model.Account;
import co.edu.uptc.model.Covenant;
import co.edu.uptc.model.Person;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

    public final String RUTE = "ProyectoEscuela\\src\\co\\edu\\uptc\\persistence\\", EXTENSION = ".json";
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
        File file =new File(RUTE+fileName+EXTENSION);
        try{
            FileWriter fw=new FileWriter(file);
            System.out.println();
            fw.write(sJson);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean writeJsonFileCovenant(String filename, HashSet<Covenant> covenants){
        JsonArray arr = new JsonArray();
        String content = read(filename);

        if(content != null){
            arr = JsonParser.parseString(content).getAsJsonArray();
        }
        JsonObject json = gson.toJsonTree(covenants).getAsJsonObject();
        arr.add(json);
        try{
            PrintWriter pw = new PrintWriter(new FileWriter(RUTE+filename+EXTENSION ));
            pw.println(arr);
            pw.close();
            return true;
        }catch (IOException f){
            f.printStackTrace();
            return false;
        }

    }
    public String read(String fileName){
        try{
            String resolve = "";
            String nextLine = "";
            BufferedReader br = new BufferedReader(new FileReader(RUTE+fileName+EXTENSION));
            nextLine= br.readLine();
            while (nextLine != null){
                resolve += "\n" + nextLine;
                nextLine = br.readLine();
            }
            br.close();
            return resolve.replaceFirst("\n", "");
        }catch (IOException e){
            return null;
        }
    }



}


