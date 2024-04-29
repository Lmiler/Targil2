import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //סיבוכיות אקספוננציאלית
        RealEstate realEstate = new RealEstate();
        int choice;
        User user = new User();
        do {
            System.out.println("choose 1 to create an account: \n"
                    + "choose 2 to log in: \n" +
                    "choose 3 to end the program: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> realEstate.creatUser();
                case 2 -> {
                    user = realEstate.login();
                    if (user == null) {
                        System.out.println("No user found.\n");
                        break;
                    }
                    int option;
                    do {
                        System.out.println("Choose the action you would like to do: \n"
                                + "choose 1 to post a new property: \n"
                                + "choose 2 to remove a property: \n" +
                                "choose 3 to present all the properties in the system: \n" +
                                "choose 4 to present all the properties published by your user: \n" +
                                "choose 5 to search a property according to filters: \n" +
                                "choose 6 to log out: ");
                        option = scanner.nextInt();
                        scanner.nextLine();
                        boolean result;
                        switch (option) {
                            case 1 -> {
                                result = realEstate.postNewProperty(user);
                                if (result) {
                                    System.out.println("Property saved successfully.\n");
                                } else {
                                    System.out.println("Property was not saved.\n");
                                }
                            }
                            case 2 -> realEstate.removeProperty(user);
                            case 3 -> realEstate.printAllProperties();
                            case 4 -> realEstate.printProperties(user);
                            case 5 -> {
                                Property[] properties = realEstate.search();
                                System.out.println("There are " + properties.length
                                        + " properties that matches your search. \n");
                                for (int i = 0; i < properties.length; i++) {
                                    System.out.println(properties[i]);
                                }
                            }
                        }
                    } while (option != 6);
                }
            }
        } while (choice != 3);
        System.out.println("Finishing program.");
    }
}