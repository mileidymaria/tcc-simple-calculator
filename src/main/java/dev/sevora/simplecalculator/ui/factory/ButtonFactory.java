package dev.sevora.simplecalculator.ui.factory;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ButtonFactory {

    private ButtonFactory() {
    }

    public static Button createButton(String buttonText, int index) {
        Button button = new Button(buttonText);

        button.setFocusTraversable(false); // prevents focusing by TAB
        button.focusedProperty().addListener((observable, old, hasNew) -> {
            if (hasNew) button.getParent().requestFocus();
        });

        button.setId(String.format("button-%d", index)); // useful for CSS, selecting individually
        button.getStyleClass().add("button"); // useful for CSS, selecting all buttons

        // Maximizes the size of each cell in the grid for its element, specifically here the buttons.
        GridPane.setHgrow(button, Priority.ALWAYS);
        GridPane.setVgrow(button, Priority.ALWAYS);

        // This makes the button take the max size of its container.
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        return button;
    }
}