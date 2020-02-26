package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import model.ConsoleModel;

import model.TurtleModel;
import model.VariableModel;

import parsing.Parser;

import javax.imageio.ImageIO;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

public class ControlPanel extends VBox {
    private ResourceBundle resources;
    private static final String RESOURCES = "resources/languages";
    private static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES + "/";
    private CommandHistoryView historyView;
    private ConsoleView consoleView;
    private Button runButton;
    private Button clearButton;
    private Button turtleSwitchButton;
    private TurtleView turtleView;
    private Parser parser;
    private String myLanguage;
    private TurtleModel model;
    private Controller c;
    private ConsoleModel cm;

    private VariableModel variableModel;


    public ControlPanel (ResourceBundle resources, CommandHistoryView historyView, ConsoleView consoleView, TurtleView turtleView, String language, Controller c, ConsoleModel cm, TurtleModel model) {
        this.resources = resources;
        this.historyView = historyView;
        this.consoleView = consoleView;
        this.turtleView = turtleView;
        this.myLanguage = language;
        this.model = model;

        this.cm = cm;
        this.c = c;

        this.variableModel = new VariableModel();

        runButton = makeButton("Run", event -> {
            try {
                executeRun();
            } catch (InvocationTargetException e) {
                //e.printStackTrace();
            } catch (IllegalAccessException e) {
                //e.printStackTrace();
            } catch (NoSuchMethodException e) {
                //e.printStackTrace();
            } catch (InstantiationException e) {
                //e.printStackTrace();
            } catch (ClassNotFoundException e) {
                //e.printStackTrace();
            }
        });
        clearButton = makeButton("Clear", event -> clearConsole());
        turtleSwitchButton = makeButton("TurtleSelect", event -> turtleView.switchTurtleImage());
        getChildren().add(runButton);
        getChildren().add(clearButton);
    }
    private Button makeButton(String property, EventHandler<ActionEvent> handler) {
        // represent all supported image suffixes
        final String IMAGEFILE_SUFFIXES = String
                .format(".*\\.(%s)", String.join("|", ImageIO.getReaderFileSuffixes()));
        Button result = new Button();
        String label = resources.getString(property);
        if (label.matches(IMAGEFILE_SUFFIXES)) {
            result.setGraphic(new ImageView(
                    new Image(getClass().getResourceAsStream(DEFAULT_RESOURCE_FOLDER + label))));
        } else {
            result.setText(label);
        }
        result.setOnAction(handler);
        return result;
    }
    public Button getTurtleSwitcher() {
        return this.turtleSwitchButton;
    }
    private void updateInputHistory(String commands){
        historyView.updateHistory(new Text("Input: " + commands), new Text("Output: " + c.getConsoleModel().getReturnVal()));
    }
    private void executeRun() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException, ClassNotFoundException {
        String commands = consoleView.getText();
        resources.getBaseBundleName();

        parser = new Parser(commands, myLanguage, model, variableModel, c.getConsoleModel());

        updateInputHistory(commands);
    }
    private void clearConsole() {
        consoleView.clear();
    }
    public void updateLanguage(ResourceBundle resources, String language) {
        runButton.setText(resources.getString("Run"));
        clearButton.setText(resources.getString("Clear"));
        this.myLanguage = language;
    }


}
