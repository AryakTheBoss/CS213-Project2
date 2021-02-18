package cs213lib;

import java.util.Calendar;

/**
 * Library class that keeps track of the current catalog.
 * @author Aryak Pande (amp487), Mayank Singamreddy (mss390)
 */
public class Library {

    private Book[] books; //array books of books
    private int numBooks; //count of current books

    /**
     * Initializes the bag structure with a size of 4
     */
    public Library(){
        books = new Book[4]; //initialize with 4 books
        numBooks = 0; //but still empty
    }

    /**
     * This method finds the spesified book in the bag structure of Books.
     * @param book the book to look for
     * @return -1 if not found, the index if found.
     */
    private int find(Book book){
        for(int i=0;i<books.length;i++){ //run through list
            if(books[i] != null) { //if the space is empty, add the book there
                if (books[i].equals(book)) //if match
                    return i; //return index
            }
        }
        return -1; //to check if not found
    }

    /**
     * Grows the bag by 4 spaces.
     */
    private void grow(){ //returns larger size array
        Book[] newBookArray = new Book[books.length+4]; //increase array length by 4, to copy over
        for(int i=0;i<books.length;i++){ //run through book array
            newBookArray[i] = books[i]; //copy over every element
        }
        books = newBookArray; //set books to new bigger array

    }

    /**
     * Adds a book to the Library
     * @param book the book to add
     */
    public void add(Book book){

          for(int i=0;i<books.length;i++){ //run through books array
              if(books[i]==null) { //found empty spot
                  books[i] = book; //set new book to empty spot
                  numBooks++; //increase books count
                  return;
              }
          }
          //if it gets to here it means its full
          grow();
        for(int i=0;i<books.length;i++){
            if(books[i]==null) { //found empty spot
                books[i] = book; //after growth, insert in new spot
                numBooks++; //increase book count
                return;
            }
        }


    }

    /**
     * Removes a book from the Library.
     * @param book the book to remove
     * @return true if it was successful false otherwise.
     */
    public boolean remove(Book book){
      int found = find(book); //find index, store
       //check if now in an index past the book
      if(found == -1){ //if book doesnt exist
        return false; //didn't happen
      }
      else{
        books[found] = null; //remove the book set it to null
        numBooks-=1; //reduce book count
          if(found != books.length-1) { //if the book does not equal to the very end
                for (int i = found; i < books.length; i++) { //this loop shifts all the books to the right of the removed book down by 1
                    if(i+1 != books.length) //if there is more space in the array
                    books[i] = books[i+1]; //put the new book there
                    else
                        books[i] = null; //this is the last space in the array, place it there
                }
            }
        return true;
      }
    }


    /**
     * Checks out a book from the library.
     * @param book the book to check out
     * @return true if book was successfully checked out, false otherwise.
     */
    public boolean checkOut(Book book){
      int found = find(book); //index
      if(found == -1){
        return false; //if it doesn't exist, false
      }
      else{
          if(!books[found].isCheckedOut()) { //if the book is already checked out
              books[found].setCheckedOut(true); //if found, set checkedOut value to true
              return true;
          }
          else
              return false; //Book is already checked out

      }
    }

    /**
     * Returns a book to the Library and shifts all books in the bag down by 1 if the removed book was not on the end.
     * @param book the book to return
     * @return true if removing was successful, false otherwise.
     */
    public boolean returns(Book book){
      int found = find(book); //get index
      if(found == -1){ //if the book is not found
        return false; //if it doesn't exist, nothing to do
      }
      else{
          if(books[found].isCheckedOut()) { //if the book is checked out
              books[found].setCheckedOut(false); //if found, it is now returned
              return true;
          }else
              return false; //book was not checked out
        //done
      }
    }

    /**
     * checks if the bag is empty
     * @return true if the bag is empty, false otherwise.
     */
    private boolean isEmpty(){
        for (Book book : books) { //run through books array
            if (book != null) //if there is no book in the array
                return false; //return false
        }
        return true; //else has books
    }

    /**
     * Prints the library catalog.
     */
    public void print(){ //print method
        if(isEmpty()){ //if nothing in array, no need to run
            System.out.println("Library catalog is Empty!");
            return;
        }
        System.out.println("**List of Books in the Library"); //before running, print this
        for (Book book : books) { //run through array
            if(book != null) //if the book exists
            System.out.println(book); //print in order of placement in array
        }// this is calling the toString() method of book
        System.out.println("**End of List.");
    }

    public void printByDate(){ //print by date sorted method
        if(isEmpty()){ //if nothing is in the array
            System.out.println("Library catalog is Empty!");
            return;
        }


        System.out.println("**List of Books by the Dates published");
        for (int outside = 0; outside < numBooks-1; outside++) { //iterate through every book
            for (int inside = 0; inside < numBooks - outside - 1; inside++) { //iterating on every opposite book


                    Calendar insideC = Calendar.getInstance(); //create calendar instance for calendar class to operate off of
                    insideC.set(books[inside].getDatePublished().getYear(), books[inside].getDatePublished().getMonth(), books[inside].getDatePublished().getDay());
                    //uses day of current book as information for year month and day
                    Calendar insideNext = Calendar.getInstance(); //create calendar instance for calendar class to operate off of
                    insideNext.set(books[inside + 1].getDatePublished().getYear(), books[inside + 1].getDatePublished().getMonth(), books[inside + 1].getDatePublished().getDay());
                    //uses day of current book as information for year month and day
                    if (insideC.before(insideNext)) //if day is lower;
                    {
                        // swap elements if the date is lower (older)
                        Book temp = books[inside]; //set temp
                        books[inside] = books[inside + 1]; //set current book to next book
                        books[inside + 1] = temp; //set replaced book as temp value, to replace

                    }

            }
        }
        for (Book book : books) { //run through books array
            if (book != null) //if the book exists
                System.out.println(book); //print out books in array, now sorted
        }
        System.out.println("**End of List.");
    }

    public void printByNumber(){ //print and sort by number per book in array
        if(isEmpty()){ //if the array has no books in it
            System.out.println("Library catalog is Empty!");
            return;
        }
        System.out.println("**List of Books by Book Number");

        for (int outside = 0; outside < numBooks-1; outside++) //for each book
            for (int inside = 0; inside < numBooks-outside-1; inside++) { //for each book
                if(books[inside] != null && books[inside+1] != null) { //if neither book is null
                    if (Integer.parseInt(books[inside].getNumber()) > Integer.parseInt(books[inside + 1].getNumber())) {
                        //if the book number is greater than the next
                        // swap arr[j+1] and arr[j]
                        Book temp = books[inside]; //save the next book as a temp value
                        books[inside] = books[inside + 1]; //set the current book to be the next
                        books[inside + 1] = temp; //set replaced book to value of temp, to swap them
                    }
                }
            }
        for (Book book : books) { //for each book
            if (book != null) //if it exists
                System.out.println(book); //print its name
        }
        System.out.println("**End of List.");
    }

}
