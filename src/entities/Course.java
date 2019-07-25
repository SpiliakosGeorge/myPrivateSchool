package entities;

import java.time.LocalDate;
import java.util.Objects;


public class Course {
    private int cid;
    private Type type;
    private Stream stream;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

    public Course() {
    }

    public Course(int cid, Type type, Stream stream, String title, LocalDate startDate, LocalDate endDate) {
        this.cid = cid;
        this.type = type;
        this.stream = stream;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Stream getStream() {
        return stream;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.cid;
        hash = 83 * hash + Objects.hashCode(this.type);
        hash = 83 * hash + Objects.hashCode(this.stream);
        hash = 83 * hash + Objects.hashCode(this.title);
        hash = 83 * hash + Objects.hashCode(this.startDate);
        hash = 83 * hash + Objects.hashCode(this.endDate);
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
        final Course other = (Course) obj;
        if (this.cid != other.cid) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.stream, other.stream)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  "cid: " + cid + " type: " + type + " stream: " + stream + " title: " + title + " startDate: " + startDate + " endDate: " + endDate ;
    }

    
}
