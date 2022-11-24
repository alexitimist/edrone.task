package string.randomizer.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MyString {
    @Id
    private Integer ID;
    private String text;

    public MyString(int ID, String text) {
        this.ID = ID;
        this.text = text;
    }

    public MyString() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
