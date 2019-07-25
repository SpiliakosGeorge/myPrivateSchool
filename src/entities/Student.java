
package entities;

import java.time.LocalDate;
import java.util.Objects;


public class Student {
 
    private int stid;
    private String fname;
    private String lname;
    private LocalDate dob;
    private float tuitionFees;

    public Student() {
    }

    public Student(int stid, String fname, String lname, LocalDate dob, float tuitionFees) {
        this.stid = stid;
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.tuitionFees = tuitionFees;
    }

    public int getStid() {
        return stid;
    }

    public void setStid(int stid) {
        this.stid = stid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public float getTuitionFees() {
        return tuitionFees;
    }

    public void setTuitionFees(float tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.stid;
        hash = 67 * hash + Objects.hashCode(this.fname);
        hash = 67 * hash + Objects.hashCode(this.lname);
        hash = 67 * hash + Objects.hashCode(this.dob);
        hash = 67 * hash + Float.floatToIntBits(this.tuitionFees);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.stid != other.stid) {
            return false;
        }
        if (Float.floatToIntBits(this.tuitionFees) != Float.floatToIntBits(other.tuitionFees)) {
            return false;
        }
        if (!Objects.equals(this.fname, other.fname)) {
            return false;
        }
        if (!Objects.equals(this.lname, other.lname)) {
            return false;
        }
        if (!Objects.equals(this.dob, other.dob)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stid: " + stid + " fname: " + fname + " lname: " + lname + " dob: " + dob + " tuitionFees: " + tuitionFees;
    }
    
    
    
    
}
