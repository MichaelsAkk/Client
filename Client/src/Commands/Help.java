package Commands;

import Instruments.Processing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public class Help implements Serializable {
    private String info;

    public Help () { info=null; }

    public String getInfo() {
        return this.info;
    }

    public void execute() {
        try {
            FileReader reader = new FileReader(Processing.getFilePath("comm"));
            // читаем посимвольно
            int c; String s = "";
            while ((c = reader.read()) != -1) {
                s = s + (char) c;
            }
            reader.close();
            info = s;
        } catch (FileNotFoundException ex) {
            info = "Ошибка выполнения: файл \"Commands.txt\" не найден";
        } catch (IOException ex) {
            info = "Ошибка чтения файла";
        }
    }

    @Override
    public String toString() {
        return "help";
    }
}
