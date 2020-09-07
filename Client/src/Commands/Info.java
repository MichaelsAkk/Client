package Commands;

import Classes.Flat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;

public class Info implements Serializable {

    private transient HashSet<Flat> flats;
    private transient LocalDateTime today;
    private String info;

    public Info() {}

    public Info (HashSet<Flat> flats, LocalDateTime today) {
        this.flats = flats;
        this.today = today;
    }

    public String getInfo() {
        return this.info;
    }

    public void execute() {
        info = "Тип коллекции: HashSet<Flat>\n";
        try {
            if (flats.size() % 10 == 1 && flats.size() % 100 != 11) {
                info = info + "Коллекция содержит " + flats.size() + " элемент\n";
            } else if ((flats.size() % 10 == 2 || flats.size() % 10 == 3 || flats.size() % 10 == 4) && (flats.size() % 100 != 12
                    || flats.size() % 10 != 13 || flats.size() % 10 != 14)) {
                info = info + "Коллекция содержит " + flats.size() + " элемента\n";
            } else info = info + "Коллекция содержит " + flats.size() + " элементов\n";
        } catch (Exception e) {
            info = info + "Коллекция содержит 0 элементов\n";
        }
        info = info +"Инициализирована "+ today.toString();
        flats = null;
        today = null;
    }

    @Override
    public String toString() {
        return "info";
    }
}
