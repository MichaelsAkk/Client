package Commands;

import Classes.Flat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Print_Ascending implements Serializable {
    private transient HashSet<Flat> flats;
    private String info;

    public Print_Ascending(HashSet<Flat> flats) {
        this.flats = flats;
    }

    public Print_Ascending() {}

    public String getInfo() {
        return this.info;
    }

    public void setFlats(HashSet<Flat> flats) {
        this.flats = flats;
    }

    public void execute() {
        info = "";
        flats.stream().filter(f -> f.getNumberOfRooms() < 100).sorted().forEach(s -> info = info + s);
    }

    public String toStrings() {
        return "print_ascending";
    }
}
