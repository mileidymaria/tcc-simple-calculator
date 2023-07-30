package dev.sevora.simplecalculator.ui;

import dev.sevora.simplecalculator.ui.factory.ButtonFactory;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.util.Duration;

/**
 * The layout for the GUI of the application.
 * @author Ralph Louis Gopez
 */
public class Layout {
    private Label label;
    private GridPane gridPane;
    private Scene scene;
    private Button[] buttons = new Button[20];
    private static String[] BUTTON_KEYS = { // Could make this static.
        "AC", "+/-", "%", "÷",
        "7", "8", "9", "x",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", "", ".", "="
    };

    /**
     * Creates a layout with the specified size.
     * @param width The width of the layout.
     * @param height The height of the layout.
     */
    public Layout(double width, double height) {
        gridPane = createGridPaneOfSize(4, 6);
        gridPane.setId("root");
        gridPane.setPrefSize(width, height); 

        // Adds spacing on the grid.
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        // This just places the label right.
        label = new Label("0");
        label.setId("label");
        label.setMaxWidth(Double.MAX_VALUE);
        label.setMaxHeight(Double.MAX_VALUE);
        label.setAlignment(Pos.TOP_RIGHT);
        label.setPadding(new Insets(20, 5, 0, 0));
        gridPane.add(label, 0, 0, 4, 1);

        // Places the buttons accordingly.
        fillGridPane(gridPane, 4, 6);

        scene = new Scene(gridPane);
        
        // CSS can be found on the path specified
        scene.getStylesheets().add("stylesheet.css");
    }

    /**
     * Creates a GridPane with the specified number of rows and columns.
     * @param columns The number of columns of the grid.
     * @param rows The number of rows of the grid.
     * @return A GridPane instance.
     */
    protected GridPane createGridPaneOfSize(final int columns, final int rows) {
        GridPane root = new GridPane();

        for (int column = 0; column < columns; ++column) {
            ColumnConstraints constraint = new ColumnConstraints();
            constraint.setPercentWidth(100.0 / columns);
            root.getColumnConstraints().add(constraint);
        }

        for (int row = 0; row < rows; ++row) {
            RowConstraints constraint = new RowConstraints();
            constraint.setPercentHeight(100.0 / rows);
            root.getRowConstraints().add(constraint);
        }

        return root;
    }

    /**
     * This is meant to be called with 4 columns and 5 rows and 
     * it modifies a GridPane by adding the buttons on it.
     * @param root The GridPane to be modified.
     * @param columns The number of columns, should be 4.
     * @param rows The number of rows, should be 5.
     */
    private void fillGridPane(GridPane root, int columns, int rows) {
        int maxIndex = Math.min(columns * rows, buttons.length);
        for (int index = 0; index < maxIndex; index++) {
            if (index == 17) continue; // There's no 17th button.

            String buttonText = Layout.BUTTON_KEYS[index];
            buttons[index] = ButtonFactory.createButton(buttonText);
            positionButton(root, buttons[index], index, columns);
        }
    }

    private void positionButton(GridPane root, Button button, int index, int columns) {
        int x = index % columns;
        int y = index / columns + 1;

        // 16th element is for the 0 button which should take two spaces horizontally instead of one
        if (index == 16) {
            root.add(button, x, y, 2, 1);
        } else {
            root.add(button, x, y);
        }
    }


    /**
     * Getter for the label of the GUI.
     * @return The Label instance of the GUI where the text should be displayed.
     */
    public Label getLabel() {
        return label;
    }

    /**
     * Getter for the buttons of the GUI.
     * @return Array of Buttons, 20 elements, one being null.
     */
    public Button[] getButtons() {
        return buttons;
    }

    /**
     * Getter for the scene of the layout.
     * @return This returns a Scene instance, meant to be rendered by adding it to the stage.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * This makes it appear that a button is being pressed even if it is not,
     * just does that programmatically.
     * @param entry The string that signifies the text a button should have.
     * @param milliseconds A double signifying how many milliseconds it should appear pressed.
     */
    public void simulateClickWithoutEvent(String entry, double milliseconds) {
        for (Button button : buttons) {
            if (button != null && button.getText().equals(entry)) {
                PauseTransition pause = new PauseTransition(Duration.millis(milliseconds));

                button.getStyleClass().add("pressed");

                pause.setOnFinished(event -> {
                    button.getStyleClass().remove("pressed");
                });

                pause.play();
                break;
            }
        }
    }

}
