
package entities;

import java.util.Objects;


public class Stream {
    private int sid;
    private String sdescr;

    public Stream() {
    }

    public Stream(int sid, String sdescr) {
        this.sid = sid;
        this.sdescr = sdescr;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSdescr() {
        return sdescr;
    }

    public void setSdescr(String sdescr) {
        this.sdescr = sdescr;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.sid;
        hash = 47 * hash + Objects.hashCode(this.sdescr);
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
        final Stream other = (Stream) obj;
        if (this.sid != other.sid) {
            return false;
        }
        if (!Objects.equals(this.sdescr, other.sdescr)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return   sid + " " + sdescr ;
    }

    
    
    
    
    
}
