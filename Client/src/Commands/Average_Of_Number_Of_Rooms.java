package Commands;

import Classes.Flat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.OptionalDouble;

public class Average_Of_Number_Of_Rooms implements Serializable {
    private transient HashSet<Flat> flats;
    private String info;

    public String getInfo() {
        return this.info;
    }

    public void setFlats(HashSet<Flat> flats) {
        this.flats = flats;
    }

    public Average_Of_Number_Of_Rooms (HashSet<Flat> flats) {
        this.flats = flats;
        this.info = null;
    }

    public Average_Of_Number_Of_Rooms() {
        this.info = null;
    }

    public void execute() {
        ArrayList<Integer> all = new ArrayList<>();
        if (flats != null) {
            for (Flat f : flats) {
                all.add(f.getNumberOfRooms());
            }
        }
        // if (numberOfRooms !=0 )numberOfRooms = Math.round(numberOfRooms / flats.size());
        OptionalDouble result = all.stream().mapToInt(e -> e).average();
        if (result.isPresent()) {
            info = "Среднее число комнат: " + Math.round(result.getAsDouble());
        } else
        info = "Среднее число комнат: "+ 0;
    }

    public String toStrings() {
        return "average_of_number_of_rooms";
    }
}
