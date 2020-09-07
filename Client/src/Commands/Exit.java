package Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

public class Exit implements Serializable {
    private BufferedReader reader;

    public Exit() {}
    public Exit (BufferedReader reader) {
       this.reader = reader;
    }

    public void setReader (BufferedReader reader) {
        this.reader = reader;
    }

    public void execute() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
