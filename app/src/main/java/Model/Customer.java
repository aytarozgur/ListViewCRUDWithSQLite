package Model;

import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by canbay on 22.03.2017.
 */

public class Customer {
    private int id;
    private String namesurname;
    private String birthdate;
    private String credit;

    public Customer(int id,String namesurname,String birthdate,String credit){
        this.id = id;
        this.namesurname = namesurname;
        this.birthdate=birthdate;
        this.credit = credit;

    }

    public Customer(String namesurname,String birthdate,String credit){
        this.namesurname = namesurname;
        this.birthdate = birthdate;
        this.credit = credit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamesurname() {
        return namesurname;
    }

    public void setNamesurname(String namesurname) {
        this.namesurname = namesurname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }
}
