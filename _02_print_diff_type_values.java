import java.util.Objects;
import java.util.Arrays;
import java.math.BigDecimal;

public class _02_print_diff_type_values {

    public static void main(String[] args) {

		// Primitive Data categorys
        int age = 34;
        double price = 99.99;
        char grade = 'A';
        boolean isAvailable = true;

        System.out.println("Age: " + age);
        System.out.println("Price: " + price);
        System.out.println("Grade: " + grade);
        System.out.println("Available: " + isAvailable);

		// Wrapper Classes
        Integer count = Integer.valueOf(100);
        Double temperature = Double.valueOf(36.6);

        System.out.println("Count: " + count);
        System.out.println("Temperature: " + temperature);

		// Strings
        String message = "Hello, Java!";
        System.out.println("Message: " + message);
		message = null;
		System.out.println(Objects.toString(message, "Unknown"));  // prints "Unknown"

		// Arrays
        int[] numbers = {1, 2, 3, 4};
        System.out.println("Numbers: " + Arrays.toString(numbers));
		
		// Objects
		Invoice myInvoice = new Invoice(); 
		System.out.printf("id: %s, category: %s%n", myInvoice.getLineItem().getId(), myInvoice.getLineItem().getCategory());
		System.out.println(myInvoice.toString());		

		LineItem LineItem = new LineItem("Sindhu Sivasankar Thalavai", "Batsman", 2.0, new BigDecimal("10001"));
		Invoice invoice = new Invoice(LineItem); 
		System.out.printf("id: %s, category: %s%n", invoice.getLineItem().getId(), invoice.getLineItem().getCategory());
		System.out.printf(invoice.toString()); // difference	
	}
}

class Invoice {

	// Immutability: LineItem fields final to ensure thread safety and predictability
    private final LineItem LineItem;

    public Invoice() {
        this.LineItem = new LineItem("Default", "Unknown", 0.0, new BigDecimal("0"));
    }

    public Invoice(LineItem LineItem) {
		// Null Safety: Add Objects.requireNonNull() to constructors
        this.LineItem = Objects.requireNonNull(LineItem, "LineItem cannot be null");
    }

    public LineItem getLineItem() {
        return LineItem;
    }

    @Override
    public String toString() {
        return String.format("Invoice{LineItem=%s}", LineItem);
    }
}


class LineItem {

    private final String id;
    private final String category;
    private final Double quantity;
    private final BigDecimal unitPrice;

    public LineItem(String id, String category, Double quantity, BigDecimal unitPrice) {
		// Null Safety: Add Objects.requireNonNull() to constructors
        this.id = Objects.requireNonNull(id, "id cannot be null");
        this.category = Objects.requireNonNull(category, "category cannot be null");
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }	
 
    public String getId() {
		if(id.length()>16)
			return "id is too large!";
		else
			return id;
    }

    public String getCategory() {
        return category;
    }

    public Double getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
	
	@Override
	public String toString() {
		return String.format("LineItem{id='%s', category='%s', quantity=%.2f, unitPrice=%s}",
							 id, category, quantity, unitPrice.toPlainString());
	}

}


/*

s2tha@THALASI MINGW64 /d/Java/java-practices (main)

	$ javac _02_print_diff_category_values.java

s2tha@THALASI MINGW64 /d/Java/java-practices (main)

	$ java _02_print_diff_category_values.java
	
	Age: 34
	Price: 99.99
	Grade: A
	Available: true
	Count: 100
	Temperature: 36.6
	Message: Hello, Java!
	Unknown
	Numbers: [1, 2, 3, 4]
	id: Default, category: Unknown
	Invoice{LineItem=LineItem{id='Default', category='Unknown', quantity=0, unitPrice=0}}
	id: Siva, category: Batsman
	Invoice{LineItem=LineItem{id='Siva', category='Batsman', quantity=2, unitPrice=10001}}
		
s2tha@THALASI MINGW64 /d/Java/java-practices (main)


*/