package cs213lib;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PayrollProcessing {

    public void run(){ //yeet

        /*
        System.out.println(cc.add(new Fulltime(new Profile("Jane,Doe","CS",new Date("1/12/2021")),85000)));
        System.out.println(cc.add(new Parttime(new Profile("Jane,Doe","ECE",new Date("1/12/2021")),49.5f,0)));
        System.out.println(cc.add(new Management(new Profile("Jane,Doe","IT",new Date("1/12/2021")),85000,Management.MANAGER)));
        System.out.println(cc.add(new Parttime(new Profile("Jane,Doe","CS",new Date("1/12/2021")),44.6f,0)));
        //cc.processPayments();
        cc.print();
        cc.processPayments();
        cc.print();
        */

        Scanner in = new Scanner(System.in);
        Company cc = new Company();
        String command;

        System.out.println("Library Kiosk Running.");
        while(true){
            command = in.nextLine();
            StringTokenizer st = new StringTokenizer(command, " ");
            String[] tokens = new String[st.countTokens()]; //holds an array of strings after splitting by commas
            for(int i =0;i<tokens.length;i++){
                tokens[i] = st.nextToken();
            }
            switch (tokens[0]) { //Check for any of the print commands
                case "PA":
                    cc.print();
                    command = null;
                    break;
                case "PH":
                    cc.printByDate();
                    command = null;
                    break;
                case "PD":
                    cc.printByDepartment();
                    command = null;
                    break;
            }
            if(command == null){ //If a print command was entered, do not continue in the loop
                continue;
            }
            if(command.isEmpty()){
                System.out.println("Invalid Command!");
                continue;
            }
            switch(command.charAt(0)){

                case 'A':
                    if(tokens.length!=5){
                        System.out.println("Invalid Command!");
                        break;
                    }
                    Date published = new Date(tokens[3]); //the date should be the 3rd argument
                    if (!published.isValid()) {
                        System.out.println("Invalid Date!");
                        break;
                    }
                    switch(command.charAt(1)){
                        case 'P':
                            Profile profile = new Profile(tokens[1],tokens[2],published);
                            Employee newEmployee = new Employee(profile);


                            cc.add(newEmployee); //added successfully

                    }


                    break;

                case 'R':

                    try {
                        if (command.charAt(1) != ',') { //if the second char isnt a comma its invalid
                            System.out.println("Invalid Command!");
                            break;
                        }
                        split = command.split(","); //split command by commas
                        if (split.length != 2) { //if the number of arguments isnt 2 including the R then its a bad command
                            System.out.println("Invalid Command!");
                            break;
                        }
                        Integer.parseInt(split[1]);
                        Book toRemove = new Book(split[1], "fake", null); //only need to provide the number
                        if (!lib.remove(toRemove)) { //does the removing if it exists if not it prints the error
                            System.out.println("Unable to remove, The Library does not have this book");
                        } else {
                            System.out.println("Book#" + split[1] + " removed.");
                        }
                    }catch (StringIndexOutOfBoundsException | NumberFormatException e){ //if the command was the only thing entered or the serial number isnt an integer
                        System.out.println("Invalid Command!");
                    }

                    break;

                case 'O':

                    try {
                        if (command.charAt(1) != ',') { //if the 2nd char isnt a comma, its invalid
                            System.out.println("Invalid Command!");
                            break;
                        }
                        split = command.split(","); //split by commas
                        if (split.length != 2) { //if the number of arguments isnt 2 including the O then its a bad command
                            System.out.println("Invalid Command!");
                            break;
                        }
                        Integer.parseInt(split[1]); //testing if its an int
                        Book toCheckOut = new Book(split[1], "fake", null); //only need to provide the number
                        if (!lib.checkOut(toCheckOut)) { //does the checking out if it exists or is not already checked out if not it prints the error
                            System.out.println("Book#" + split[1] + " is not available.");
                        } else {
                            System.out.println("You've checked out Book#" + split[1] + ". Enjoy!");
                        }
                    }catch(StringIndexOutOfBoundsException | NumberFormatException e){
                        System.out.println("Invalid Command!");
                    }
                    break;

                case 'I':

                    try {
                        if (command.charAt(1) != ',') { //if second char isnt a comma its invalid
                            System.out.println("Invalid Command!");
                            break;
                        }
                        split = command.split(","); //split by commas
                        if (split.length != 2) { //if the number of arguments isnt 2 including the R then its a bad command
                            System.out.println("Invalid Command!");
                            break;
                        }
                        Integer.parseInt(split[1]); //test if the argument is an int
                        Book toReturn = new Book(split[1], "fake", null); //only need to provide the number
                        if (!lib.returns(toReturn)) { //does the returning if it exists and is checked out if not it prints the error
                            System.out.println("Unable to return Book#" + split[1] + ".");
                        } else {
                            System.out.println("Book#" + split[1] + " return has completed. Thanks!");
                        }
                    }catch(StringIndexOutOfBoundsException | NumberFormatException e){ //if its not an int or I was the only thing entered
                        System.out.println("Invalid Command!");
                    }
                    break;

                case 'Q':

                    System.out.println("Kiosk Session Ended.");
                    System.exit(0); //Quit the session
                    break;

                default: //if its anything else, its invalid
                    System.out.println("Invalid Command!");
                    break;
            }
        }
    }

}
