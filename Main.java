import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private TableView<PizzaOrder> table;
    private TextField nameField, mobileField, toppingsField;
    private ToggleGroup sizeGroup;
    private PizzaOrderDAO dao = new PizzaOrderDAO();
    private ObservableList<PizzaOrder> data;

    @Override
    public void start(Stage primaryStage) {
        //Title
        Label title = new Label(" Pizza Ordering System");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        //Input fields
        nameField = new TextField();
        nameField.setPromptText("Customer Name");

        mobileField = new TextField();
        mobileField.setPromptText("Mobile Number");

        toppingsField = new TextField();
        toppingsField.setPromptText("No. of Toppings");

        HBox inputs = new HBox(10, nameField, mobileField, toppingsField);

        //Pizza size checkboxes (as radio buttons)
        sizeGroup = new ToggleGroup();
        RadioButton xl = new RadioButton("XL"); xl.setToggleGroup(sizeGroup);
        RadioButton l = new RadioButton("L"); l.setToggleGroup(sizeGroup);
        RadioButton m = new RadioButton("M"); m.setToggleGroup(sizeGroup);
        RadioButton s = new RadioButton("S"); s.setToggleGroup(sizeGroup);
        HBox sizeBox = new HBox(10, new Label("Size:"), xl, l, m, s);

        //Buttons
        Button createBtn = new Button("Create");
        Button viewBtn = new Button("View");
        Button updateBtn = new Button("Update");
        Button deleteBtn = new Button("Delete");
        Button clearBtn = new Button("Clear");

        createBtn.setOnAction(e -> createOrder());
        viewBtn.setOnAction(e -> loadData());
        updateBtn.setOnAction(e -> updateOrder());
        deleteBtn.setOnAction(e -> deleteOrder());
        clearBtn.setOnAction(e -> clearFields());

        HBox buttonBox = new HBox(10, createBtn, viewBtn, updateBtn, deleteBtn, clearBtn);

        //TableView
        table = new TableView<>();
        table.setPrefHeight(250);
        table.setOnMouseClicked(e -> populateFields());

        TableColumn<PizzaOrder, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<PizzaOrder, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        TableColumn<PizzaOrder, String> mobileCol = new TableColumn<>("Mobile");
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));

        TableColumn<PizzaOrder, String> sizeCol = new TableColumn<>("Size");
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("pizzaSize"));

        TableColumn<PizzaOrder, Integer> toppingsCol = new TableColumn<>("Toppings");
        toppingsCol.setCellValueFactory(new PropertyValueFactory<>("numToppings"));

        TableColumn<PizzaOrder, Double> billCol = new TableColumn<>("Total Bill");
        billCol.setCellValueFactory(new PropertyValueFactory<>("totalBill"));

        table.getColumns().addAll(idCol, nameCol, mobileCol, sizeCol, toppingsCol, billCol);

        VBox root = new VBox(15, title, inputs, sizeBox, buttonBox, table);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 800, 550);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pizza Ordering - Nimesh Neupane");
        primaryStage.show();
    }

    private void createOrder() {
        String name = nameField.getText();
        String mobile = mobileField.getText();
        int toppings = Integer.parseInt(toppingsField.getText());
        RadioButton selected = (RadioButton) sizeGroup.getSelectedToggle();
        if (selected == null) return;
        String size = selected.getText();

        double total = PizzaCalculator.calculateTotal(size, toppings);
        PizzaOrder order = new PizzaOrder(name, mobile, size, toppings, total);
        dao.insert(order);
        loadData();
    }

    private void loadData() {
        data = FXCollections.observableArrayList(dao.getAllOrders());
        table.setItems(data);
    }

    private void deleteOrder() {
        PizzaOrder selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            dao.delete(selected.getId());
            loadData();
        }
    }

    private void updateOrder() {
        PizzaOrder selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        String name = nameField.getText();
        String mobile = mobileField.getText();
        int toppings = Integer.parseInt(toppingsField.getText());
        RadioButton selectedSize = (RadioButton) sizeGroup.getSelectedToggle();
        if (selectedSize == null) return;
        String size = selectedSize.getText();

        double total = PizzaCalculator.calculateTotal(size, toppings);
        PizzaOrder updated = new PizzaOrder(selected.getId(), name, mobile, size, toppings, total);
        dao.update(updated);
        loadData();
    }

    private void populateFields() {
        PizzaOrder selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            nameField.setText(selected.getCustomerName());
            mobileField.setText(selected.getMobileNumber());
            toppingsField.setText(String.valueOf(selected.getNumToppings()));

            for (Toggle t : sizeGroup.getToggles()) {
                if (((RadioButton)t).getText().equals(selected.getPizzaSize())) {
                    sizeGroup.selectToggle(t);
                    break;
                }
            }
        }
    }

    private void clearFields() {
        nameField.clear();
        mobileField.clear();
        toppingsField.clear();
        sizeGroup.selectToggle(null);
        table.getSelectionModel().clearSelection();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
