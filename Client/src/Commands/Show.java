package Commands;

import Classes.Flat;

import java.io.Serializable;
import java.util.HashSet;

public class Show implements Serializable {
    private transient HashSet<Flat> flats;
    private String info;

    public Show() { info = null; }

    public Show (HashSet<Flat> flats) {
        this.flats = flats;
        info = null;
    }

    public String getInfo() {
        return info;
    }

    public void execute() {
        info = "";
        if (flats.size() != 0) {
            flats.stream().filter(f -> f.getNumberOfRooms() < 100).sorted().forEach(s -> info = info + s);
            int a;
        }
        else
            info = "Коллекция пуста";
        flats = null;
    }

    @Override
    public String toString() {
        return "show";
    }

}
