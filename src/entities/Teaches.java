
package entities;

import java.util.Objects;


public class Teaches {
 
    private int teid;
    private Trainer trainer;
    private Course course;

    public Teaches() {
    }

    public Teaches(int teid, Trainer trainer, Course course) {
        this.teid = teid;
        this.trainer = trainer;
        this.course = course;
    }

    public int getTeid() {
        return teid;
    }

    public void setTeid(int teid) {
        this.teid = teid;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.teid;
        hash = 41 * hash + Objects.hashCode(this.trainer);
        hash = 41 * hash + Objects.hashCode(this.course);
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
        final Teaches other = (Teaches) obj;
        if (this.teid != other.teid) {
            return false;
        }
        if (!Objects.equals(this.trainer, other.trainer)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return   trainer.getFname() + " "+trainer.getLname()+ " teaches " + course.getTitle() ;
    }
    
    
    
}
