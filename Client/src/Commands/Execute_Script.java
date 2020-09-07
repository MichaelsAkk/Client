package Commands;

import Classes.Flat;
import Instruments.ScriptInfo;
import Instruments.UserCommandsScript;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

public class Execute_Script implements Serializable {
    private String sFile;
    private transient HashSet<Flat> flats;
    private ArrayList<String> history;
    private transient LocalDateTime today;
    private transient BufferedReader reader;
    private transient ArrayList<String> scripts;
    private ArrayList<String> scriptCommands;
    private String info;

    public String getInfo() {
        if (info.equalsIgnoreCase("")) return "Скрипт не содержит команд";
        return this.info;
    }

    public ArrayList<String> getScriptCommands() {
        return this.scriptCommands;
    }

    public void setFields(HashSet<Flat> flats, LocalDateTime today) {
        this.flats = flats;
        this.today = today;
    }

    public void setScripts(ArrayList<String> scripts) {
        this.scripts = scripts;
    }

    public Execute_Script () {}

    public Execute_Script (String sFile, HashSet<Flat> flats, ArrayList<String> history, LocalDateTime today,
                           BufferedReader reader, File file, ArrayList<String> scripts) {
        this.sFile = sFile;
        this.flats = flats;
        this.history = history;
        this.today = today;
        this.reader = reader;
        this.scripts = scripts;
    }

    public Execute_Script(String sFile, ArrayList<String> history) {
        this.history = history;
        this.sFile = sFile;
    }

    public Execute_Script(String sFile) {
        this.sFile = sFile;
    }

    public void getCommandsFromFile() {
        File script = new File(sFile);
        scriptCommands = Instruments.Processing.scriptToString(script, scripts);
    }

    public void execute() {
        String userCommand;
        for (int i = 0; i<scriptCommands.size(); i++) {
            userCommand = scriptCommands.get(i);
            UserCommandsScript.check(userCommand);
            if (UserCommandsScript.getStatus() == 1) {
                try {
                    UserCommandsScript.execute(userCommand, reader, flats, today, history, scriptCommands, i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (userCommand.equalsIgnoreCase("add")) i += 12;
                try {
                    if (userCommand.substring(0, 6).equalsIgnoreCase("update") && UserCommandsScript.getStatus() == 1)
                        i += 12;
                } catch (Exception e) {
                }
            }
        }
        info = ScriptInfo.getInfo();
    }

    public String toStrings() {
        return "execute_script " + sFile;
    }
}
