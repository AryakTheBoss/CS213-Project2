package cs213lib;



public class Profile {

    private String name;
    private String department;
    private Date dateHired;

    public Profile(String name, String department, Date dateHired){
        this.name=name;
        this.department=department;
        this.dateHired=dateHired;
    }

    public String getName(){
        return name;
    }

    public String getDepartment(){
        return department;
    }

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
