import java.util.Scanner;

public class RealEstate {
    private City[] cities;
    private User[] users;
    private Property[] properties;

    public User[] getUsers() {
        //סיבוכיות קבועה
        return users;
    }

    public RealEstate() {
        //סיבוכיות קבועה
        this.cities = new City[10];
        String[] streets = {"Ben Gurion", "The olive", "The grape"};
        this.cities[0] = new City("Ashkelon", "South", streets);
        this.cities[1] = new City("Dimona", "Negev", streets);
        this.cities[2] = new City("Haifa", "North", streets);
        this.cities[3] = new City("Rishon Lezion", "Center", streets);
        this.cities[4] = new City("Hedera", "Hasharon", streets);
        this.cities[5] = new City("Tel Aviv", "Center", streets);
        this.cities[6] = new City("Rehovot", "Center", streets);
        this.cities[7] = new City("Ashdod", "South", streets);
        this.cities[8] = new City("Natanya", "Hasharon", streets);
        this.cities[9] = new City("Jerusalem", "Center", streets);
        this.users = new User[0];
        this.properties = new Property[0];
    }

    public void creatUser() {
        //סיבוכיות אקספוננציאלית (בגלל מתודות עזר)
        User user = new User();
        user.setUsername(this);
        user.setPassword();
        user.setPhoneNumber();
        user.setIsRegularUser();
        System.out.println("User added successfully!\n");
        this.addUser(user);
    }
    public void addUser(User user) {
        //סיבוכיות לינארית
        User[] temp = new User[this.users.length + 1];
        for (int i = 0; i < this.users.length; i++) {
            temp[i] = this.users[i];
        }
        temp[temp.length - 1] = user;
        this.users = temp;
    }

    public void addProperty(Property property) {
        //סיבוכיות לינארית
        Property[] temp = new Property[this.properties.length + 1];
        for (int i = 0; i < this.properties.length; i++) {
            temp[i] = this.properties[i];
        }
        temp[temp.length - 1] = property;
        this.properties = temp;
    }

