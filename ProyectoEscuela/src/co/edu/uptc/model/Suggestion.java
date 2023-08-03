package co.edu.uptc.model;

import co.edu.uptc.model.persontypes.Student;

public class Suggestion {
    private String date;
    private String content;
    private Account account;

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
