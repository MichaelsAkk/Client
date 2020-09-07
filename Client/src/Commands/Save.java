package Commands;

import Classes.Flat;
import com.google.gson.Gson;

import java.io.*;
import java.util.HashSet;

public class Save implements Serializable {
    private HashSet<Flat> flats;
    private File file;

    public Save(HashSet<Flat> flats, File file){
        this.flats = flats;
        this.file = file;
    }

    public void execute() {
        try {
            Gson gson = new Gson();
            FileWriter fStream1 = null;
            fStream1 = new FileWriter(file);
            BufferedWriter out1 = new BufferedWriter(fStream1);
            out1.write(""); out1.write(gson.toJson(flats));
            out1.close();
            System.out.println("Коллекция успешно сохранена");
            }
        catch (IOException e) {
            System.out.println("Ошибка сохранения");
        }

    }
}
