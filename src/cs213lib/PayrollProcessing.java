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
                System.out.println("No Command Entered.");
                continue;
            }
            switch(tokens[0]){

                case "AP":
                    if(tokens.length != 5){
                        System.out.println("Invalid Command!");
                        break;
                    }
                    if(Integer.parseInt(tokens[4])<0){
                        System.out.println("Invalid Pay!");
                        break;
                    }
                    Date hired = new Date(tokens[3]); //the date should be the 3rd argument
                    if (!hired.isValid()) {
                        System.out.println("Invalid Date!");
                        break;
                    }


                            if(checkDepartment(tokens[2])){
                                try {
                                    Profile profile = new Profile(tokens[1], tokens[2], hired);
                                    Employee newEmployee = new Parttime(profile, Float.parseFloat(tokens[4]), 0);
                                    if(cc.add(newEmployee)) { //added successfully
                                        System.out.println("Employee Added.");
                                    }else{
                                        System.out.println("Employee is already in the Database");
                                    }
                                }catch(NumberFormatException e){
                                    System.out.println("\""+tokens[4]+"\""+"is not a number.");
                                }
                            }else{
                                System.out.println("\""+tokens[2]+"\""+" is not a valid department code.");
                                break;
                            }

                            break;
                case "AF":

                    if(tokens.length != 5){
                        System.out.println("Invalid Command!");
                        break;
                    }
                     hired = new Date(tokens[3]); //the date should be the 3rd argument
                    if (!hired.isValid()) {
                        System.out.println("Invalid Date!");
                        break;
                    }
                    if(Integer.parseInt(tokens[4])<0){
                        System.out.println("Invalid Pay!");
                        break;
                    }
                    if(checkDepartment(tokens[2])){
                        try {
                            Profile profile = new Profile(tokens[1], tokens[2], hired);
                            Employee newEmployee = new Fulltime(profile, Float.parseFloat(tokens[4]));
                            if(cc.add(newEmployee)) { //added successfully
                                System.out.println("Employee Added.");
                            }else{
                                System.out.println("Employee is already in the Database");
                            }

                        }catch(NumberFormatException e){
                            System.out.println("\""+tokens[4]+"\""+"is not a number.");
                        }
                    }else{
                        System.out.println("\""+tokens[2]+"\""+" is not a valid department code.");
                        break;
                    }

                    break;
                case "AM":
                    if(tokens.length != 6){
                        System.out.println("Invalid Command!");
                        break;
                    }
                     hired = new Date(tokens[3]); //the date should be the 3rd argument
                    if (!hired.isValid()) {
                        System.out.println("Invalid Date!");
                        break;
                    }
                    if(Integer.parseInt(tokens[4])<0){
                        System.out.println("Invalid Pay!");
                        break;
                    }
                    if(checkDepartment(tokens[2])){
                        try {
                            Profile profile = new Profile(tokens[1], tokens[2], hired);
                            int mgmtCode = Integer.parseInt(tokens[5]);
                            if(mgmtCode >= 1 && mgmtCode <= 3){
                                Employee newEmployee = new Management(profile, Float.parseFloat(tokens[4]),mgmtCode);
                                if(cc.add(newEmployee)) { //added successfully
                                    System.out.println("Employee Added.");
                                }else{
                                    System.out.println("Employee is already in the Database");
                                }
                            }else{
                                System.out.println("Invalid Management Code.");
                            }

                        }catch(NumberFormatException e){
                            System.out.println("\""+tokens[4]+"\""+"is not a number OR"+"\""+tokens[5]+"\""+" is not a number.");
                        }
                    }else{
                        System.out.println("\""+tokens[2]+"\""+" is not a valid department code.");
                        break;
                    }

                    break;

                case "R":
                    try {
                        if (command.charAt(1) != ' ') { //if the second char isnt a space its invalid
                            System.out.println("Invalid Command!");
                            break;
                        }
                        if (tokens.length != 4) { //if the number of arguments isnt 2 including the R then its a bad command
                            System.out.println("Invalid Command!");
                            break;
                        }
                        Date date = new Date(tokens[3]);
                        Profile profile = new Profile(tokens[1],tokens[2],date);
                        Employee toRemove = new Employee(profile); //only need to provide the correct profile
                        if (!cc.remove(toRemove)) { //does the removing if it exists if not it prints the error
                            System.out.println("Unable to remove, The Library does not have this book");
                        } else {
                            System.out.println("Employee removed.");
                        }
                    }catch (StringIndexOutOfBoundsException | NumberFormatException e){ //if the command was the only thing entered or the serial number isnt an integer
                        System.out.println("Invalid Command!");
                    }

                    break;

                case "C":
                    if(tokens.length!=1){
                        System.out.println("Invalid Command!");
                        break;
                    }

                        cc.processPayments();
                        System.out.println("Calculation of employee payments is done.");

                    break;

                case "S":

                    if(tokens.length != 5){
                        System.out.println("Invalid Command!");
                        break;
                    }
                    hired = new Date(tokens[3]); //the date should be the 3rd argument
                    if (!hired.isValid()) {
                        System.out.println("Invalid Date!");
                        break;
                    }
                    if(checkDepartment(tokens[2])){
                        Profile profile = new Profile(tokens[1], tokens[2], hired);
                        float hours = Float.parseFloat(tokens[4]);
                        if(hours<=0||hours>=100){
                            System.out.println("Invalid amount of hours.");
                        }
                        Employee newEmployee = new Parttime(profile, 0, hours);
                        if(cc.setHours(newEmployee)){
                            System.out.println("Working hours set.");
                        }else{
                            System.out.println("Employee doesn't exist or is not Part-Time");
                        }
                    }

                    break;

                case "Q":

                    System.out.println("Payroll Processing Ended.");
                    System.exit(0); //Quit the session
                    break;

                default: //if its anything else, its invalid
                    System.out.println("Invalid Command!");
                    break;
            }
        }
    }

    private boolean checkDepartment(String dep){
        return dep.equals("ECE") || dep.equals("CS") || dep.equals("IT");
    }

}
