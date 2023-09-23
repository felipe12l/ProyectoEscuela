package co.edu.uptc.view;

import co.edu.uptc.model.Covenant;
import co.edu.uptc.utilities.InputLibrary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.List;

public class ManageCovenants extends Header{
    LoginView view;
    BorderPane borderPane;
    TableView table;
    TableColumn tittleColum;
    TableColumn contactColum;
    TableColumn nameOfCreatorColum;
    TableColumn descriptionColum;
    TableColumn linkColum;
    TableColumn typeColum;
    TableColumn typeDescriptionColum;
    TextField inputTitle, inputContact, inputName, inputDescription, inputLink;
    ComboBox<String> categories;
    Button add, delete, edit;
    Covenant editCovenant;
    Boolean confirmChange;

    ObservableList<Covenant> covenants = FXCollections.observableArrayList();

    /**
     * Constructs a Header instance with a provided home button.
     *
     * @param button The home button for the header.
     */
    public ManageCovenants(LoginView loginView, Button button) {
        super(button);
        this.view = loginView;
        borderPane = new BorderPane();
        table = new TableView<>();
        confirmChange = false;
        creationColumns();
    }
    public HBox createHbox(){
        VBox vBox1 = new VBox();
        Label labelTittle = new Label("Tittle");
        inputTitle = new TextField();
        inputTitle.setPromptText("Tittle");
        inputTitle.setMinWidth(100);

        Label labelContact = new Label("Contact");
        inputContact = new TextField();
        inputContact.setPromptText("Contact");
        inputContact.setMinWidth(100);

        Label labelName = new Label("Name of creator");
        inputName = new TextField();
        inputName.setPromptText("name of creator");
        inputName.setMinWidth(100);

        labelName.setId("inputLabel");
        labelContact.setId("inputLabel");
        labelTittle.setId("inputLabel");
        vBox1.getChildren().addAll(labelTittle,inputTitle, labelContact,inputContact, labelName,inputName);
        VBox.setMargin(inputTitle, new Insets(10,0,10,0));
        VBox.setMargin(inputContact, new Insets(10,0,10,0));
        VBox.setMargin(inputName, new Insets(10,0,10,0));

        VBox vBox2 = new VBox();
        Label labelDescription = new Label("Description");
        inputDescription = new TextField();
        inputDescription.setPromptText("description");
        inputDescription.setMinWidth(100);

        Label labelLink = new Label("Link");
        inputLink = new TextField();
        inputLink.setPromptText("Link");
        inputLink.setMinWidth(100);

        Label labelCategories = new Label("Categories");
        final String[] selectedOption = {null};
        categories = new ComboBox<>();
        categories.getItems().addAll("Convenios Interinstitucionales","Convenios de Movilidad",
                "Convenios Empresariales", "Convenios de Cooperación", "Convenios de Extensión", "Convenios Culturales y Deportivos",
                "Convenios de Intercambio Académico");
        categories.setValue("Convenios Interinstitucionales");
        categories.setOnAction(event -> {
            selectedOption[0] = categories.getValue();
        });

        labelDescription.setId("inputLabel");
        labelLink.setId("inputLabel");
        labelCategories.setId("inputLabel");
        vBox2.getChildren().addAll(labelDescription, inputDescription, labelLink, inputLink, labelCategories, categories);
        VBox.setMargin(inputDescription, new Insets(10,0,10,0));
        VBox.setMargin(inputLink, new Insets(10,0,10,0));
        VBox.setMargin(categories, new Insets(10,0,10,0));

        VBox vBox3 = new VBox();
        add = new Button("Add");
        add.setCursor(Cursor.HAND);
        add.setOnAction(event -> addButtonClicked(selectedOption[0]));
        add.setId("Button");
        delete = new Button("Delete");
        delete.setCursor(Cursor.HAND);
        delete.setOnAction(event -> deleteButtonClicked());
        delete.setId("Button");
        edit = new Button("Edit");
        edit.setCursor(Cursor.HAND);
        edit.setOnAction(event -> editButtonClicked(selectedOption[0]));
        edit.setId("Button");

        vBox3.getChildren().addAll(add,delete,edit);
        VBox.setMargin(add, new Insets(40,0,10,0));
        VBox.setMargin(delete, new Insets(10,0,10,0));
        VBox.setMargin(edit, new Insets(10,0,10,0));

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(20);
        hBox.getChildren().addAll(vBox1,vBox2,vBox3);
        return hBox;
    }

