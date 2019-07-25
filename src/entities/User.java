
package entities;

import java.util.Objects;


public class User {
    
    private int uid;
    private String uname;
    private String upass;

    public User() {
    }

    public User(int uid, String uname, String upass) {
        this.uid = uid;
        this.uname = uname;
        this.upass = upass;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.uid;
        hash = 53 * hash + Objects.hashCode(this.uname);
        hash = 53 * hash + Objects.hashCode(this.upass);
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
        final User other = (User) obj;
        if (this.uid != other.uid) {
            return false;
        }
        if (!Objects.equals(this.uname, other.uname)) {
            return false;
        }
        if (!Objects.equals(this.upass, other.upass)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return   uname ;
    }
    
    
}
