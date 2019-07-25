package entities;

import java.util.Objects;


public class Trainer {
    
    private int trid;
    private String fname;
    private String lname;
    private String subject;

    public Trainer() {
    }

    public Trainer(int trid, String fname, String lname, String subject) {
        this.trid = trid;
        this.fname = fname;
        this.lname = lname;
        this.subject = subject;
    }

    public int getTrid() {
        return trid;
    }

    public void setTrid(int trid) {
        this.trid = trid;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.trid;
        hash = 41 * hash + Objects.hashCode(this.fname);
        hash = 41 * hash + Objects.hashCode(this.lname);
        hash = 41 * hash + Objects.hashCode(this.subject);
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
        final Trainer other = (Trainer) obj;
        if (this.trid != other.trid) {
            return false;
        }
        if (!Objects.equals(this.fname, other.fname)) {
            return false;
        }
        if (!Objects.equals(this.lname, other.lname)) {
            return false;
        }
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trid: " + trid + " fname: " + fname + " lname: " + lname + " subject: " + subject ;
    }
    
    
    
}
