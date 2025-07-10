public class PizzaCalculator {
    public static double calculateTotal(String size, int toppings) {
        double basePrice = 0;
        switch (size) {
            case "XL": basePrice = 15.00; break;
            case "L": basePrice = 12.00; break;
            case "M": basePrice = 10.00; break;
            case "S": basePrice = 8.00; break;
            default: basePrice = 0.0;
        }

        double toppingsCost = toppings * 1.50;
        double subtotal = basePrice + toppingsCost;
        double totalWithTax = subtotal * 1.15; // 15% HST

        return Math.round(totalWithTax * 100.0) / 100.0; // Round to 2 decimals
    }

    // Examples:
    public static void main(String[] args) {
        System.out.println("XL + 3 toppings: $" + calculateTotal("XL", 3)); // 15 + 4.5 = 19.5 * 1.15 = 22.42
        System.out.println("M + 2 toppings: $" + calculateTotal("M", 2));   // 10 + 3 = 13 * 1.15 = 14.95
    }
}
