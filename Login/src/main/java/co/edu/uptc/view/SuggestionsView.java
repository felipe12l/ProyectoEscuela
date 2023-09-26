package co.edu.uptc.view;

import co.edu.uptc.controller.SuggestionController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;

import java.io.File;
import java.util.Optional;

/***
 *
 */

public class SuggestionsView extends Header{

    SuggestionController sCont;
    Button sendButton;
    Label titleSuggestionLabel, contentSuggestionLabel;
    TextField titleSuggestionField;
    TextArea contentSuggestionField;
    LoginView parent;

    VBox root, container,fieldsContainer;

    /**
     * Constructs a Header instance with a provided home button.
     *
     * @param button The home button for the header.
     */
    public SuggestionsView(LoginView loginView, Button button) {
        super(button);
        this.parent=loginView;
        this.sCont=new SuggestionController();

    }
    public Scene View(){
        refillableFields();
        //header
        HBox header = this.getHeader();
        this.setOption("Crear sugerencias");
        this.setName(parent.controller.getName());

        //form containers
        this.fieldsContainer=new VBox();
        fieldsContainer.getChildren().addAll(
                this.titleSuggestionLabel,
                this.titleSuggestionField,
                this.contentSuggestionLabel,
                this.contentSuggestionField,
                this.sendButton);
        fieldsContainer.setId("fields");
        this.container=new VBox();
        container.setId("main");
        //instance
        this.root=new VBox();
        //set visible
        titleSuggestionLabel.setVisible(true);
        titleSuggestionField.setVisible(true);
        contentSuggestionLabel.setVisible(true);
        contentSuggestionField.setVisible(true);
        sendButton.setVisible(true);
        container.setVisible(true);
        //setContainer Child
        container.getChildren().add(fieldsContainer);
        container.setAlignment(Pos.CENTER);

        //root
        root.getChildren().addAll(header,container);
        root.setId("root");
        VBox.setMargin(root, new Insets(0, 0, 0, 0));
        //scene init
        Scene view=new Scene(root,1000,600);
        view.getStylesheets().add(new File ("./styles/suggestionView.css").toURI().toString());
        return view;
    }
    private void refillableFields(){
        //titleField, label
        titleSuggestionField= new TextField();
        titleSuggestionLabel=new Label("Titulo: ");
        titleSuggestionField.setMaxWidth(200);
        titleSuggestionField.setId("suggestionField");

        //contentField,label
        contentSuggestionField=new TextArea();
        contentSuggestionField.setWrapText(true);
        contentSuggestionLabel=new Label("Descripcion");
        contentSuggestionField.setId("contentField");
        contentSuggestionField.setMaxWidth(200);
        //button
        sendButton=new Button("Enviar");
        sendButton.setFont(new Font(14));
        sendButton.setId("sendButton");
        if (!contentSuggestionField.getText().equals(null)){
        sendButton.setOnAction(e ->showPopUp());}
    }

    private void showPopUp() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Selección de Opción");
        alert.setHeaderText("Elige una opción:");
        alert.setContentText("¿Esta seguro de enviar esto?");

        ButtonType opcion1 = new ButtonType("si");
        ButtonType opcion2 = new ButtonType("no");


        alert.getButtonTypes().setAll(opcion1, opcion2);

        // Bloquea la ventana principal hasta que se elija una opción
        alert.initModality(Modality.APPLICATION_MODAL);

        Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent()) {
                if (result.get() == opcion1) {
                    reStartInputs();
                    sCont.createSuggestion("Title: "+titleSuggestionField.getText()+"content: "+contentSuggestionField.getText(),parent.controller.getLoggedPerson().getAccount());
                } else if (result.get() == opcion2) {
                    alert.close();
                }

            }
    }
    public void reStartInputs(){
        contentSuggestionField.clear();
        titleSuggestionField.clear();
    }


}
