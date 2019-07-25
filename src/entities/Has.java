
package entities;

import java.util.Objects;


public class Has {
    
    private int hid;
    private int oralMark;
    private int totalMark;
    private Student student;
    private Assignment assignment;

    public Has() {
    }

    public Has(int hid, int oralMark, int totalMark, Student student, Assignment assignment) {
        this.hid = hid;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
        this.student = student;
        this.assignment = assignment;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public int getOralMark() {
        return oralMark;
    }

    public void setOralMark(int oralMark) {
        this.oralMark = oralMark;
    }

    public int getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.hid;
        hash = 23 * hash + this.oralMark;
        hash = 23 * hash + this.totalMark;
        hash = 23 * hash + Objects.hashCode(this.student);
        hash = 23 * hash + Objects.hashCode(this.assignment);
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
        final Has other = (Has) obj;
        if (this.hid != other.hid) {
            return false;
        }
        if (this.oralMark != other.oralMark) {
            return false;
        }
        if (this.totalMark != other.totalMark) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        if (!Objects.equals(this.assignment, other.assignment)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Has{" + "hid=" + hid + ", oralMark=" + oralMark + ", totalMark=" + totalMark + ", student=" + student + ", assignment=" + assignment + '}';
    }

    
}
