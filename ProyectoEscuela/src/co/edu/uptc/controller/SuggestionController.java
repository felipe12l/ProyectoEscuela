package co.edu.uptc.controller;

import co.edu.uptc.model.Account;
import co.edu.uptc.model.Suggestion;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * The SuggestionController class is responsible for managing a collection of Suggestion objects,
 * which represent the suggestions made by students.
 * It provides methods to create new suggestions, retrieve suggestions, and associate them with accounts.
 * This controller handles the logic for handling student suggestions and their storage.
 *
 * @author Diego Combariza
 * @version 1.0.0
 */
public  class SuggestionController {


    private Gson gson;
    private static final String fileName = "Suggestions";
    private FileManagerController fmc;

    /**
     * Constructs a new SuggestionController with an empty list of suggestions.
     */
    public SuggestionController() {
        fmc = new FileManagerController();
        gson= new Gson();
    }

    /**
     * Retrieves the list of suggestions stored in the SuggestionController.
     * @return A List of Suggestion objects representing the suggestions made by students.
     */
    public String getSuggestions() {
        String content = fmc.read(fileName);
        Suggestion[] suggestions = gson.fromJson(content, Suggestion[].class);
        String response = "";
        int index = 1;
        for (Suggestion ch: suggestions){
            response += "\n"+ index + ". " + ch.toString();
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
        Suggestion newSuggestion = new Suggestion(date.toString(), message, account);
        return fmc.writeJsonFile(fileName,newSuggestion,false);
    }
}




