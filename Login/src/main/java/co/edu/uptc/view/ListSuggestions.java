package co.edu.uptc.view;

import co.edu.uptc.model.Covenant;
import co.edu.uptc.model.Suggestion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class ListSuggestions extends Header {
    LoginView view;
    BorderPane borderPane;
    ListView<String> listView; // Utilizamos ListView para mostrar las sugerencias

    ObservableList<String> suggestions = FXCollections.observableArrayList();

    public ListSuggestions(LoginView loginView, Button button) {
        super(button);
        this.view = loginView;
        borderPane = new BorderPane();
        listView = new ListView<>();
    }

    public Scene loginListSuggestions() {
        showSuggestions(); // Llama a la función para mostrar las sugerencias

        borderPane.setCenter(listView);
        HBox header = this.getHeader();
        this.setOption("Ver sugerencias"); // Puedes cambiar el texto según tus necesidades
        this.setName(view.controller.getName());
        VBox root = new VBox(header, borderPane);
        Scene scene = new Scene(root, 1000, 600);
        return scene;
    }

    private void showSuggestions() {
        List<Suggestion> suggestionsList = view.suggestionController.getSuggestionsList();

        for (Suggestion suggestion : suggestionsList) {
            String suggestionText = suggestion.getContent(); // Reemplaza con el nombre de tu propiedad de contenido
            suggestions.add(suggestionText);
        }

        listView.setItems(suggestions);
    }
}
