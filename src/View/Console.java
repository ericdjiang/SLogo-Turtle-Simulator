package View;

import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.util.ResourceBundle;

public class Console extends TextArea {
    private final ResourceBundle myResources;

    public Console(ResourceBundle resources) {
        this.myResources = resources;
        this.setPromptText(myResources.getString("EnterText"));
    }
    public void updateLanguage(ResourceBundle resources) {
        this.setPromptText(resources.getString("EnterText"));
    }

}
