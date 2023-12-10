package gui;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    @FXML
    private VBox chatVbox;

    @FXML
    private TextField messageText;

    @FXML
    private Button send;

    public void updateChatBox(ActionEvent event) {
        Label label = new Label(messageText.getText());
        label.getStyleClass().add("label-with-background");
        label.setId("client");
        label.setPadding(new Insets(3, 10, 3, 10));
        label.setWrapText(true);

        HBox hBox=new HBox();
        hBox.getChildren().add(label);
        hBox.setAlignment(Pos.BASELINE_RIGHT);
        HBox.setMargin(label, new Insets(5));

        label.minWidthProperty().bind(Bindings.createDoubleBinding(
                () -> Math.max(label.prefWidth(-1) / 2 + 10, 50),
                label.textProperty(), label.graphicProperty(), label.prefWidthProperty(), label.layoutBoundsProperty()));
        label.maxWidthProperty().bind(Bindings.createDoubleBinding(
                () -> Math.min(label.prefWidth(-1) + 10, 275),
                label.textProperty(), label.graphicProperty(), label.prefWidthProperty(), label.layoutBoundsProperty()));

        chatVbox.getChildren().add(hBox);

        messageText.setText("");

        Label newLabel = new Label("Hello");
        newLabel.getStyleClass().add("newlabel-with-background");
        newLabel.setId("server");
        newLabel.setPadding(new Insets(3, 10, 3, 10));
        newLabel.minWidthProperty().bind(Bindings.createDoubleBinding(
                () -> Math.max(newLabel.prefWidth(-1) / 2 + 10, 50),
                newLabel.textProperty(), newLabel.graphicProperty(), newLabel.prefWidthProperty(), newLabel.layoutBoundsProperty()));
        newLabel.maxWidthProperty().bind(Bindings.createDoubleBinding(
                () -> Math.min(newLabel.prefWidth(-1) + 10, 275),
                newLabel.textProperty(), newLabel.graphicProperty(), newLabel.prefWidthProperty(), newLabel.layoutBoundsProperty()));
        chatVbox.getChildren().add(newLabel);
        VBox.setMargin(newLabel, new Insets(5));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        send.addEventFilter(ActionEvent.ACTION, event -> {
            String message = messageText.getText();
            if (message == null || message.equals("")) {
                event.consume();
            }
        });
        messageText.setOnKeyPressed(event -> {
            if(event.getCode().getName().equals("Enter")){
                String message =  messageText.getText();
                if(message == null || message.equals("")){
                    event.consume();
                } else {
                    updateChatBox(new ActionEvent());
                }
            }
        });
    }
}