    //הערה: במתודה הזאת לא השתמשנו ב ignore case משום ששמות משתמש וסיסמאות הם case sensitive
    public User login() {
        //סיבוכיות לינארית
        User user = null;
        String username;
        String password;
        System.out.println("Please enter your username: ");
        username = Main.scanner.nextLine();
        System.out.println("Please enter your password: ");
        password = Main.scanner.nextLine();
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].getUsername().equals(username) &&
                    this.users[i].getPassword().equals(password)) {
                user = this.users[i];
                break;
            }
        }
        return user;
    }

    public boolean postNewProperty(User user) {
        //סיבוכיות לינארית
        boolean isAllowed = false;
        if (!maxPropertiesReached(user)) {
            for (int i = 0; i < this.cities.length; i++) {
                System.out.println(this.cities[i].getName());
            }
            System.out.println();
            System.out.println("Enter a city from the list above: ");
            String cityName = Main.scanner.nextLine();
            City city = doesCityExists(cityName);
            if (city != null) {
                for (int j = 0; j < city.getStreets().length; j++) {
                    System.out.println(city.getStreets()[j]);
                }
                System.out.println();
                System.out.println("Enter a street from the list above: ");
                String street = new Scanner(System.in).nextLine();

                if (!doesStreetExists(street, city).isEmpty()) {
                    System.out.println("What is the type of your property? " +
                            "Enter 1 for regular apartment, 2 for penthouse, 3 for a private house: ");
                    int type = Main.scanner.nextInt();
                    if (type == 1 || type == 2 || type == 3) {
                        isAllowed = true;
                        Property property = questionsToProperty(city, street, user, type);
                        addProperty(property);
                    }
                    else{
                        System.out.println("Not a valid type.");
                    }
                }
            }
        }
        return isAllowed;
    }


    public boolean maxPropertiesReached(User user) {
        //סיבוכיות לינארית (בגלל מתודת עזר)
        boolean isReached = false;
        boolean isRegularUser = user.getIsRegularUser();
        int count = countUserProperties(user);
        if (isRegularUser && count == 2) {
            isReached = true;
        }
        if (!isRegularUser && count == 5) {
            isReached = true;
        }
        if(isReached){
            System.out.println("This user can not post any more properties - limit reached.");
        }
        return isReached;
    }

    public int countUserProperties(User user) {
        //סיבוכיות לינארית
        int count = 0;
        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i].getUser().equals(user)) {
                count++;
            }
        }
        return count;
    }

    public City doesCityExists(String cityName) {
        //סיבוכיות לינארית
        City city = null;
        for (int i = 0; i < this.cities.length; i++) {
            if (this.cities[i].getName().equalsIgnoreCase(cityName)) {
                city = this.cities[i];
                break;
            }
        }
        if (city == null) {
            System.out.println("No city found.");
        }
        return city;
    }

    public String doesStreetExists(String streetName, City city) {
        //סיבוכיות לינארית
        String name = "";
        for (int j = 0; j < city.getStreets().length; j++) {
            if (city.getStreets()[j].equalsIgnoreCase(streetName)) {
                name = city.getStreets()[j];
                break;
            }
        }
        if (name.isEmpty()) {
            System.out.println("Street not found.");
        }
        return name;
    }

    public Property questionsToProperty(City city, String street, User user, int type) {
        //סיבוכיות קבועה
        int floor = 0;
        if (type == 1 || type == 2) {
            System.out.println("What floor is the property?");
            floor = Main.scanner.nextInt();
        }
        System.out.println("How many rooms?");
        int rooms = Main.scanner.nextInt();
        System.out.println("What is the house number?");
        int houseNumber = Main.scanner.nextInt();
        System.out.println("Enter 1 if the property is for rent. Enter 2 if it is for sale: ");
        int rent = Main.scanner.nextInt();
        boolean isToRent = rent == 1;
        System.out.println("What is the price?");
        int price = Main.scanner.nextInt();
        Property property = new Property(city, user, street, rooms, houseNumber, floor, price,
                type, isToRent);
        return property;
    }

    public void removeProperty(User user) {
        //סיבוכיות לינארית (בגלל מתודת עזר)
        if (this.countUserProperties(user) == 0) {
            System.out.println("This user has no properties.");
        } else {
            this.printProperties(user);
            System.out.println("Choose the property you would like to remove according to the list number of it:");
            int index = Main.scanner.nextInt();
            this.removePropertyByIndex(index);
            System.out.println("Property removed successfully.");
        }
    }

    public void removePropertyByIndex(int index) {
        //סיבוכיות לינארית
        Property[] temp = new Property[this.properties.length - 1];
        int j = 0;
        for (int i = 0; i < this.properties.length; i++) {
            if (i != index - 1) {
                temp[j] = this.properties[i];
                j++;
            }
        }
        this.properties = temp;
    }

    public void printAllProperties() {
        //סיבוכיות לינארית
        for (int i = 0; i < this.properties.length; i++) {
            System.out.println(i + 1 + ". " + this.properties[i]);
        }
    }

    public void printProperties(User user) {
        //סיבוכיות לינארית
        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i].getUser().equals(user)) {
                System.out.println(i + 1 + ". " + this.properties[i]);
            }
        }
    }

    public Property[] search() {
        //סיבוכיות לינארית (בגלל מתודות עזר)
        System.out.println("Please note, if you are not interested in a specific filter, enter the value -999.");
        System.out.println("Enter 1 for properties that are for rent, or 2 for properties that are for sale:");
        int rent = Main.scanner.nextInt();
        System.out.println("What type of property? 1 - regular apartment, 2 - penthouse apartment, " +
                "3 - private house.");
        int type = Main.scanner.nextInt();
        System.out.println("How many rooms?");
        int rooms = Main.scanner.nextInt();
        System.out.println("Whats the price range?");
        System.out.println("Min:");
        double min = Main.scanner.nextDouble();
        System.out.println("Max:");
        double max = Main.scanner.nextDouble();
        Property[] properties = this.properties;
        if (rent != -999) {
            boolean isForRent = rent == 1;
            properties = this.searchRent(properties, isForRent);
        }
        if (type != -999) {
            properties = this.searchType(properties, type);
        }
        if (rooms != -999) {
            properties = this.searchRooms(properties, rooms);
        }
        if (max != -999 && min != -999) {
            properties = this.searchPrice(properties, min, max);
        }
        return properties;
    }

    public Property[] searchRent(Property[] properties, boolean isForRent) {
        //סיבוכיות לינארית
        int count = 0;
        for (int i = 0; i < properties.length; i++) {
            if (properties[i].isToRent() == isForRent) {
                count++;
            }
        }
        Property[] properties1 = new Property[count];
        int j = 0;
        for (int i = 0; i < properties.length; i++) {
            if (properties[i].isToRent() == isForRent) {
                properties1[j] = properties[i];
                j++;
            }
        }
        return properties1;
    }

    public Property[] searchType(Property[] properties, int type) {
        //סיבוכיות לינארית
        int count = 0;
        for (int i = 0; i < properties.length; i++) {
            if (properties[i].getType() == type) {
                count++;
            }
        }
        Property[] properties1 = new Property[count];
        int j = 0;
        for (int i = 0; i < properties.length; i++) {
            if (properties[i].getType() == type) {
                properties1[j] = properties[i];
                j++;
            }
        }
        return properties1;
    }

    public Property[] searchRooms(Property[] properties, int rooms) {
        //סיבוכיות לינארית
        int count = 0;
        for (int i = 0; i < properties.length; i++) {
            if (properties[i].getRooms() == rooms) {
                count++;
            }
        }
        Property[] properties1 = new Property[count];
        int j = 0;
        for (int i = 0; i < properties.length; i++) {
            if (properties[i].getRooms() == rooms) {
                properties1[j] = properties[i];
                j++;
            }
        }
        return properties1;
    }

    public Property[] searchPrice(Property[] properties, double min, double max) {
        //סיבוכיות לינארית
        int count = 0;
        for (int i = 0; i < properties.length; i++) {
            if (properties[i].getPrice() >= min && properties[i].getPrice() <= max) {
                count++;
            }
        }
        Property[] properties1 = new Property[count];
        int j = 0;
        for (int i = 0; i < properties.length; i++) {
            if (properties[i].getPrice() >= min && properties[i].getPrice() <= max) {
                properties1[j] = properties[i];
                j++;
            }
        }
        return properties1;
    }
}
