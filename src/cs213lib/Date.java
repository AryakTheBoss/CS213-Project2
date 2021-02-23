package cs213lib;

import java.util.Calendar;

/**
 * Data type for a Date
 * @author Aryak Pande (amp487), Mayank Singamreddy (mss390)
 */
public class Date implements Comparable<Date>{
  private int year;
  private int month;
  private int day;
  public static final int MINYEARS = 1900; //directors are 3
  public static final int THIRTYDAYMONTH = 30; //directors are 3
  public static final int THIRTYONEDAYMONTH = 31; //directors are 3
  public static final int TWENTYEIGHTDAYMONTH = 28; //directors are 3
  public static final int TWENTYNINEDAYMONTH = 29; //directors are 3
  public static final int LEAPYEARCHECKONEHUNDRED = 100; //directors are 3
  public static final int LEAPYEARCHECKFOURHUNDRED = 400; //directors are 3
  public static final int LEAPYEARCHECKFOUR = 4; //directors are 3


  /**
   * Creates a new date using a string with format mm/dd/yyyy
   * @param date a date string
   */
  public Date(String date) {
    String[] data = date.split("/"); //split the entered date by '/'
    try {
      year = Integer.parseInt(data[2]); //year is the last argument
      //month is the first argument
      month = Integer.parseInt(data[0])-1; //subtract 1 so it works with the calendar class's constants (January is 0 in Calendar)
      day = Integer.parseInt(data[1]); //day is the second argument
    }catch(NumberFormatException | IndexOutOfBoundsException nfe){ //error in parsing date
      year=-1; //default values for incorrect
      month=-1; //default values for incorrect
      day=-1; //default values for incorrect
    }
  } //taking mm/dd/yyyy and create a Date object

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
    if(given.after(today) || year < MINYEARS){
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
        return day <= THIRTYONEDAYMONTH && day > 0;
      case Calendar.APRIL:
      case Calendar.JUNE:
      case Calendar.SEPTEMBER:
      case Calendar.NOVEMBER:
        return day <= THIRTYDAYMONTH && day > 0;

      case Calendar.FEBRUARY: //special case for feb
        if(year%LEAPYEARCHECKFOUR == 0){
          if(year%LEAPYEARCHECKONEHUNDRED == 0){
            if(year%LEAPYEARCHECKFOURHUNDRED == 0){
              return day<=TWENTYNINEDAYMONTH;//leap year
            }else{
              return day<=TWENTYEIGHTDAYMONTH; //not a leap year
            }
          }else{
            return day<=TWENTYNINEDAYMONTH;//leap year
          }
        }else{
          return day<=TWENTYEIGHTDAYMONTH; //not a leap year
        }
      default:
        return false; //month is not 1-12

    }


  }

  /*convert the date to a usable string*/
  @Override
  public String toString() {
    return (month+1)+"/"+day+"/"+year; //formatting for the date
    //month+1 because of index to real life notation offset
  }

  /*
  compare the date values given, determine which comes first
  */
  @Override
  public int compareTo(Date o) { //returns -1 if date given is before, 1 if after and 0 if equal
    Calendar thisDate = Calendar.getInstance();

    thisDate.set(year,month,day); //set the thisDate temp value to these values

    Calendar other = Calendar.getInstance(); //set second temp value to other
    other.set(o.getYear(),o.getMonth(),o.getDay()); //set the temp values

    return thisDate.compareTo(other); //return the values for compareTo

  }
}
