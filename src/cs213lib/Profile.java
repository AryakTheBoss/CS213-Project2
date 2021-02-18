package cs213lib;

import java.util.Objects;

public class Profile {

    private String name;
    private String department;
    private Date dateHired;

    public Profile(String name, String department, Date dateHired){
        this.name=name;
        this.department=department;
        this.dateHired=dateHired;
    }

    @Override
    public String toString() {
        return name+"::"+department+"::"+dateHired;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }


}
