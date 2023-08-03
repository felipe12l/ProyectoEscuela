package co.edu.uptc.model;


/**
 * The Suggestion class represents a suggestion made by a student. It contains information about the date,
 * the content of the suggestion, and the student who made the suggestion.
 * This class provides methods to get and set the date, content, and associated student account.
 *
 * @author Diego Combariza
 * @version 1.0.0
 */

public class Suggestion {
    private String date;
    private String content;
    private Account account;
    /**
     * Constructs a new Suggestion object with the provided date, content, and associated student account.
     *
     * @param date    The date when the suggestion was made.
     * @param content The content of the suggestion.
     * @param account The Account object representing the student who made the suggestion.
     */

    public Suggestion(String date, String content, Account account) {
        this.date = date;
        this.content = content;
        this.account = account;
    }

    public Suggestion() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Suggestion{" +
                "date='" + date + '\'' +
                ", content='" + content + '\'' +
                ", student=" + account +
                '}';
    }
}
