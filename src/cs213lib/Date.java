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

  /**
   * Creates a new date using a string with format mm/dd/yyyy
   * @param date a date string
   */
  public Date(String date) {
    String[] data = date.split("/"); //split the entered date by '/'
    try {
      year = Integer.parseInt(data[2]); //year is first argument
      month = Integer.parseInt(data[0])-1; //subtract 1 so it works with the calendar class's constants (January is 0 in Calendar)
      day = Integer.parseInt(data[1]); //day is last argument
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

  /*convert the date to a usable string*/
  @Override
  public String toString() {
    return (month+1)+"/"+day+"/"+year; //formatting for the date
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
