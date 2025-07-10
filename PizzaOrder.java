public class PizzaOrder {
    private int id;
    private String customerName;
    private String mobileNumber;
    private String pizzaSize;
    private int numToppings;
    private double totalBill;

    public PizzaOrder(int id, String customerName, String mobileNumber, String pizzaSize, int numToppings, double totalBill) {
        this.id = id;
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.pizzaSize = pizzaSize;
        this.numToppings = numToppings;
        this.totalBill = totalBill;
    }

    public PizzaOrder(String customerName, String mobileNumber, String pizzaSize, int numToppings, double totalBill) {
        this(-1, customerName, mobileNumber, pizzaSize, numToppings, totalBill);
    }

    public int getId() { return id; }
    public String getCustomerName() { return customerName; }
    public String getMobileNumber() { return mobileNumber; }
    public String getPizzaSize() { return pizzaSize; }
    public int getNumToppings() { return numToppings; }
    public double getTotalBill() { return totalBill; }

    public void setId(int id) { this.id = id; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    public void setPizzaSize(String pizzaSize) { this.pizzaSize = pizzaSize; }
    public void setNumToppings(int numToppings) { this.numToppings = numToppings; }
    public void setTotalBill(double totalBill) { this.totalBill = totalBill; }
}