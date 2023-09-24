package co.edu.uptc.controller;

import co.edu.uptc.model.Account;
import co.edu.uptc.model.Person;
import co.edu.uptc.model.Suggestion;
import com.google.gson.Gson;
import co.edu.uptc.utilities.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The SuggestionController class is responsible for managing a collection of Suggestion objects,
 * which represent the suggestions made by students.
 * It provides methods to create new suggestions, retrieve suggestions, and associate them with accounts.
 * This controller handles the logic for handling student suggestions and their storage.
 *
 * @author Diego Combariza
 * @version 1.1
 */
public  class SuggestionController {


    private Gson gson;
    private List<Suggestion> suggestionsList;
    private static final String fileName = "Suggestions";
    //private FileManagerController fmc;
    private JsonStorageUtilities fmc;

    /**
     * Constructs a new SuggestionController with an empty list of suggestions.
     */
    public SuggestionController() {
        fmc = new JsonStorageUtilities();
        gson= new Gson();
        suggestionsList = fmc.readContentFromFile(fileName,new TypeToken<List<Suggestion>>(){}.getType());
    }

    /**
     * Retrieves the list of suggestions stored in the SuggestionController.
     * @return A List of Suggestion objects representing the suggestions made by students.
     */
    public String getSuggestions() {
        String response = "";
        int index = 1;
        for (Suggestion ch: this.suggestionsList){
            String readed = ch.isRead() ? "Leído" : "No leído";
            response += "\n"+ index + ". " + ch.toString()+ " Estado= "+ readed;
            index++;
        }
        return response;
    }
    /**
     * Creates a new suggestion with the provided message, date, and associated account,
     * and adds it to the list of suggestions in the SuggestionController.
     * @param message The content of the suggestion.
     * @param account The Account object representing the student who made the suggestion.
     * @return true if the suggestion was successfully created and added, false otherwise.
     */
    public boolean createSuggestion(String message, Account account) {
        Date date = new Date();
        String userName = account.getUserName();
        String userMail = account.getEmail();
        Object user = userName+userMail;
        Suggestion newSuggestion = new Suggestion(date.toString(), message, user);
        suggestionsList.add(newSuggestion);
        return fmc.saveDataToFile(suggestionsList,fileName,new TypeToken<List<Suggestion>>(){}.getType());
    }

    public boolean readed(Suggestion sugerencia) {

        if (!sugerencia.isRead()) {
            sugerencia.setRead(true);
            return fmc.saveDataToFile(suggestionsList, fileName, new TypeToken<List<Suggestion>>() {
            }.getType());
        }
        return false;
    }

}




