// Defines the class named _01_hello_world
public class _01_hello_world {

    // Entry point of the Java application
    public static void main(String[] args) {

        // Prints the number of command-line arguments
        System.out.println("Number of arguments / flags : " + args.length);

        String name = "";
        String alias = "";

        // Help flag check
        if (!containsArgumentsOrFlag(args)) {
            return;
        }

        // Parse arguments
        for (int i = 0; i < args.length; i++) {
            if ("--name".equals(args[i]) && i + 1 < args.length) {
                name = args[i + 1];
                i++; // Skip next arg since it's the value
            } else if ("--alias".equals(args[i]) && i + 1 < args.length) {
                alias = args[i + 1];
                i++; // Skip next arg since it's the value
            } else {
                System.out.println("Unknown argument: " + args[i]);
            }
        }

        // Trim and fallback to "Guest" if name is empty
        name = (name == null || name.trim().isEmpty()) ? "Guest" : name.trim();

        // Greet the user
        System.out.println("Hello Mr. '" + name + "' @ '" + alias + "' , welcome to Java World");
    }

    // Utility method to check for help flag or empty args
	private static boolean containsArgumentsOrFlag(String[] args) {        
		if (args.length == 0) {
			printUsage();
			return false;
		}

		for (String arg : args) {
			if ("--help".equals(arg)) {
				printUsage();
				return false;
			}        
		}

		return true;
	}

    // Help message
    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  --name <your name>     Specify your name");
        System.out.println("  --alias <your alias>   Specify your alias");
        System.out.println("  --help                 Show this help message");
    }
}


/*

### Outputs:

s2tha@THALASI MINGW64 /d/Java/java-practices (main)

	$ javac _01_hello_world.java

s2tha@THALASI MINGW64 /d/Java/java-practices (main)

	$ java _01_hello_world
	Number of arguments / flags : 0
	Usage:
	  --name <your name>     Specify your name
	  --alias <your alias>   Specify your alias
	  --help                 Show this help message

s2tha@THALASI MINGW64 /d/Java/java-practices (main)

	$ java _01_hello_world --help
	Number of arguments / flags : 1
	Usage:
	  --name <your name>     Specify your name
	  --alias <your alias>   Specify your alias
	  --help                 Show this help message

s2tha@THALASI MINGW64 /d/Java/java-practices (main)

	$ java _01_hello_world --name 'sivasankar thalavai' --alias thalasi
	Number of arguments / flags : 4
	Hello Mr. 'sivasankar thalavai' @ 'thalasi', welcome to Java World

s2tha@THALASI MINGW64 /d/Java/java-practices (main)

	$ java _01_hello_world --name '' --alias thalasi
	Number of arguments / flags : 4
	Hello Mr. Guest @ 'thalasi', welcome to Java World


*/



/* 

🧠 What’s Inside a .class File?

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


⚙️ What Happens Behind the Scenes?

	When you compile a .java file using javac, here’s the flow:

	Lexical Analysis: Your code is tokenized — keywords, identifiers, literals, etc.

	Parsing: The compiler builds an Abstract Syntax Tree (AST) to understand structure.

	Semantic Analysis: Type checking, scope resolution, and symbol table generation.

	Bytecode Generation: The AST is translated into JVM bytecode and written into a .class file.

	Class Loading: At runtime, the JVM loads the .class file using a ClassLoader.

	Verification: Bytecode is checked for security and correctness.

	Execution: The JVM interprets or JIT-compiles the bytecode into native machine code.
	

🧪 Want to Peek Inside?

You can inspect a .class file using:

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

s2tha@THALASI MINGW64 /d/Java/java-practices (main)


*/