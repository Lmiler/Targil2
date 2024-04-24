public class User {
    private String username;
    private String password;
    private String phoneNumber;
    private boolean isRegularUser;
    public User (){
        //סיבוכיות קבועה
        this.username = "";
        this.password = "";
        this.phoneNumber = "";
    }
    public boolean getIsRegularUser() {
        //סיבוכיות קבועה
        return isRegularUser;
    }

    public String getUsername() {
        //סיבוכיות קבועה
        return this.username;
    }

    public String getPassword() {
        //סיבוכיות קבועה
        return password;
    }

    public void setUsername(RealEstate realEstate) {
        //סיבוכיות לינארית
        System.out.println("Enter a username: ");
        String username;
        do {
            username = Main.scanner.nextLine();
        } while (!this.isUsernameTaken(username , realEstate));
        System.out.println("Username got chosen.\n");
        this.username = username;
    }
    public boolean isUsernameTaken(String username , RealEstate realEstate) {
        //סיבוכיות לינארית
        boolean isTaken = true;
        User[] users = realEstate.getUsers();
        for (int i = 0; i < users.length; i++) {
            if (users[i].username.equals(username)) {
                System.out.println("Username already taken, please enter another username.\n");
                isTaken = false;
            }
        }
        return isTaken;
    }
    public void setPassword() {
        //סיבוכיות אקספוננציאלית (בגלל מתודת עזר)
        String password;
        System.out.println("A strong password got to have at least 5 characters, " +
                "one number and one of the followings characters: $ , % , _ \n");
        do {
            System.out.println("Enter a strong password according to the instructions: ");
            password = Main.scanner.nextLine();
        } while (!this.isPasswordStrong(password));
        System.out.println("Password got set.\n");
        this.password = password;
    }
    public boolean isPasswordStrong(String password) {
        //סיבוכיות אקספוננציאלית
        boolean isStrong = false;
        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        if (password.length() >= 5) {
            if (password.contains("%") || password.contains("$") || password.contains("_")) {
                boolean found = false;
                for (int i = 0; i < password.length(); i++) {
                    for (int j = 0; j < numbers.length; j++) {
                        if (password.charAt(i) == numbers[j]) {
                            isStrong = true;
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
            }
        }
        return isStrong;
    }
    public void setPhoneNumber() {
        //סיבוכיות אקספוננציאלית (בגלל מתודת עזר)
        String phoneNumber;
        System.out.println("Enter your phone number: ");
        do {
            phoneNumber = Main.scanner.nextLine();
        } while (!this.isPhoneNumberValid(phoneNumber));
        System.out.println("Phone number got set.\n");
        this.phoneNumber = phoneNumber;
    }

    public boolean isPhoneNumberValid(String phoneNumber) {
        //סיבוכיות אקספוננציאלית
        boolean isValid = (phoneNumber.length() == 10 && phoneNumber.startsWith("05"));
        if (isValid) {
            char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
            for (int i = 0; i < phoneNumber.length(); i++) {
                isValid = false;
                for (int j = 0; j < numbers.length; j++) {
                    if (phoneNumber.charAt(i) == numbers[j]) {
                        isValid = true;
                        break;
                    }
                }
                if (!isValid) {
                    break;
                }
            }
        }
        if(!isValid){
            System.out.println("Phone number isn't valid.\n");
        }
        return isValid;
    }

    public void setIsRegularUser() {
        //סיבוכיות לינארית
        System.out.println("Are you a real estate agent? Enter - 1, for yes. Or - 2, for no.");
        int answer;
        do {
            System.out.println("Answer with only 1 or 2: ");
            answer = Main.scanner.nextInt();
        } while (answer != 1 && answer != 2);
        this.isRegularUser = answer == 2;
    }
    public String toString(){
        //סיבוכיות קבועה
        String output = this.username + " " + this.phoneNumber;
        output += this.isRegularUser ? " (regular user)." : " (real estate broker).";
        return output + "\n";
    }
}
