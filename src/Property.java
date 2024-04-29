public class Property {
    private City city;
    private User user;
    private String street;
    private int rooms;
    private int houseNumber;
    private int floorNumber;
    private double price;
    private int type;
    private boolean isToRent;

    public Property(City city, User user, String street, int rooms, int houseNumber, int floorNumber,
                    double price, int type, boolean isToRent) {
        //סיבוכיות קבועה
        this.city = city;
        this.user = user;
        this.street = street;
        this.rooms = rooms;
        this.houseNumber = houseNumber;
        this.floorNumber = floorNumber;
        this.price = price;
        this.type = type;
        this.isToRent = isToRent;
    }

    public User getUser() {
        //סיבוכיות קבועה
        return user;
    }

    public int getRooms() {
        //סיבוכיות קבועה
        return rooms;
    }

    public double getPrice() {
        //סיבוכיות קבועה
        return price;
    }

    public int getType() {
        //סיבוכיות קבועה
        return type;
    }

    public boolean isToRent() {
        //סיבוכיות קבועה
        return isToRent;
    }

    @Override
    public String toString() {
        //סיבוכיות קבועה
        String output = this.city.getName() + " - " + this.street + " " + this.houseNumber + ". \n";
        switch (this.type) {
            case 1 -> output += "Regular apartment - ";
            case 2 -> output += "Penthouse apartment - ";
            case 3 -> output += "Private house - ";
        }
        output += isToRent ? "for rent: " : "for sale: ";
        output += this.rooms + " rooms, floor " + this.floorNumber + ".\n"
                + "Price: " + this.price + "$. \n";
        output += "Contact info: " + this.user.toString();
        return output + "\n";
    }
}
