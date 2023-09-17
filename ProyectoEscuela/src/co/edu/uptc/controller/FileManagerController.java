package co.edu.uptc.controller;
import co.edu.uptc.model.Account;
import co.edu.uptc.model.Covenant;
import co.edu.uptc.model.Person;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

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

    public boolean create(String fileName){
        File file = new File(fileName);
        try{
            PrintWriter printWriter = new PrintWriter(new FileWriter(RUTE+fileName+EXTENSION, true));
            printWriter.close();
            return true;
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return false;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean writeJsonFile(String filename, Object object, boolean overwrite){
        JsonArray arr = new JsonArray();
        String content = read(filename);

        if(content != null && !overwrite){
            arr = JsonParser.parseString(content).getAsJsonArray();
        }
        if (object instanceof Object[] || object.getClass().isArray()) {
            arr = gson.toJsonTree(object).getAsJsonArray();
        } else {
            JsonObject json = gson.toJsonTree(object).getAsJsonObject();
            arr.add(json);
        }
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


