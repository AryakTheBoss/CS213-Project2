package cs213lib;

import java.util.Scanner;

/**
 * Handles I/O in the console
 * @author Aryak Pande (amp487), Mayank Singamreddy (mss390)
 */
public class Kiosk {

    public void run() {
        Scanner in = new Scanner(System.in);
        Library lib = new Library();
        String command;
        String[] split; //holds an array of strings after splitting by commas
        int currentSerialNumber = 10001; //starting serial number
        System.out.println("Library Kiosk Running.");
        while(true){
            command = in.nextLine();
            switch (command) { //Check for any of the print commands
                case "PA":
                    lib.print();
                    command = null;
                    break;
                case "PD":
                    lib.printByDate();
                    command = null;
                    break;
                case "PN":
                    lib.printByNumber();
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
            switch(command.charAt(0)){ //if the command starts with A R I O Q

                case 'A':

                    try {
                        if (command.charAt(1) != ',') { //if the second character isn't a comma its invalid
                            System.out.println("Invalid Command!");
                            break;
                        }
                        split = command.split(","); //split the command by commas
                        if (split.length != 3) { //if the number of arguments isnt 3 including the A then its a bad command
                            System.out.println("Invalid Command!");
                            break;
                        }
                        Date published = new Date(split[2]); //the date should be the 3rd argument
                        if (!published.isValid()) {
                            System.out.println("Invalid Date!");
                            break;
                        }
                        Book newBook = new Book(Integer.toString(currentSerialNumber), split[1], published);
                        lib.add(newBook); //added successfully
                        System.out.println("\"" + split[1] + "\" added to the Library.");
                        currentSerialNumber++; //if book was added increase the next serial number
                    }catch(StringIndexOutOfBoundsException e){ //Command was correct but has no arguments
                        System.out.println("Invalid Command!");
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
