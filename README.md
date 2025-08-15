# java-practices

	“write once, run anywhere.” 

	Java is cross-platform programming language. 
    compiled and interpreted to balance performance, portability, and security.

	How Java Is Compiled and Interpreted

		Phase							Tool							Purpose

		Compilation						javac							Converts .java source code into .class bytecode (platform-independent)
		Interpretation / Execution		JVM (Java Virtual Machine)		Reads and executes bytecode, either by interpreting or compiling it further (JIT)


	JVM’s role as both a 
	
		1. runtime guardian and a
		
		2. performance optimizer
		
		

Let’s break down how it enforces runtime checks, memory safety, and access controls, 
and how can leverage or extend these mechanisms in own systems.

1.  Runtime Checks: JVM as the Gatekeeper

		The JVM performs several runtime validations to prevent illegal operations:

		Null checks: Throws NullPointerException if you dereference a null.

		Array bounds checks: Ensures you don’t access outside array limits (ArrayIndexOutOfBoundsException).

		Type safety: Enforces strict type casting rules (ClassCastException).

		Bytecode verification: Before execution, the JVM verifies that the .class file adheres to the JVM spec — no illegal instructions, stack overflows, or type mismatches.

		can inspect bytecode verification using tools like javap -verify or enable strict verification flags in JVM startup.

2.  Memory Safety: No Manual Memory Management

		Java avoids common memory bugs by design:

		No pointer arithmetic: You can’t manipulate memory addresses directly.

		Automatic garbage collection: JVM tracks object lifetimes and reclaims unused memory.

		Stack and heap isolation: JVM manages memory regions with strict boundaries.

		Thread-local stacks: Prevents data races and stack corruption.

		JVM implementations like HotSpot use safe memory models and barriers to ensure visibility and ordering across threads (especially with volatile and synchronized).

3. 	Access Controls: Security Manager & ClassLoader Isolation

		Java enforces access restrictions at multiple levels:

		Language-level access modifiers: private, protected, package-private, and public are enforced by the JVM.

		Security Manager (legacy but still relevant): Controls file access, network access, reflection, and more.

		ClassLoader isolation: Prevents classes from accessing or overriding classes from other modules or packages.

		Module system (Java 9+): Enforces encapsulation across modules (exports, requires, etc.).


can define custom ClassLoaders to sandbox plugins or tenant-specific logic in multi-tenant SaaS platforms.


In Java, the code is compiled by the Java Compiler, specifically the javac tool provided by the Java Development Kit (JDK). 

Here's how it works behind the scenes:


### Why This Dual Approach?

	1. Portability via Bytecode

		Java source is compiled into bytecode, not native machine code.

		Bytecode is universal — it runs on any device with a JVM (Windows, Linux, macOS, etc.).

		This abstraction layer is what makes Java cross-platform.

	2. Performance via JIT Compilation

		The JVM can interpret bytecode line-by-line (slower but flexible).

		But modern JVMs use Just-In-Time (JIT) compilers to convert hot code paths into native machine code at runtime.

		This hybrid model gives Java near-native performance for long-running apps.


	3. Security and Sandboxing

		Bytecode allows the JVM to enforce runtime checks, memory safety, and access controls.

		This is especially useful in environments like browsers (historically via applets) or cloud runtimes.

	4. Tooling and Optimization

		Bytecode enables powerful tools: profilers, debuggers, bytecode instrumentation, and runtime monitoring.

		JVMs can optimize execution based on actual usage patterns (adaptive optimization).


### Who Does the Compiling?

	You trigger compilation by running javac MyClass.java.

	javac (Java Compiler) reads your .java source file.

	It performs lexical analysis, parsing, semantic checks, and then generates a .class file containing JVM bytecode.

	This bytecode is what the Java Virtual Machine (JVM) executes—not your original Java code.

### What Happens During Compilation?

	When you compile a .java file using javac, here’s the flow:

	Lexical Analysis: Your code is tokenized — keywords, identifiers, literals, etc.

	Parsing: The compiler builds an Abstract Syntax Tree (AST) to understand structure.

	Semantic Analysis: Type checking, scope resolution, and symbol table generation.

	Bytecode Generation: The AST is translated into JVM bytecode and written into a .class file.

	Class Loading: At runtime, the JVM loads the .class file using a ClassLoader.

	Verification: Bytecode is checked for security and correctness.

	Execution: The JVM interprets or JIT-compiles the bytecode into native machine code.
	

### Who Owns javac?

javac is part of the OpenJDK or Oracle JDK, depending on your distribution.

It’s written in Java itself and can even be invoked programmatically via the javax.tools.JavaCompiler API.


## Compile and Run Instructions


### Compile the program

	javac _01_hello_world.java


### Run the compiled class

	java _01_hello_world


### What’s Inside a .class File?

	A .class file is the compiled bytecode version of your .java source file. 

	It’s not human-readable like Java code, but it’s structured in a very specific binary format that 
	the Java Virtual Machine (JVM) understands.

	Here’s what it typically contains:

	Section	Description

		Magic Number	Identifies the file as a valid .class file (0xCAFEBABE)
		Version Info	Specifies the Java version used to compile the class
		Constant Pool	A table of constants (strings, class names, method names, etc.)
		Access Flags	Indicates if the class is public, abstract, final, etc.
		Class Info		The name of the class and its superclass
		Interfaces		Any interfaces the class implements
		Fields			Metadata about class variables
		Methods			Bytecode instructions for each method
		Attributes		Extra info like line numbers, annotations, and debugging symbols



