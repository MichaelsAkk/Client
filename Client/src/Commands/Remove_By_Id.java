package Commands;

import Classes.Flat;

import java.io.Serializable;
import java.util.HashSet;

public class Remove_By_Id implements Serializable {
    private transient HashSet<Flat> flats;
    private Integer id;
    private String info;

   public Remove_By_Id () {
       this.info = null;
   }

    public Remove_By_Id(HashSet<Flat> flats, Integer id) {
        this.flats = flats;
        this.id = id;
    }

    public Remove_By_Id(Integer id) {
        this.id = id;
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
           flats.stream().filter(f -> f.getId().toString().equalsIgnoreCase(id.toString())).limit(1).forEach(f -> flats.remove(f));
       } catch (Exception e) {
       }
       if (control == flats.size()) info = "Элемента с id = "+ id +" не существует в коллекции";
       else info = "Элемент успешно удален";
       flats = null;
       id = null;
    }

    public String toStrings() {
        return "remove_by_id " + id;
    }
}
