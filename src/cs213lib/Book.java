package cs213lib;

/**
 * Abstract data type for a Book
 * @author Aryak Pande (amp487), Mayank Singamreddy (mss390)
 */
public class Book {

    private String number;
    private String name;
    private boolean checkedOut;
    private Date datePublished;

    /**
     * Creates a new book with the spesified properties
     * @param number the book's serial number
     * @param name the book's name
     * @param published the date it was published
     */
    public Book(String number,String name,Date published){
        this.number = number;
        this.name = name;
        datePublished = published;
        checkedOut = false;
    }

    /**
     * Changes whether this book is checked out
     * @param checkedOut the status of the book
     */
    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    /**
     * Gets the number of the book
     * @return the serial number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Checks if book is checked out
     * @return true if book is available, false otherwise.
     */
    public boolean isCheckedOut() {
        return checkedOut;
    }

    /**
     * Gets the date this book was published
     * @return the Date instance attached to this book
     */
    public Date getDatePublished() {
        return datePublished;
    }

    /**
     * Checks if two books are equal by comparing their serial numbers
     * @param obj the other book
     * @return true if they are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        Book other;
        if(obj instanceof Book){
           other = (Book)obj;
        }else{
            return false;
        }

            return number.equals(other.getNumber());

    }

    /**
     * Converts the book into a string representation
     * @return the string
     */
   @Override
    public String toString(){

        return "Book#"+number+"::"+name+"::"+(datePublished.getMonth()+1)+"/"+datePublished.getDay()+"/"+datePublished.getYear()+"::"+(checkedOut ? "is NOT available" : "is available");
    }
}
