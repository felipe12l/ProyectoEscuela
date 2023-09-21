package co.edu.uptc.model.persontypes;

import co.edu.uptc.model.Account;
import co.edu.uptc.model.Person;

public class Secretary  extends Person {
    public Secretary(String id, String name, String lastname, Account account) {
        super(id, name, lastname, account);
    }
    public Secretary(String id, String name, String lastname) {
        super(id, name, lastname);
    }
}
