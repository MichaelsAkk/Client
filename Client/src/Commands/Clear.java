package Commands;

import Classes.Flat;

import java.io.Serializable;
import java.util.HashSet;

public class Clear implements Serializable {
    private transient HashSet<Flat> flats;
    private String info;

    public Clear() { info=null; }

    public Clear (HashSet<Flat> flats) {
        info = null;
        this.flats = flats;
    }

    public void execute() {
        flats.clear();
        info = "Коллекция успешно очищена";
        flats = null;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "clear";
    }
}
