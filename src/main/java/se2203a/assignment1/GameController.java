package se2203a.assignment1;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameController {
    // Private variable to reference the components that have a fx:id in the FXML file
    @FXML
    private Slider slider;
    @FXML
    private Button playBtn;
    @FXML
    private GridPane gridPane;
    @FXML
    private Text gameOverTxt;

    // Counter to keep track of heads cut
    int totalNumberOfHeadsCut = 0;

    /* A 2D-array to track available nodes in order to avoid overlapping.
    This array can contain only two variables:
     - An int 0 which indicates that node is empty.
     - An int 1 which indicates that node is occupied.
    All elements of the array are initialized to 0, indicating that all nodes are empty.*/
    int[][] takenNodes = new int[16][16];


    // Method to preform a set of events once the Play Button is clicked
    @FXML
    protected void onPlayBtnClick() {
        // Get the value of the Slider, and cast it to an int as the only possible values are 2, 3, 4, 5, and 6
        int headSize = (int) slider.getValue();

        // Call the method that spans a Hydra Head with the size obtained from the Slider
        hydraSpawner(headSize);

        // Disable the Play Button and the Slider upon starting the game
        playBtn.setDisable(true);
        slider.setDisable(true);
    }

    // Method to spawn a Hydra Head of the received int size
    public void hydraSpawner(int headSize) {
        // Locate the correct HeadSize png from resources folder and create an Image class instance
        Image headImg = new Image("file:src/main/resources/se2203a/assignment1/HeadSize" +  headSize + ".png");
        // Create an ImageView instance
        ImageView hydraIcon = new ImageView();
        // Set the ID of the head to be a String value of its size
        hydraIcon.setId(String.valueOf(headSize));
        // Set the image of the ImageView instance to be the image of the Hydra Head
        hydraIcon.setImage(headImg);
        // Set Width of Head
        hydraIcon.setFitWidth(40);
        // Set Height of Head
        hydraIcon.setFitHeight(40);

        // The range of possible row and column values of the Grid Pane
        int[] gridRange = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        /* Call a method twice that returns a random int twice, and set each
        int to the row and column of where the Hydra Head is to be place */
        int firstHydraSpanRow = getRandom(gridRange);
        int firstHydraSpanColumn = getRandom(gridRange);

        /* If the location of the node is equal to 1, this means that the location is already taken by a Hydra Head.
        Keep regenerating new rows and columns until the Node is equal to 0 (an empty Node has been located) */
        while (takenNodes[firstHydraSpanRow][firstHydraSpanColumn] == 1) {
            firstHydraSpanRow = getRandom(gridRange);
            firstHydraSpanColumn = getRandom(gridRange);
        }

        // Add the Hydra Head at the selected row and column
        gridPane.add(hydraIcon, firstHydraSpanRow, firstHydraSpanColumn);
        // Set the row and column of the 2D-array element to 1 to indicate that this location is not empty
        takenNodes[firstHydraSpanRow][firstHydraSpanColumn] = 1;

    }

    // Method to accept an array of type int and return a random integer
    public static int getRandom(int[] array) {
        int random = new Random().nextInt(array.length);
        return array[random];
    }

    // A method to preform a set of events once Grid Pane is clicked on
    @FXML
    protected void clickGrid(javafx.scene.input.MouseEvent event) {
        // Identify the Node that was clicked
        Node clickedNode = event.getPickResult().getIntersectedNode();

        /* In order to avoid errors, detect whether the Node that was selected is empty or not. Check if the Node is
        empty by using hasProperties() method. This method will return true if a Hydra Head is in that location, and
        it will continue with the next set of events */
        if(clickedNode.hasProperties()) {
            // Get the row and column of the removed Node, and set the element of 2D-array to 0 (i.e. set it to empty)
            int removedRow = GridPane.getRowIndex(clickedNode);
            int removedColumn = GridPane.getColumnIndex(clickedNode);

            // Remove the selected Node
            gridPane.getChildren().remove(clickedNode);

            // Set the row and column of the 2D-array element to 0 to indicate that this location is empty
            takenNodes[removedRow][removedColumn] = 0;

            // Increment counter of the number of heads
            totalNumberOfHeadsCut++;
        }

        /* Get ID of the selected Node and identify its size. If it's of size 2 or greater, call the getTwoOrThree
        method  to return a randomly assigned number of either 2 or 3, and spawn the nodes of smaller size. */
        String headId = clickedNode.getId();
        switch (headId) {
            case "1":
                // If this was the last Head clicked, the if-statement will be true and complete the following events
                if (gridPane.getChildren().toString() == "[]") {
                    gameOverTxt.setVisible(true);
                    gameOverTxt.setText("Good Job! - You have cut " + totalNumberOfHeadsCut + " Hydra Heads");
                }
                break;
            case "2":
                if (getTwoOrThree() == 2) {
                    for (int i = 0; i < 2; i++) {
                        hydraSpawner(1);
                    }
                }
                else {
                    for (int i = 0; i < 3; i++) {
                        hydraSpawner(1);
                    }
                }
                break;
            case "3":
                if (getTwoOrThree() == 2) {
                    for (int i = 0; i < 2; i++) {
                        hydraSpawner(2);
                    }
                }
                else {
                    for (int i = 0; i < 3; i++) {
                        hydraSpawner(2);
                    }
                }
                break;
            case "4":
                if (getTwoOrThree() == 2) {
                    for (int i = 0; i < 2; i++) {
                        hydraSpawner(3);
                    }
                }
                else {
                    for (int i = 0; i < 3; i++) {
                        hydraSpawner(3);
                    }
                }
                break;
            case "5":
                if (getTwoOrThree() == 2) {
                    for (int i = 0; i < 2; i++) {
                        hydraSpawner(4);
                    }
                }
                else {
                    for (int i = 0; i < 3; i++) {
                        hydraSpawner(4);
                    }
                }
                break;
            case "6":
                if (getTwoOrThree() == 2) {
                    for (int i = 0; i < 2; i++) {
                        hydraSpawner(5);
                    }
                }
                else {
                    for (int i = 0; i < 3; i++) {
                        hydraSpawner(5);
                    }
                }
                break;
        }

    }

    // Method to either return an int 2 or 3
    public int getTwoOrThree() {
        // Make an ArrayList of type Integer
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        // Add 2 and 3 to the list
        numbers.add(2);
        numbers.add(3);
        // Shuffle the Integers in order to create chance during selection
        Collections.shuffle(numbers);
        // Select and return the first element of the list
        int randomlyAssignedInt = numbers.get(0);
        return randomlyAssignedInt;
    }

    // Method to preform a set of events once the Reset Button is clicked
    @FXML
    protected void onRstBtnClick() {
        // Set Slider to 4
        slider.setValue(4);
        // Enable the Play Button and the Slider
        playBtn.setDisable(false);
        slider.setDisable(false);
        // Hide Game Over Text
        gameOverTxt.setVisible(false);
        // Clear all heads on the grid
        gridPane.getChildren().clear();
        // Reset the counter
        totalNumberOfHeadsCut = 0;
    }
}