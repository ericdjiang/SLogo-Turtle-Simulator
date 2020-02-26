package view;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ResourceBundle;

public class VariableView extends ScrollPane {
    private VBox content = new VBox();
    private ResourceBundle resources;

    public VariableView(ResourceBundle resources) throws IOException {
        this.resources = resources;
        setContent(content);
    }
    public void addVariable(Text t) {
        this.content.getChildren().add(t);
    }




}
