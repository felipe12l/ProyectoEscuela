package co.edu.uptc.model;

public class Category {
    /**
     * Category Class... just ff
     * @author Felipe Luna
     */
    private String name, description;


    @Override
    public String toString() {
        return
                "Type: " + name +
                "\nDescription: " + description ;
    }

    public String nameToString() {
        return
                "Tipo " +name
                ;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
