package cs213lib;


/**
 * Represents an employee profile
 *  @author Mayank Singamreddy mss390, Aryak Pande amp487
 */
public class Profile {

    private String name;
    private String department;
    private Date dateHired;

    /**
     * Defines an employee profile
     * @param name employee name
     * @param department the department
     * @param dateHired date they were hired
     */
    public Profile(String name, String department, Date dateHired){
        this.name=name;
        this.department=department;
        this.dateHired=dateHired;
    }

    /**
     * gets the name of employee
     * @return the name
     */
    public String getName(){
        return name;
    }

    /**
     * gets the department
     * @return the department
     */
    public String getDepartment(){
        return department;
    }

    /**
     * gets the date hired
     * @return the date hired
     */
    public Date getDateHired(){
        return dateHired;
    }

    @Override
    public String toString() {
        return name+"::"+department+"::"+dateHired;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Profile){
            Profile other = (Profile) o;
            return this.name.equals(other.getName()) && this.department.equals(other.getDepartment()) && this.dateHired.toString().equals(other.getDateHired().toString());
        }
        return false;
    }


}
