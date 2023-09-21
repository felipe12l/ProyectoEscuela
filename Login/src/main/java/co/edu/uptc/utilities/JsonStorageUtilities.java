package co.edu.uptc.utilities;

import co.edu.uptc.model.Account;
import co.edu.uptc.model.Covenant;
import co.edu.uptc.model.Person;
import co.edu.uptc.model.persontypes.Administrator;
import co.edu.uptc.model.persontypes.Professor;
import co.edu.uptc.model.persontypes.Secretary;
import co.edu.uptc.model.persontypes.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonStorageUtilities {
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private FileWriter fileWriter;
    private FileReader fileReader;
    private Gson gson;
    // Creo una lista global para almacenar los objetos leídos del archivo, esto para que
    //cuando agreguemos algo, no borre el resto
    private List<Person> existingContentsPersons = new ArrayList<>();
    private List<Account> existingContentsAccounts = new ArrayList<>();
    private static final String FILEPATH = "./src/main/java/co/edu/uptc/persistence/";
    private static final String EXTENSION = ".json";
    private static final String FILEPATHPEOPLE = "./src/main/java/co/edu/uptc/persistence/people.json";

    public JsonStorageUtilities(){
        //El gson esta inicializado asi para que se escriba en cascada y no en una misma linea
        gson = new GsonBuilder().setPrettyPrinting().create();
        //Esto es para que reconozca siempre el contenido del archivo, ya que si no lo hacemos asi
        //cuando se agregue algo nuevo se va a sobreescribir
        //readContentFromFile();
        readPersons("people");
        readAccounts("accounts");
    }

    //Probablemente nunca se use / Este comentario es provisional, por eso el español
    public boolean createFile(String fileName){
        String ext = ".json";
        String filePath = FILEPATH+fileName+ext;

        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //Se utiliza métodos genericos para la creación de dos archivos diferentes ( cuentas y personas )
    public <T> boolean saveDataToFile(List<T> dataList, String fileName, Type type) {
        File file = new File(FILEPATH + fileName + EXTENSION);
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
        try (FileWriter fileWriter = new FileWriter(file)) {
            gson.toJson(dataList, type, fileWriter);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean saveDataToFilePerson(List<Person> listPerson, String fileName) {
        Type personListType = new TypeToken<List<Person>>() {}.getType();
        return saveDataToFile(listPerson, fileName, personListType);
    }

    public boolean saveDataToFileAccount(List<Account> listAccount, String fileName) {
        Type accountListType = new TypeToken<List<Account>>() {}.getType();
        return saveDataToFile(listAccount, fileName, accountListType);
    }

    public <T> List<T> readContentFromFile(String fileName, Type type) {
        List<T> dataList = new ArrayList<>();

        File file = new File( FILEPATH + fileName + EXTENSION);
        if (!file.exists()) {
            System.out.println("hola");
            return null;
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            dataList.clear();
            dataList = gson.fromJson(bufferedReader, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return dataList;
    }
    /**
     * Reads a JSON file containing information about people and stores them in the list
     * 'existingContentsPersons', initializing each person with their respective role.
     *
     * @param fileName The name of the JSON file to be read.
     */
    public void readPersons(String fileName) {
        List<Person> personList = readContentFromFile(fileName,new TypeToken<List<Person>>(){}.getType());
        String rol = "";
        for (Person person:personList) {
            rol = person.getAccount().getRole();
            switch (rol){
                case "STUDENT":
                    existingContentsPersons.add(new Student(person.getId(),person.getName(),person.getLastname(),person.getAccount()));
                    break;
                case "PROFESSOR":
                    existingContentsPersons.add(new Professor(person.getId(),person.getName(),person.getLastname(), person.getAccount()));
                    break;
                case "ADMINISTRATOR":
                    existingContentsPersons.add(new Administrator(person.getId(),person.getName(),person.getLastname(), person.getAccount()));
                    break;
                case "SECRETARY":
                    existingContentsPersons.add(new Secretary(person.getId(),person.getName(),person.getLastname(), person.getAccount()));
                    break;
                case "DIRECTOR":
                    existingContentsPersons.add(new Administrator(person.getId(),person.getName(),person.getLastname(), person.getAccount()));
                    break;
            }
        }
    }

    public void readAccounts(String fileName) {
        existingContentsAccounts.addAll(readContentFromFile(fileName, new TypeToken<List<Account>>() {}.getType()));
    }
    //Julian hpta
    public Person findPosition(String id) {
        for (int i = 0; i < existingContentsPersons.size(); i++) {
            if (existingContentsPersons.get(i).getId().equals(id)) {
                return existingContentsPersons.get(i);
            }
        }
        return null;
    }

    public boolean deletePerson(String id) {
        Person personToRemove = findPosition(id);
        if (personToRemove != null) {
            existingContentsPersons.remove(personToRemove);
            saveObject(existingContentsPersons);
            return true;
        }
        return false;
    }

    private void saveObject(List<Person> personList) {
        try{
            fileWriter = new FileWriter(FILEPATHPEOPLE);
            fileWriter.write(gson.toJson(personList));
            fileWriter.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public List<Account> getExistingContentsAccounts() {
        return existingContentsAccounts;
    }

    public void setExistingContentsAccounts(List<Account> existingContentsAccounts) {
        this.existingContentsAccounts = existingContentsAccounts;
    }

    public List<Person> getExistingContentsPersons() {
        return existingContentsPersons;
    }

    public void setExistingContentsPersons(List<Person> existingContents) {
        this.existingContentsPersons = existingContents;
    }
}
