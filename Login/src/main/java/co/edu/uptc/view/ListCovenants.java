package co.edu.uptc.view;

import co.edu.uptc.model.Covenant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class ListCovenants extends Header{
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

    ObservableList<Covenant> covenants = FXCollections.observableArrayList();
    /**
     * Constructs a Header instance with a provided home button.
     *
     * @param button The home button for the header.
     */
    public ListCovenants(LoginView loginView,Button button) {
        super(button);
        this.view = loginView;
        borderPane = new BorderPane();
        table = new TableView<>();
        creationColumns();
    }

    public Scene loginListCovenants(){
        updateTable();

        table.setItems(covenants);
        borderPane.setCenter(table);
        HBox header = this.getHeader();
        this.setOption("Ver convenios");
        this.setName(view.controller.getName());
        VBox root = new VBox(header, borderPane);
        Scene scene = new Scene(root, 1000, 600);
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
}
