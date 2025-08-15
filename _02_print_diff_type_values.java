import java.util.Objects;
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
		message = null;
		System.out.println(Objects.toString(message, "Unknown"));  // prints "Unknown"

		// Arrays
        int[] numbers = {1, 2, 3, 4};
        System.out.println("Numbers: " + Arrays.toString(numbers));
		
		// Objects
		Team myTeam = new Team(); 
		System.out.printf("Name: %s, Type: %s%n", myTeam.getMember().getName(), myTeam.getMember().getType());
		System.out.println(myTeam.toString());		

		Member member = new Member("Siva", "Batsman", 2, 10001);
		Team team = new Team(member); 
		System.out.printf("Name: %s, Type: %s%n", team.getMember().getName(), team.getMember().getType());
		System.out.printf(team.toString());	
		

    }
}

class Team {

	// Immutability: Member fields final to ensure thread safety and predictability
    private final Member member;

    public Team() {
        this.member = new Member("Default", "Unknown", 0, 0);
    }

    public Team(Member member) {
		// Null Safety: Add Objects.requireNonNull() to constructors
        this.member = Objects.requireNonNull(member, "Member cannot be null");
    }

    public Member getMember() {
        return member;
    }

    @Override
    public String toString() {
        return String.format("Team{member=%s}", member);
    }
}


class Member {

    private final String name;
    private final String type;
    private final int level;
    private final int rank;

    public Member(String name, String type, int level, int rank) {
		// Null Safety: Add Objects.requireNonNull() to constructors
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.type = Objects.requireNonNull(type, "Type cannot be null");
        this.level = level;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return String.format("Member{name='%s', type='%s', level=%d, rank=%d}", name, type, level, rank);
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