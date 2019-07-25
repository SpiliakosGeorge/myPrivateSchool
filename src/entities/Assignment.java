
package entities;

import java.util.Objects;

public class Assignment {
    private int asid;
    private String title;
    private String descr;

    public Assignment() {
    }

    public Assignment(int asid, String title, String descr) {
        this.asid = asid;
        this.title = title;
        this.descr = descr;
    }

    public int getAsid() {
        return asid;
    }

    public void setAsid(int asid) {
        this.asid = asid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.asid;
        hash = 23 * hash + Objects.hashCode(this.title);
        hash = 23 * hash + Objects.hashCode(this.descr);
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
        final Assignment other = (Assignment) obj;
        if (this.asid != other.asid) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.descr, other.descr)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  "asid: " + asid + " title: " + title + " descr: " + descr;
    }
    
    
    
    
}
