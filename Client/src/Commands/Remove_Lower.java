package Commands;

import Classes.Flat;

import java.io.Serializable;
import java.util.HashSet;

public class Remove_Lower implements Serializable {
    private transient HashSet<Flat> flats;
    private int numberOfRooms;
    private String info;

    public Remove_Lower() {
        this.info = null;
    }

    public Remove_Lower(HashSet<Flat> flats, int numberOfRooms) {
        this.flats = flats;
        this.numberOfRooms = numberOfRooms;
    }

    public Remove_Lower(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getInfo() {
        return this.info;
    }

    public void setFlats(HashSet<Flat> flats) {
        this.flats = flats;
    }

    public void execute() {
        int control = flats.size();
        try {
            flats.stream().filter(f -> f.getNumberOfRooms() < numberOfRooms).forEach(f -> flats.remove(f));
        } catch (Exception e) {
        }
        if (control == flats.size()) info = "Было удалено 0 элементов";
        else {
            if ((control - flats.size()) % 10 == 1 && (control - flats.size()) % 100 != 11)
                info = "Был удален " + (control - flats.size()) + " элемент";
            else if (((control - flats.size()) % 10 == 2 || (control - flats.size()) % 10 == 3 || (control - flats.size()) % 10 == 4) && ((control - flats.size()) % 100 != 12
                    || (control - flats.size()) % 10 != 13 || (control - flats.size()) % 10 != 14))
                info = "Было удалено " + (control - flats.size()) + " элемента";
            else info = "Было удалено " + (control - flats.size()) + " элементов";
        }
        flats = null;
    }

    public String toStrings() {
        return "remove_lower "+ numberOfRooms;
    }
}
