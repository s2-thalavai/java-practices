import java.util.Arrays;

public class _02_print_diff_type_values {

    public static void main(String[] args) {

		// Primitive Data Types
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

		// Arrays
        int[] numbers = {1, 2, 3, 4};
        System.out.println("Numbers: " + Arrays.toString(numbers));
		
		// Objects
		Team myTeam = new Team(); 
		System.out.printf("Name: %s, Type: %s%n", myTeam.member.getName(), myTeam.member.getType());
		System.out.println(myTeam.toString());		

		Member member = new Member("Siva", "Batsman", 2, 10001);
		Team team = new Team(member); 
		System.out.printf("Name: %s, Type: %s%n", team.member.getName(), team.member.getType());
		System.out.printf(team.toString());		

    }
}

class Team {
	
    Member member;

    Team() {
        this.member = new Member("Default", "Unknown", 0, 0);
    }

    Team(Member member) {
        this.member = member;
    }
	
	public String toString() {
		return "Team{member=" + member + "}";
	}

}


class Member {
	
    private String name;
    private String type;
    private int level;
    private int rank;

    Member(String name, String type, int level, int rank) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.rank = rank;
    }

	// Accessing Private Fields throough getter
    String getName() {
        return name;
    }

    String getType() {
        return type;
    }

    public String toString() {
        return "Member{name='" + name + "', type=" + type + "}";
    }
}

/*

s2tha@THALASI MINGW64 /d/Java/java-practices (main)

	$ javac _02_print_diff_type_values.java

s2tha@THALASI MINGW64 /d/Java/java-practices (main)

	$ java _02_print_diff_type_values.java
	
	Age: 34
	Price: 99.99
	Grade: A
	Available: true
	Count: 100
	Temperature: 36.6
	Message: Hello, Java!
	Numbers: [1, 2, 3, 4]
	Name: Siva, Type: Batsman
	Team{member=Member{name='Siva', type=Batsman}}
	
s2tha@THALASI MINGW64 /d/Java/java-practices (main)


*/