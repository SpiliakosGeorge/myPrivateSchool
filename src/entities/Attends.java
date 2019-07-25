
package entities;

import java.util.Objects;

public class Attends {
    
    private int aid;
    private Course course;
    private Student student;

    public Attends() {
    }

    public Attends(int aid, Course course, Student student) {
        this.aid = aid;
        this.course = course;
        this.student = student;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.aid;
        hash = 71 * hash + Objects.hashCode(this.course);
        hash = 71 * hash + Objects.hashCode(this.student);
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
        final Attends other = (Attends) obj;
        if (this.aid != other.aid) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  "course: " + course.getTitle() + " student: " + student.getFname() + " "+student.getLname();
    }
    
    
    
}