### Peek Inside

	inspect a .class file using:

	javap -c ClassName

	This disassembles the bytecode and shows you the instructions like aload_0, invokevirtual, etc.

	
	s2tha@THALASI MINGW64 /d/Java/java-practices (main)

	$ javap -c _01_hello_world
	
	Compiled from "_01_hello_world.java"
	public class _01_hello_world {
	  public _01_hello_world();
		Code:
		   0: aload_0
		   1: invokespecial #1                  // Method java/lang/Object."<init>":()V
		   4: return

	  public static void main(java.lang.String[]);
		Code:
		   0: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
		   3: aload_0
		   4: arraylength
		   5: invokedynamic #13,  0             // InvokeDynamic #0:makeConcatWithConstants:(I)Ljava/lang/String;
		  10: invokevirtual #17                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
		  13: ldc           #23                 // String
		  15: astore_1
		  16: ldc           #23                 // String
		  18: astore_2
		  19: aload_0
		  20: invokestatic  #25                 // Method containsArgumentsOrFlag:([Ljava/lang/String;)Z
		  23: ifne          27
		  26: return
		  27: iconst_0
		  28: istore_3
		  29: iload_3
		  30: aload_0
		  31: arraylength
		  32: if_icmpge     117
		  35: ldc           #31                 // String --name
		  37: aload_0
		  38: iload_3
		  39: aaload
		  40: invokevirtual #33                 // Method java/lang/String.equals:(Ljava/lang/Object;)Z
		  43: ifeq          66
		  46: iload_3
		  47: iconst_1
		  48: iadd
		  49: aload_0
		  50: arraylength
		  51: if_icmpge     66
		  54: aload_0
		  55: iload_3
		  56: iconst_1
		  57: iadd
		  58: aaload
		  59: astore_1
		  60: iinc          3, 1
		  63: goto          111
		  66: ldc           #39                 // String --alias
		  68: aload_0
		  69: iload_3
		  70: aaload
		  71: invokevirtual #33                 // Method java/lang/String.equals:(Ljava/lang/Object;)Z
		  74: ifeq          97
		  77: iload_3
		  78: iconst_1
		  79: iadd
		  80: aload_0
		  81: arraylength
		  82: if_icmpge     97
		  85: aload_0
		  86: iload_3
		  87: iconst_1
		  88: iadd
		  89: aaload
		  90: astore_2
		  91: iinc          3, 1
		  94: goto          111
		  97: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
		 100: aload_0
		 101: iload_3
		 102: aaload
		 103: invokedynamic #41,  0             // InvokeDynamic #1:makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
		 108: invokevirtual #17                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
		 111: iinc          3, 1
		 114: goto          29
		 117: aload_1
		 118: ifnull        131
		 121: aload_1
		 122: invokevirtual #44                 // Method java/lang/String.trim:()Ljava/lang/String;
		 125: invokevirtual #48                 // Method java/lang/String.isEmpty:()Z
		 128: ifeq          136
		 131: ldc           #52                 // String Guest
		 133: goto          140
		 136: aload_1
		 137: invokevirtual #44                 // Method java/lang/String.trim:()Ljava/lang/String;
		 140: astore_1
		 141: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
		 144: aload_1
		 145: aload_2
		 146: invokedynamic #54,  0             // InvokeDynamic #2:makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
		 151: invokevirtual #17                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
		 154: return
	}


###  enforce stricter type checking in project

1. Enable Compiler Warnings
   
	Use the -Xlint family of flags during compilation to catch unsafe or unchecked operations:
	
		javac -Xlint:unchecked -Xlint:rawtypes -Xlint:cast -Xlint:deprecation YourFile.java
	
 	Or for all warnings:
	
		javac -Xlint:all YourFile.java

   configure this in your build tool:

	Maven (pom.xml):
	
	xml
	
	 	<compilerArgs>
		  <arg>-Xlint:all</arg>
		</compilerArgs>
 
	Gradle (build.gradle):
	
	groovy

		tasks.withType(JavaCompile) {
		    options.compilerArgs << "-Xlint:all"
		}

2. Avoid Raw Types

Always use generics:

	List<String> names = new ArrayList<>(); // ✅
	List names = new ArrayList();           // ❌ triggers unchecked warning

3. Use @SuppressWarnings Sparingly
   
	If you must suppress warnings, be precise:

		@SuppressWarnings("unchecked")
		List<String> safeCast(Object obj) {
		    return (List<String>) obj;
		}
	Avoid blanket suppression unless absolutely necessary.

4. Use Optional Instead of Nulls

     Avoid null where possible:

		Optional<String> name = Optional.of("Siva");

     This enforces presence checks and avoids NullPointerException.


## Stack and Heap memory tuning in java

🧠 JVM Memory Overview
		
		Memory Area			Purpose										Tunable?
		
		Heap				Stores objects and class instances			✅ Yes
		Stack				Stores method frames and local variables	✅ Yes
		Metaspace			Stores class metadata (Java 8+)				✅ Yes
		Code Cache			Stores compiled bytecode					✅ Yes

🔧 Heap Memory Tuning

	Key Flags:
	
		-Xms512m         # Initial heap size
		-Xmx2048m        # Maximum heap size
		-XX:+UseG1GC     # Use G1 Garbage Collector
		-XX:MaxGCPauseMillis=200  # Target GC pause time

	Strategy:

		Set -Xms = -Xmx for predictable performance in production.
		Use G1GC for balanced throughput and pause times.
		Monitor GC logs to adjust heap size based on allocation rate and GC frequency.

###  Example for a Spring Boot microservice:

		java -Xms1024m -Xmx2048m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:+PrintGCDetails 


 

 
