package co.edu.uptc.model.persontypes;
import co.edu.uptc.model.Account;
import co.edu.uptc.model.Person;

public class Professor extends Person {


    public Professor(String id, String name, String lastname) {
        super(id, name, lastname);
    }
    public Professor(String id, String name, String lastname, Account account) {
        super(id, name, lastname, account);
    }
}
