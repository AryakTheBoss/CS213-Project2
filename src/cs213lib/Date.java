package cs213lib;

import java.util.Calendar;

/**
 * Data type for a Date
 * @author Aryak Pande (amp487), Mayank Singamreddy (mss390)
 */
public class Date {
  private int year;
  private int month;
  private int day;

  /**
   * Creates a new date using a string with format mm/dd/yyyy
   * @param date
   */
  public Date(String date) {
    String[] data = date.split("/");
    try {
      year = Integer.parseInt(data[2]);
      month = Integer.parseInt(data[0])-1; //subtract 1 so it works with the calendar class's constants (January is 0 in Calendar)
      day = Integer.parseInt(data[1]);
    }catch(NumberFormatException | IndexOutOfBoundsException nfe){ //error in parsing date
      year=-1;
      month=-1;
      day=-1;
    }

  } //taking mm/dd/yyyy and create a Date object

  /**
   * Creates a Date with today's date
   */
  public Date() {
   Calendar c = Calendar.getInstance();

   year = c.get(Calendar.YEAR);
   month = c.get(Calendar.MONTH);
   day = c.get(Calendar.DAY_OF_MONTH);
  } //create an object with today’s date (see Calendar class)


  /**
   * Gets the year
   * @return the year
   */
  public int getYear() {
    return year;
  }

  /**
   * Gets the month
   * @return the month
   */
  public int getMonth() {
    return month;
  }

  /**
   * Gets the day
   * @return the day
   */
  public int getDay() {
    return day;
  }

  /**
   * checks if the date is a valid date
   * @return true if valid false otherwise.
   */
  public boolean isValid() {
    if(day==-1 || year==-1 || month==-1){
      return false;
    }
    Calendar today = Calendar.getInstance();
    Calendar given = Calendar.getInstance();
    given.set(year,month,day);
    if(given.after(today) || year < 1900){
      return false; //date provided is after todays date or is before year 1900
    }

    switch(month){
      case Calendar.JANUARY:
      case Calendar.MARCH:
      case Calendar.MAY:
      case Calendar.JULY:
      case Calendar.AUGUST:
      case Calendar.OCTOBER:
      case Calendar.DECEMBER:
        return day <= 31 && day > 0;
      case Calendar.APRIL:
      case Calendar.JUNE:
      case Calendar.SEPTEMBER:
      case Calendar.NOVEMBER:
        return day <= 30 && day > 0;

      case Calendar.FEBRUARY: //special case for feb
        if(year%4 == 0){
          if(year%100 == 0){
            if(year%400 == 0){
              return day<=29;//leap year
            }else{
              return day<=28; //not a leap year
            }
          }else{
            return day<=29;//leap year
          }
        }else{
          return day<=28; //not a leap year
        }
      default:
        return false; //month is not 1-12

    }


  }

  //this makes 0 sense for it to be here but here it is i guess
  public static void main(String[] args){
    Command[] testCommands1 = {
            new Command("a","Invalid Command"), //fake lowercase command for add
            new Command("r","Invalid Command"), //fake lowercase command for remove
            new Command("o","Invalid Command"), //fake lowercase command for check out
            new Command("i","Invalid Command"), //fake lowercase command for return
            new Command("pa","Invalid Command"), //fake lowercase command for print by added order
            new Command("pd","Invalid Command"), //fake lowercase command for print by date
            new Command("pn","Invalid Command"), //fake lowercase command for print by number
            new Command("q","Invalid Command"), //fake lowercase command for quit
            new Command("I","Invalid Command"), //trying to return a book without any arguments
            new Command("A","Invalid Command"), //trying to add a book without any arguments
            new Command("G","Invalid Command"), //G is not a command that exists
            new Command("O,TTTT","Invalid Command"), //Trying to checkout a book with String not int for serial number
            new Command("R,R","Invalid Command"), //Trying to return a book with string as an argument
            new Command("A,B,C","Invalid Date"), //With correct formatting, only having C as a date, to check is valid date
            new Command("PP","Invalid Command"), //PP is not a command that exists
            new Command("PA","Empty Catalog"),//Print an empty list case
            new Command("PD","Empty Catalog"),//Print an empty list case
            new Command("PN","Empty Catalog"),//print an empty list case
            new Command("R,10001","Unable to remove"), // remove a book from empty bag
            new Command("A ,Java Programming,10/12/2018","Invalid Command"), //case to add a book with a space between A and comma
            new Command("A,Java Programming,10/13/2018","Added Successfully"), //case to add a book with an invalid date
            new Command("A,Java Programming,10/12/2018,tester","Invalid Command"), //add a single book, but with too many arguments
            new Command("A,Histography,10/12/2016","Added Successfully"), //add a single book
            new Command("A,Astrology,14/10/2020","Invalid Date"),//add a single book
            new Command("A,Computer Concepts,2/14/2023","Invalid Date"),//add a single book
            new Command("A,DNA Concepts,9/31/2020","Invalid Date"), //September has no 31st day
            new Command("A,Chemistry Concepts,2/29/2019","Invalid Date"), //2019 is not a leap year, and has no 29th day. invalid date
            new Command("A,The Declaration of Independance,8/02/1776","Invalid Date"), //no books before 1900 should be processed
            new Command("A,Artificial stuff,1/07/2020","Added Successfully"), //2020 is a leap year, and is a valid date
            new Command("A,Memes,3/8/2019","Added Successfully"), //march 8th, is a valid date
            new Command("A,All About Leap Years,2/29/2020","Added Successfully"), //2020 is a leap year, and has a 29th of february
            new Command("A,eXtreme Programming,2/10/2010","Added Successfully"),//add a single book
            new Command("A,Object Oriented Analysis and Design,4/30/2019","Added Successfully"), //force array to grow case
            new Command("O,10001","Checked out Successfully"), //check out book normally case, should pass
            new Command("O,10001","Unable to checkout"), //check out book that is already checked out, should fail
            new Command("O,44444","Unable to checkout"), //check out book that doesn't exist
            new Command("I,10001","Returned Successfully"), //return a book that is checked out, should pass
            new Command("I,10001","Unable to return"), //return a book that is already returned, should fail
            new Command("I,44444","Unable to return"), //return a book that does not exist, should fail
            new Command("PA","Print List"), //print list from not empty bag
            new Command("PN","Print list by number"), //print list from not empty bag
            new Command("PD","Print list sorted by date"), //print list from not empty bag
            new Command("PA","Print should be same order as previous"), //should print same as above
            new Command("PN","should be Re-sorted by number"), //should print in number order 1 2 3 4
            new Command("R ,10001","Invalid Command"), //remove a book with faulty space between R and comma
            new Command("R,10001,test","Invalid Command"), //remove a book with too many arguments for removal
            new Command("R,10001","Removed Successfully"), //remove a book normally case
            new Command("R,10002","Removed Successfully"), //remove a book normally case
            new Command("R,10003","Removed Successfully"), //remove a book normally case
            new Command("R,10004","Removed Successfully"), //remove a book normally case
            new Command("R,44444","Unable to remove"), //remove a book that does not exist
            new Command("R,10005","Removed successfully"), //remove a book normally case
            new Command("r,10006","Invalid Command"), //lowercase r, even with correct formatting, should fail
            new Command("R,10006","Removed Successful"), //correct removal of 10006
            new Command("","Invalid Command"), //blank line test
            new Command("Q","Should quit"), //normal quit command, should quit kiosk
            new Command("A,Should not be processed,10/11/2020","Line should not be seen") //false quit command, should have already ended program and will not print anything
    };

    new KioskTestVersion().run(testCommands1); //I just ran it, it works
  }
}