    public Scene loginListCovenants(){
        updateTable();

        table.setItems(covenants);
        borderPane.setCenter(table);
        HBox header = this.getHeader();
        this.setOption("Ver convenios");
        this.setName(view.controller.getName());
        table.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 1) {
                selectCovenant();
            } else if(event.getClickCount() > 1){
                reStartInputs();
            }
        });
        HBox hBox = createHbox();
        hBox.setAlignment(Pos.CENTER);
        VBox root = new VBox(header, borderPane, hBox);
        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(new File("./styles/ManageCovenants.css").toURI().toString());
        return scene;
    }

    private void updateTable() {
        List<Covenant> updatedCovenants = view.covenantController.getCovenantsList();

        for (Covenant updatedCovenant : updatedCovenants) {
            if (!covenants.contains(updatedCovenant)) {
                addCovenant(updatedCovenant);
            }
        }

        covenants.removeIf(covenant -> !updatedCovenants.contains(covenant));
    }

    public void addCovenant(Covenant covenant){
        covenants.add(covenant);
    }

    private void creationColumns() {

        tittleColum = new TableColumn<Covenant, String>("Tittle");
        tittleColum.setCellValueFactory(new PropertyValueFactory<Covenant, String>("Tittle"));

        contactColum = new TableColumn<Covenant, String>("Contact");
        contactColum.setCellValueFactory(new PropertyValueFactory<Covenant, String>("Contact"));

        nameOfCreatorColum = new TableColumn<Covenant, String>("Name creator");
        nameOfCreatorColum.setCellValueFactory(new PropertyValueFactory<Covenant, String>("NameofCreator"));

        descriptionColum = new TableColumn<Covenant, String>("Description");
        descriptionColum.setCellValueFactory(new PropertyValueFactory<Covenant, String>("Description"));

        linkColum = new TableColumn<Covenant, String>("Link");
        linkColum.setCellValueFactory(new PropertyValueFactory<Covenant, String>("Link"));

        typeColum = new TableColumn<Covenant, String>("category name");
        typeColum.setCellValueFactory(new PropertyValueFactory<Covenant, String>("categoryName"));

        typeDescriptionColum = new TableColumn<Covenant, String>(" category description");
        typeDescriptionColum.setCellValueFactory(new PropertyValueFactory<Covenant, String>("categoryDescription"));
        typeDescriptionColum.setPrefWidth(150);

        table.getColumns().add(tittleColum);
        table.getColumns().add(contactColum);
        table.getColumns().add(nameOfCreatorColum);
        table.getColumns().add(descriptionColum);
        table.getColumns().add(linkColum);
        table.getColumns().add(typeColum);
        table.getColumns().add(typeDescriptionColum);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }

    public void addButtonClicked(String category){
        int position = 0;

        for (int i = 0; i < view.covenantController.prechargeCategories().size();i++){
            if(view.covenantController.prechargeCategories().get(i).getName().equals(category)){
                position = i;
            }
        }

        view.covenantController.addCovenant(inputTitle.getText(), inputContact.getText(),inputName.getText(),inputDescription.getText(),inputLink.getText(), position);
        reStartInputs();
        updateTable();
    }
    public void selectCovenant() {
        int position = 0;
        ObservableList<Covenant> covenantSelected = table.getSelectionModel().getSelectedItems();

        if (covenantSelected.size() == 1) {
            inputTitle.setText(covenantSelected.get(0).getTittle());
            inputContact.setText(covenantSelected.get(0).getContact());
            inputDescription.setText(covenantSelected.get(0).getDescription());
            inputName.setText(covenantSelected.get(0).getNameofCreator());
            inputLink.setText(covenantSelected.get(0).getLink());
            for (int i = 0; i < view.covenantController.prechargeCategories().size(); i++) {
                if (view.covenantController.prechargeCategories().get(i).getName().equals(covenantSelected.get(0).getCategoryName())) {
                    categories.setValue(covenantSelected.get(0).getCategoryName());
                    position = i;
                }
            }
        }
        editCovenant = covenantSelected.get(0);
    }
    public void deleteButtonClicked(){
        ObservableList<Covenant> covenantSelected, allCovenants;
        allCovenants = table.getItems();
        covenantSelected = table.getSelectionModel().getSelectedItems();

        for(int i = 0; i<covenantSelected.size();i++){
            Covenant deleted = covenantSelected.get(i);
            view.covenantController.DeleteCovenant(deleted);
        }
        covenantSelected.forEach(allCovenants::remove);
        updateTable();
        reStartInputs();
    }
    public void editButtonClicked(String category){
        int position = 0;
        for (int i = 0; i < view.covenantController.prechargeCategories().size();i++){
            if(view.covenantController.prechargeCategories().get(i).getName().equals(category)){
                position = i;

            }
        }
        view.covenantController.DeleteCovenant(editCovenant);
        view.covenantController.addCovenant(inputTitle.getText(), inputContact.getText(),inputName.getText(),inputDescription.getText(),inputLink.getText(),position);
        reStartInputs();
        updateTable();
    }

    public void  confirmButtonSelected(){
        confirmChange = true;
    }
    public void reStartInputs(){
        inputTitle.clear();
        inputContact.clear();
        inputName.clear();
        inputDescription.clear();
        inputLink.clear();
        categories.setValue("Convenios Interinstitucionales");
    }
}
