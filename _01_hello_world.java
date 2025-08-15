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
## Compile and Run Instructions


### Compile the program

	javac _01_hello_world.java


### Run the compiled class

	java _01_hello_world


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
