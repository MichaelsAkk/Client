package Commands;

import Classes.Flat;

import java.io.Serializable;
import java.util.HashSet;

public class Filter_By_Number_Of_Rooms implements Serializable {
    private transient HashSet<Flat> flats;
    private int numberOfRooms;
    private String info;

    public String getInfo() {
        return this.info;
    }

    public void setFlats(HashSet<Flat> flats) {
        this.flats = flats;
    }

    public Filter_By_Number_Of_Rooms (HashSet<Flat> flats, int numberOfRooms) {
        this.flats = flats;
        this.numberOfRooms = numberOfRooms;
    }

    public Filter_By_Number_Of_Rooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Filter_By_Number_Of_Rooms() {}

    public void execute() {
        info = "";
        flats.stream().filter(f -> f.getNumberOfRooms() == numberOfRooms).sorted().forEach(s -> info = info + s);
        if (info=="") info = "Элементы не найдены";
    }

    public String toStrings() {
        return "filter_by_number_of_rooms " + numberOfRooms;
    }
}
