package co.edu.uptc.model;


import co.edu.uptc.model.persontypes.User;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.Objects;

/**
 * The Suggestion class represents a suggestion made by a student. It contains information about the date,
 * the content of the suggestion, and the student who made the suggestion.
 * This class provides methods to get and set the date, content, and associated student account.
 *
 * @author Diego Combariza
 * @version 1.1.0
 */

public class Suggestion {
    private String date;
    private String content;
    private String userName;
    private String contactEmail;
    private boolean read;
    /**
     * Constructs a new Suggestion object with the provided date, content, and associated student account.
     *
     * @param date    The date when the suggestion was made.
     * @param content The content of the suggestion.
     * @param usuario The user object representing the student who made the suggestion.
     */

    public Suggestion(String date, String content, User usuario) {
        this.date = date;
        this.content = content;
        this.userName = usuario.getName();
        this.contactEmail = usuario.getEmail();
        this.read = false;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Suggestion that = (Suggestion) o;
        return Objects.equals(content, that.content) && Objects.equals(userName, that.userName) && Objects.equals(contactEmail, that.contactEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, userName, contactEmail);
    }

    @Override
    public String toString() {
        return "Suggestion{" +
                "date='" + date + '\'' +
                ", content='" + content + '\'' +
                ", nameOfUser='" + userName + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", read=" + read +
                '}';
    }
}
