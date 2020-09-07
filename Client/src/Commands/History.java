package Commands;

import java.io.Serializable;
import java.util.ArrayList;

public class History implements Serializable {
    private ArrayList<String> history;
    private String info;

    public History (ArrayList<String> history) {
        this.history = history;
    }

    public History() {}

    public String getInfo() {
        return this.info;
    }

    public void execute() {
        if (history == null) info = "[]";
        else info = history.toString();
    }

    public String toStrings() {
        return "history";
    }
}
