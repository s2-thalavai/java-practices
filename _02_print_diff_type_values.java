/*

🧩 Primitive vs Wrapper Types in Java

		Feature					Primitive Type					Wrapper Class

		Definition				Basic built-in data types		Object representation of primitives
		Stored in				Stack memory					Heap memory
		Nullability				Cannot be null					Can be null
		Default Values			e.g., int → 0					e.g., Integer → null
		Use in Collections		❌ Not allowed					✅ Required (e.g., List<Integer>)
		Autoboxing/Unboxing		❌ Manual conversion needed		✅ Automatic conversion supported
		Performance	Faster, 	lightweight	Slightly 			slower (object overhead)
		Methods Available		None							Rich API (e.g., Integer.parseInt())


🔧 Examples


When to Use What?

	Use primitives for performance-critical code and simple calculations.

	Use wrappers when working with:

		Collections (List, Map, etc.)

		Generics

		APIs that expect objects

		Nullable values
		

🔄 Autoboxing & Unboxing

	Java automatically converts between primitives and wrappers:

	List<Integer> ages = new ArrayList<>();
	ages.add(25); // Autoboxing: int → Integer

	int age = ages.get(0); // Unboxing: Integer → int

*/
import java.util.List;
import java.util.Objects;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.ArrayList;

public class _02_print_diff_type_values {

    public static void main(String[] args) {

		// Primitive Data categorys
        int age = 34;
        double price = 99.990;
        char grade = 'A';
        boolean isAvailable = true;

        System.out.println("Age: " + age);
        System.out.println("Price: " + price);
        System.out.println("Grade: " + grade);
        System.out.println("Available: " + isAvailable);

		// Wrapper Classes
        Integer countObj = Integer.valueOf(100);
        Double temperatureObj = Double.valueOf(36.6);

        System.out.println("Count: " + countObj);
        System.out.println("Temperature: " + temperatureObj);

		// Strings
        String message = "Hello, Java!";
        System.out.println("Message: " + message);
		message = null;
		System.out.println(Objects.toString(message, "Unknown"));  // prints "Unknown"

		// Arrays
        int[] numbers = {1, 2, 3, 4};
        System.out.println("Numbers: " + Arrays.toString(numbers));
		
		// Objects
		LineItem item1 = new LineItem("001", "Laptop", 1, new BigDecimal("75000.00"));
        LineItem item2 = new LineItem("002", "Mouse", 2, new BigDecimal("499.99"));

        Invoice myInvoice = new Invoice("INV-1001", Arrays.asList(item1, item2));		
		System.out.println(myInvoice.toString());		
		
		LineItem item3 = new LineItem("003", "Keyboard", 1, new BigDecimal("899.99"));
		Invoice myInvoice1 = new Invoice("INV-1002", Arrays.asList(item3));
		System.out.println(myInvoice1);

		LineItem firstItem = myInvoice.getLineItems().get(0);
		System.out.printf("id: %s, category: %s%n", firstItem.getId(), firstItem.getDescription());
		
		List<Invoice> invoiceList = new ArrayList<>();
		invoiceList.add(myInvoice);
		invoiceList.add(myInvoice1);
		
		invoiceList.forEach(System.out::println);

		BigDecimal grandTotal = invoiceList.stream()
			.flatMap(invoice -> invoice.getLineItems().stream())
			.map(LineItem::getTotalPrice)
			.reduce(BigDecimal.ZERO, BigDecimal::add);

		System.out.println("Grand Total Across All Invoices: " + grandTotal);

	}
}

class Invoice {

    private final String invoiceId;
    private final List<LineItem> lineItems;

    public Invoice(String invoiceId, List<LineItem> lineItems) {
        this.invoiceId = Objects.requireNonNull(invoiceId, "invoiceId cannot be null");
        this.lineItems = Objects.requireNonNull(lineItems, "lineItems cannot be null");
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public BigDecimal getTotalAmount() {
        return lineItems.stream()
                    .map(LineItem::getTotalPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice{id='").append(invoiceId).append("', total=").append(getTotalAmount()).append("}\n");
        for (LineItem item : lineItems) {
            sb.append("  ").append(item).append("\n");
        }
        return sb.toString();
    }
}

class LineItem {

    private final String id;
    private final String description;
    private final int quantity;
    private final BigDecimal unitPrice;

    public LineItem(String id, String description, int quantity, BigDecimal unitPrice) {
        this.id = Objects.requireNonNull(id, "id cannot be null");
        this.description = Objects.requireNonNull(description, "description cannot be null");
        this.quantity = quantity;
        this.unitPrice = Objects.requireNonNull(unitPrice, "unitPrice cannot be null");
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toString() {
        return String.format("LineItem{id='%s', description='%s', quantity=%d, unitPrice=%s, total=%s}",
                id, description, quantity, unitPrice.toPlainString(), getTotalPrice().toPlainString());
    }
}



/*

s2tha@THALASI MINGW64 /d/Java/java-practices (main)

	$ javac _02_print_diff_category_values.java

s2tha@THALASI MINGW64 /d/Java/java-practices (main)

	$ java _02_print_diff_category_values.java
	
	Age: 34
	Price: 99.99
	Grade: AAvailable: true
	Count: 100
	Temperature: 36.6
	Message: Hello, Java!
	Unknown
	Numbers: [1, 2, 3, 4]
	Invoice{id='INV-1001', total=75999.98}
	  LineItem{id='001', description='Laptop', quantity=1, unitPrice=75000.00, total=75000.00}
	  LineItem{id='002', description='Mouse', quantity=2, unitPrice=499.99, total=999.98}

	id: 003, category: Keyboard
	Invoice{id='INV-1002', total=899.99}
	LineItem{id='003', description='Keyboard', quantity=1, unitPrice=899.99, total=899.99}
		
s2tha@THALASI MINGW64 /d/Java/java-practices (main)


*/