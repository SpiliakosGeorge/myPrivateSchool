
package entities;

import java.util.Objects;

public class Type {
    
    private int tid;
    private String tdescr;

    public Type() {
    }

    public Type(int tid, String tdescr) {
        this.tid = tid;
        this.tdescr = tdescr;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTdescr() {
        return tdescr;
    }

    public void setTdescr(String tdescr) {
        this.tdescr = tdescr;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.tid;
        hash = 17 * hash + Objects.hashCode(this.tdescr);
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
        final Type other = (Type) obj;
        if (this.tid != other.tid) {
            return false;
        }
        if (!Objects.equals(this.tdescr, other.tdescr)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return   tid + " " + tdescr ;
    }

    
    
    
}
