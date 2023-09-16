package co.edu.uptc.controller;

import co.edu.uptc.model.Account;
import co.edu.uptc.model.Suggestion;
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
 * @version 1.0.0
 */
public  class SuggestionController {


    private ArrayList<Suggestion> suggestions;
    private static final String fileName = "Suggestions";
    private FileManagerController fmc;

    /**
     * Constructs a new SuggestionController with an empty list of suggestions.
     */
    public SuggestionController() {
        this.suggestions = new ArrayList<>();
        fmc = new FileManagerController();
    }

    /**
     * Retrieves the list of suggestions stored in the SuggestionController.
     * @return A List of Suggestion objects representing the suggestions made by students.
     */
    public List<Suggestion> getSuggestions() {
        List<Suggestion> allSuggestions = new ArrayList<>();
        for (Suggestion suggestion : suggestions) {
            String suggestionDate = suggestion.getDate();
            String suggestionMessage = suggestion.getContent();
            Account suggestionAccount = suggestion.getAccount();
           Suggestion newSuggestion = new Suggestion(suggestionDate, suggestionMessage, suggestionAccount);
            allSuggestions.add(newSuggestion);
        }
        return allSuggestions;
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




