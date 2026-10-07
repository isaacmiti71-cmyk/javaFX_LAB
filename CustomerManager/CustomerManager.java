import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerManager extends Application {

// Customer list
private final ObservableList<Customer> customers =
FXCollections.observableArrayList();

@Override
public void start(Stage stage) {

// =========================
// TITLE
// =========================
Label title = new Label("Customer Manager");
title.setStyle(
"-fx-font-size: 24px;" +
"-fx-font-weight: bold;"
);

// =========================
// NAME INPUT
// =========================
Label nameLabel = new Label("Name:");

TextField nameField = new TextField();
nameField.setPromptText("Enter customer name");


HBox nameRow = new HBox(10);
nameRow.getChildren().addAll(
nameLabel,
nameField
);

// =========================
// PROVINCE INPUT
// =========================
Label provinceLabel = new Label("Province:");

ComboBox<String> provinceBox = new ComboBox<>();

provinceBox.getItems().addAll(
"Central",
"Copperbelt",
"Eastern",
"Luapula",

"Lusaka",
"Muchinga",
"Northern",
"North-Western",
"Southern",
"Western"
);

provinceBox.setPromptText("Select province");

HBox provinceRow = new HBox(10);
provinceRow.getChildren().addAll(
provinceLabel,
provinceBox
);

// =========================
// TABLE
// =========================
TableView<Customer> table = new 

TableView<>();

TableColumn<Customer, String> nameColumn =
new TableColumn<>("Name");

nameColumn.setCellValueFactory(
new PropertyValueFactory<>("name")
);

TableColumn<Customer, String> provinceColumn =
new TableColumn<>("Province");

provinceColumn.setCellValueFactory(
new PropertyValueFactory<>("province")
);

table.getColumns().addAll(
nameColumn,
provinceColumn

);

table.setItems(customers);

table.setColumnResizePolicy(
TableView.CONSTRAINED_RESIZE_POLICY
);

// =========================
// ADD BUTTON
// =========================
Button addButton = new Button("Add Customer");

addButton.setOnAction(event -> {

String name = nameField.getText().trim();
String province = provinceBox.getValue();

if (name.isEmpty() || province == null) {


showAlert(
Alert.AlertType.ERROR,
"Invalid Input",
"Please enter a name and select a province."
);

return;
}

Customer customer =
new Customer(name, province);

customers.add(customer);

nameField.clear();
provinceBox.setValue(null);
});

// =========================

// DELETE BUTTON
// =========================
Button deleteButton =
new Button("Delete Customer");

deleteButton.setOnAction(event -> {

Customer selectedCustomer =
table.getSelectionModel()
.getSelectedItem();

if (selectedCustomer == null) {

showAlert(
Alert.AlertType.WARNING,
"No Selection",
"Please select a customer to delete."
);

return;

}

Alert confirmation =
new Alert(Alert.AlertType.CONFIRMATION);

confirmation.setTitle("Delete Customer");
confirmation.setHeaderText(null);

confirmation.setContentText(
"Are you sure you want to delete "
+ selectedCustomer.getName()
+ "?"
);

confirmation.showAndWait()
.ifPresent(response -> {

if (response == ButtonType.OK) {
customers.remove(selectedCustomer);
}

});
});

// =========================
// BUTTON ROW
// =========================
HBox buttonRow = new HBox(10);

buttonRow.getChildren().addAll(
addButton,
deleteButton
);

// =========================
// MAIN LAYOUT
// =========================
VBox layout = new VBox(15);

layout.setPadding(new Insets(20));

layout.getChildren().addAll(
title,
nameRow,
provinceRow,
buttonRow,
table
);

// =========================
// SCENE
// =========================
Scene scene =
new Scene(layout, 600, 500);

stage.setTitle("Customer Manager");
stage.setScene(scene);
stage.show();
}

// =========================

// ALERT METHOD
// =========================
private void showAlert(
Alert.AlertType type,
String title,
String message) {

Alert alert = new Alert(type);

alert.setTitle(title);
alert.setHeaderText(null);
alert.setContentText(message);

alert.showAndWait();
}

// =========================
// MAIN METHOD
// =========================
public static void main(String[] args) {

launch(args);
}

// =========================
// CUSTOMER CLASS
// =========================
public static class Customer {

private final String name;
private final String province;

public Customer(
String name,
String province) {

this.name = name;
this.province = province;
}

public String getName() {

return name;
}

public String getProvince() {
return province;
}
}
}
