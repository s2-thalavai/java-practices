/*

stack vs heap memory is essential for mastering Java performance, 
memory management, and even debugging tricky issues like 
 
	OutOfMemoryError or 
	
	StackOverflowError.


## Stack Memory in Java

🔹 What It Stores:

		Stores method calls(References to objects (not the objects themselves)), local variables, and primitive values

		Operates in a Last-In-First-Out (LIFO) manner

		Each thread gets its own stack

🔹 Characteristics:

		Fast access, allocation and deallocation

		Automatically managed by JVM — memory is freed when a method exits

		Limited size — deep recursion or large local allocations can cause StackOverflowError
		
		
🔁 Method Calls
	
		What Happens:

			Every time a method is invoked, a stack frame is created.

			This frame holds the method’s parameters, return address, and local variables.

			When the method finishes, the frame is popped off the stack.

	🧪 Example:
	
			public class MethodCallDemo {
				public static void main(String[] args) {
					greet("Siva"); // stack frame for greet() is created
				}

				static void greet(String name) {
					System.out.println("Hello, " + name); // stack frame holds 'name'
				}
			}
			
			
			
			
📦 Local Variables
	
	    What They Are:
		
			Variables declared inside a method.

			Stored in the stack frame of that method.

			Automatically destroyed when the method exits.

		🧪 Example:
		
			public void calculateSum() {
				int a = 10; // stored in stack
				int b = 20; // stored in stack
				int sum = a + b; // also in stack
			}
			
🔢 Primitive Values

		📌  What They Include:
		
				Types like int, double, char, boolean, etc.

				Stored directly in stack memory when used as local variables.

		🧪 Example:

				public void showPrimitives() {
					boolean isActive = true; // stack
					char grade = 'A';        // stack
					double score = 95.5;     // stack
				}


🧠 JVM Stack Memory Flow

	When you call a method:

		A new stack frame is pushed.

		Local variables and primitive values are stored in that frame.

		Once the method completes, the frame is popped, and memory is reclaimed.


In multi-threaded apps (e.g., using ExecutorService or reactive streams), 
each thread has its own stack. 

So stack memory usage scales with thread count  something to watch when tuning thread pools in cloud deployments.



🧱 Heap Memory in Java


	🔹 What It Is:
			
			Stores objects, arrays, and class-level variables

			Shared across all threads			

	🔹 Characteristics:
			
			Slower access than stack (but flexible)

			Garbage collected — memory is reclaimed when objects are no longer referenced

			Large capacity — but can throw OutOfMemoryError if exhausted

	🔹 Example:

			LineItem item = new LineItem("001", "Laptop", 1, new BigDecimal("75000.00"));
			// 'item' reference is on the stack, but the object itself is on the heap
			
			
		


🧪 Visual Analogy

		Memory Type		Stores							Managed By			Speed		Scope

		Stack			Method calls, primitives		JVM (auto)			Very fast	Per thread

		Heap			Objects,arrays, class fields	Garbage Collector	Slower		Shared memory



🧠 Pro Tip for Cloud & DevOps

		When tuning JVM performance (e.g., on Azure VMs or containers), you can configure heap size with:

		-Xms512m -Xmx2048m
		
		And monitor stack usage with profiling tools like VisualVM or JFR.




🔍 Breakdown by Component

		Component					Memory Location				Notes

		productName				Stack						Method parameter stored in the current stack frame
		"Laptop"				String pool (Heap)			Interned string, reused from heap
		equalsIgnoreCase(...)	Stack						Method call executes in stack; uses heap-stored string data
		10.00 / 5.00			Heap (autoboxed Double)		These are boxed into Double objects, which are stored in heap
		Return value			Stack → Heap				Reference to the Double object is returned via stack, but the actual object lives in heap




Summary


	Logical operations and control flow → happen in stack.

	Objects and boxed values → live in heap.

	Method execution context → always resides in stack.

*/
import java.util.List;
import java.util.Objects;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.ArrayList;

public class _03_stack_and_heap_memory {	
	
	 public static void main(String[] args) {
		        
		Product product = new Product("Laptop", 75000.00); // product reference → stored in stack, new Product("Laptop", 75000) → object stored in heap 
		System.out.println("product: " + product);
		
		Double discount =  applyDiscount(product.getName()); // method call → stack frame for main() and applyDiscount() → stored in stack
		System.out.println("Discount applied to product " + product.getName() + " is : " + discount); 
	 }
	
	static Double applyDiscount(String productName) { //productName parameter in applyDiscount() → stored in stack
		System.out.println("productName: " + productName); // stack frame holds this
		return productName.equalsIgnoreCase("Laptop") ? 10.00 : 5.00; // is evaluated entirely within the stack memory, inside the stack frame of the applyDiscount method.
	}

}

class Product {
	
    private final String name;   // stored in heap
    private final Double price;     // stored in heap

    Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }
	
	public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
	
	@Override
    public String toString() {
        return String.format("Product{name='%s', price='%.2f'}",
                name, price);    // runtime
    }
}