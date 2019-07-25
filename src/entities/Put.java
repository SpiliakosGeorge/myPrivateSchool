
package entities;

import java.time.LocalDate;
import java.util.Objects;


public class Put {
    
    private int pid;
    private Assignment assignment;
    private Course course;
    private LocalDate subDateTime;

    public Put() {
    }

    public Put(int pid, Assignment assignment, Course course, LocalDate subDateTime) {
        this.pid = pid;
        this.assignment = assignment;
        this.course = course;
        this.subDateTime = subDateTime;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getSubDateTime() {
        return subDateTime;
    }

    public void setSubDateTime(LocalDate subDateTime) {
        this.subDateTime = subDateTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.pid;
        hash = 79 * hash + Objects.hashCode(this.assignment);
        hash = 79 * hash + Objects.hashCode(this.course);
        hash = 79 * hash + Objects.hashCode(this.subDateTime);
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
        final Put other = (Put) obj;
        if (this.pid != other.pid) {
            return false;
        }
        if (!Objects.equals(this.assignment, other.assignment)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        if (!Objects.equals(this.subDateTime, other.subDateTime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return assignment.getTitle() + " " + course.getTitle() + " " + subDateTime ;
    }
    
    
    
    
}
