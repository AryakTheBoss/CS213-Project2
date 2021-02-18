package cs213lib;

import java.util.Scanner;

/**
 * Modified version of Kiosk for testing purposes that uses an array of commands instead of scanner
 * @author Aryak Pande (amp487), Mayank Singamreddy (mss390)
 */
public class KioskTestVersion {

    public void run(Command[] commands) {

        //NOTE TO GRADER: this class is identical to Kiosk.java except that instead of scanner it uses an array of Command Objects
        Library lib = new Library();
        String[] split;
        int currentSerialNumber = 10001;
        System.out.println("Library Kiosk Running.");
       for(Command command : commands){
            System.out.println(command); //print out case for ease of reading while testing
            switch (command.getCommand()) {
                case "PA":
                    lib.print();
                    command = null;
                    //System.out.println("Initial run of PA, is done on an empty list, and should print nothing");
                    //System.out.println("Second run of PA, will be done on list of 5 books");
                    break;
                case "PD":
                    lib.printByDate();
                    command = null;
                   // System.out.println("Initial run of PD, is done on an empty list, and should print nothing");
                   // System.out.println("Second run of PD, will be done on list of 5 books");
                    break;
                case "PN":
                    lib.printByNumber();
                    command = null;
                   // System.out.println("Initial run of PN, is done on an empty list, and should print nothing");
                   // System.out.println("Second run of PN, will be done on list of 5 books");
                    break;
            }
            if(command == null){
                continue;
            }
            if(command.getCommand().isEmpty()){
                System.out.println("Invalid Command!");
                continue;
            }
            switch(command.getCommand().charAt(0)){

                case 'A':

                    try {
                        if (command.getCommand().charAt(1) != ',') {
                            System.out.println("Invalid Command!");
                            // System.out.println("This should print once, to account for the case with a space between A and comma");
                            break;
                        }
                        split = command.getCommand().split(",");
                        if (split.length != 3) { //if the number of arguments isnt 3 including the A then its a bad command
                            System.out.println("Invalid Command!");
                            // System.out.println("This should print once, to account for the addition case with too many arguments");
                            break;
                        }
                        Date published = new Date(split[2]);
                        if (!published.isValid()) {
                            System.out.println("Invalid Date!");
                            // System.out.println("This should print once, to account for the case with the month of 13 as a date");
                            break;
                        }

                        Book newBook = new Book(Integer.toString(currentSerialNumber), split[1], published);
                        lib.add(newBook); //added successfully
                        System.out.println("\"" + split[1] + "\" added to the Library.");
                        // System.out.println("This should print five times, to account for the 5 normal books being added");
                        currentSerialNumber++;
                    }catch(StringIndexOutOfBoundsException e){
                        System.out.println("Invalid Command!");
                    }
                    break;

                case 'R':

                    try {
                        if (command.getCommand().charAt(1) != ',') {
                            System.out.println("Invalid Command!");
                            //System.out.println("This should print once, to account for the case with a space between R and comma");
                            break;
                        }
                        split = command.getCommand().split(",");
                        if (split.length != 2) { //if the number of arguments isnt 2 including the R then its a bad command
                            System.out.println("Invalid Command!");
                            // System.out.println("This should print once, to account for the removal case with too many arguments");
                            break;
                        }
                        Integer.parseInt(split[1]);
                        Book toRemove = new Book(split[1], "fake", null);
                        if (!lib.remove(toRemove)) { //does the removing if it exists if not it prints the error
                            System.out.println("Unable to remove, The Library does not have this book");
                            //System.out.println("This should print once, to account for when the book does not exist");
                        } else {
                            System.out.println("Book#" + split[1] + " removed.");
                            //System.out.println("This should print five times, to remove each book that was added");
                        }
                    }catch(StringIndexOutOfBoundsException | NumberFormatException e){
                        System.out.println("Invalid Command!");
                    }
                    break;

                case 'O':

                    try {
                        if (command.getCommand().charAt(1) != ',') {
                            System.out.println("Invalid Command!");
                            break;
                        }
                        split = command.getCommand().split(",");
                        if (split.length != 2) { //if the number of arguments isnt 2 including the R then its a bad command
                            System.out.println("Invalid Command!");
                            break;
                        }
                        Integer.parseInt(split[1]);
                        Book toCheckOut = new Book(split[1], "fake", null);
                        if (!lib.checkOut(toCheckOut)) { //does the checking out if it exists or is not already checked out if not it prints the error
                            System.out.println("Book#" + split[1] + " is not available.");
                            //System.out.println("This should print twice, to address case of already checked out, and non-existing book");
                        } else {
                            System.out.println("You've checked out Book#" + split[1] + ". Enjoy!");
                            // System.out.println("This should print once, to check out a book normally");
                        }
                    }catch(StringIndexOutOfBoundsException | NumberFormatException e){
                        System.out.println("Invalid Command!");
                    }
                    break;

                case 'I':

                    try {
                        if (command.getCommand().charAt(1) != ',') {
                            System.out.println("Invalid Command!");
                            break;
                        }
                        split = command.getCommand().split(",");
                        if (split.length != 2) { //if the number of arguments isnt 2 including the R then its a bad command
                            System.out.println("Invalid Command!");
                            break;
                        }
                        Integer.parseInt(split[1]);
                        Book toReturn = new Book(split[1], "fake", null);
                        if (!lib.returns(toReturn)) { //does the returning if it exists and is checked out if not it prints the error
                            System.out.println("Unable to return Book#" + split[1] + ".");
                            //System.out.println("This should print twice, to attempt to return an alraedy returned book, and a non-existing book");
                        } else {
                            System.out.println("Book#" + split[1] + " return has completed. Thanks!");
                            // System.out.println("This should print once, to check out a book normally");
                        }
                    }catch(StringIndexOutOfBoundsException | NumberFormatException e){
                        System.out.println("Invalid Command!");
                    }
                    break;

                case 'Q':

                    System.out.println("Kiosk Session Ended.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Command!");
                  //  System.out.println("This will print 8 times, once for each lowercase invalid command.");
                    break;
            }
        }

    }

}
