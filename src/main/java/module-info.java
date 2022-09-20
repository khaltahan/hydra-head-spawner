module se2203a.assignment1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens se2203a.assignment1 to javafx.fxml;
    exports se2203a.assignment1;
}