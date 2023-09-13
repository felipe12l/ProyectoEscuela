package co.edu.uptc.model;



public class Covenant {
    private String Tittle, Contact, NameofCreator, Description, Link;

    @Override
    public String toString() {
        return  "Tittle='" + Tittle + '\'' +
                ", Contact='" + Contact + '\'' +
                ", NameofCreator='" + NameofCreator + '\'' +
                ", Description='" + Description + '\'' +
                ", Link='" + Link + '\'' +
                ", category=" + category
                ;
    }

    private Category category;

    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getNameofCreator() {
        return NameofCreator;
    }

    public void setNameofCreator(String nameofCreator) {
        NameofCreator = nameofCreator;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Covenant() {
    }

    public Covenant(String tittle, String contact, String nameofCreator, String description, String link, Category category) {
        Tittle = tittle;
        Contact = contact;
        NameofCreator = nameofCreator;
        Description = description;
        Link = link;
        this.category = category;
    }

}
