package co.edu.uptc.view;

import co.edu.uptc.controller.SuggestionController;
import co.edu.uptc.model.Suggestion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;


import java.io.File;
import java.util.List;

public class ManageSuggestion extends Header{
    LoginView view;
    BorderPane borderPane;
    TableView table;
    TableColumn DateColum;
    TableColumn contentColum;
    TableColumn userColum;
    TableColumn userMailColum;
    TableColumn readStatus;
    Button markAsReadButton;
    SuggestionController mySugCo;
    ObservableList<Suggestion> suggestions = FXCollections.observableArrayList();
    /**
     * Constructs a Header instance with a provided home button.
     */
    public ManageSuggestion(LoginView loginView, Button button) {
        super(button);
        this.view = loginView;
        borderPane = new BorderPane();
        table = new TableView();
        creationColumns();
        mySugCo = new SuggestionController();
    }
    public Scene ListSuggestions(){
        updateTable();
        table.setItems(suggestions);
        borderPane.setCenter(table);
        HBox header = this.getHeader();
        this.setOption("Leer sugerencias");
        this.setName(view.controller.getName());

        VBox root = new VBox(header, borderPane);
        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(new File("./styles/ManageCovenants.css").toURI().toString());
        return scene;
    }
    public void updateTable(){
        List<Suggestion> updatedSuggestions = view.suggestionController.getSuggestionsList();

        for (Suggestion updatedSuggestion: updatedSuggestions){
            if (!suggestions.contains(updatedSuggestion)){
                addSugestion(updatedSuggestion);
            }
        }
        suggestions.removeIf(suggestion -> !updatedSuggestions.contains(suggestion));
    }

    private  void creationColumns(){
        DateColum = new TableColumn<Suggestion, String>("Date");
        DateColum.setCellValueFactory(new PropertyValueFactory<Suggestion, String>("Date"));

        contentColum = new TableColumn<Suggestion, String>("Content");
        contentColum.setCellValueFactory(new PropertyValueFactory<Suggestion, String>("Content"));

        userColum = new TableColumn<Suggestion, String>("Name User");
        userColum.setCellValueFactory(new PropertyValueFactory<Suggestion, String>("UserName"));

        userMailColum = new TableColumn<Suggestion, String>("Email");
        userMailColum.setCellValueFactory(new PropertyValueFactory<Suggestion, String>("ContactEmail"));

        readStatus = new TableColumn<>("ReadStatus");
        readStatus.setCellFactory(tc -> {
            return new TableCell<Suggestion, Boolean>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);

                    if (!empty) {
                        markAsReadButton = new Button("mark as read");
                        this.setGraphic(markAsReadButton);
                        markAsReadButton.setOnAction(event -> {
                            int selectedIndex = table.getSelectionModel().getSelectedIndex();
                            if (selectedIndex >= 0 && selectedIndex < table.getItems().size()) {
                                Suggestion suggestion = (Suggestion) table.getItems().get(selectedIndex);
                                mySugCo.markAsRead(suggestion);
                                updateItem(item, empty);
                                markAsReadButton.setText("");
                                markAsReadButton.graphicProperty().setValue(new Label("readed"));
                            }
                        });

                    } else {
                        this.setGraphic(null);
                    }
                }
            };
        });

        table.getColumns().add(DateColum);
        table.getColumns().add(contentColum);
        table.getColumns().add(userColum);
        table.getColumns().add(userMailColum);
        table.getColumns().add(readStatus);


        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }
    public void addSugestion(Suggestion suggestion){
        suggestions.add(suggestion);
    }
}
