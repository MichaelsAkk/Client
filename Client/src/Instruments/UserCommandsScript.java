package Instruments;

import Classes.Flat;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import Commands.*;

public class UserCommandsScript extends UserCommands {

    public static void check (String userCommand) {       //Определяет status
        UserCommands.status = ProcessingScript.commands (userCommand.toLowerCase(), userCommand);
    }

    public static void execute(String userCommand, BufferedReader reader, HashSet<Flat> flats, LocalDateTime today, ArrayList<String> history, ArrayList<String> scriptCommands, int i) throws IOException {      // Выполняет команду, если status == 1
        if (UserCommands.status == 1){
            if (userCommand.equalsIgnoreCase("help")) {
                Help help = new Help();
                help.execute();
                ScriptInfo.setInfo(help.getInfo());
            } else
            if (userCommand.equalsIgnoreCase("exit")) {
                ScriptInfo.setInfo("Сервер не поддерживает команду exit");
            } else
            if (userCommand.equalsIgnoreCase("show")) {
                Show show = new Show(flats);
                show.execute();
                ScriptInfo.setInfo(show.getInfo());
            } else
            if (userCommand.equalsIgnoreCase("info")) {
                Info infoCom = new Info(flats, today);
                infoCom.execute();
                ScriptInfo.setInfo(infoCom.getInfo());
            } else
            if (userCommand.equalsIgnoreCase("add")) {
                ScriptAdd add = new ScriptAdd(flats, scriptCommands, i);
                if (add.fields() != 0) {
                    add.execute();
                    ScriptInfo.setInfo(add.getInfo());
                } else {
                    ScriptInfo.setInfo(add.getInfo());
                    ScriptInfo.setInfo("Сбой создания элеммента коллекции");
                }
            } else
            if (userCommand.equalsIgnoreCase("clear")) {
                Clear clear = new Clear(flats);
                clear.execute();
                ScriptInfo.setInfo(clear.getInfo());
            } else
            if (userCommand.equalsIgnoreCase("save")) {
                ScriptInfo.setInfo("Сервер не поддерживает команду save");
            } else
            if (userCommand.equalsIgnoreCase("history")) {
                History his = new History(history);
                his.execute();
                ScriptInfo.setInfo(his.getInfo());
            } else
            if (userCommand.equalsIgnoreCase("average_of_number_of_rooms")) {
                Average_Of_Number_Of_Rooms avg = new Average_Of_Number_Of_Rooms(flats);
                avg.execute();
                ScriptInfo.setInfo(avg.getInfo());
            } else
            if (userCommand.equalsIgnoreCase("print_ascending")) {
                Print_Ascending pr = new Print_Ascending(flats);
                pr.execute();
                ScriptInfo.setInfo(pr.getInfo());
            }
            try {
                if (userCommand.substring(0, 6).equalsIgnoreCase("update")) {
                    String[] parts = userCommand.split(" ", 2);
                    Integer id = 0; int control = 1;
                    try {
                        id = Integer.parseInt(parts[1]);
                    }
                    catch (Exception e) {
                        control = 0;
                    }
                    if (control != 0) {
                        ScriptUpdate update = new ScriptUpdate(flats, id, scriptCommands, i);
                        if (update.fieldsUpdate() == 1) ScriptInfo.setInfo("Элемент обновлен");
                        else {
                            ScriptInfo.setInfo(update.getInfo());
                            ScriptInfo.setInfo("Сбой обновления элемента коллекции");
                        }
                    }
                }
            }
            catch (Exception e) {
            }
            try {
                if (userCommand.substring(0, 12).equalsIgnoreCase("remove_by_id")) {
                    String[] parts = userCommand.split(" ", 2);
                    Integer id = 0; int control = 1;
                    try {
                        id = Integer.parseInt(parts[1]);
                    }
                    catch (Exception e) {
                    }
                    if (control != 0) {
                        Remove_By_Id rmi = new Remove_By_Id(flats, id);
                        rmi.execute();
                        ScriptInfo.setInfo(rmi.getInfo());
                    }
                }
            }
            catch (Exception e) {
            }

            try {
                if (userCommand.substring(0, 14).equalsIgnoreCase("remove_greater")) {
                    String[] parts = userCommand.split(" ", 2);
                    int numberOfRooms = Integer.parseInt(parts[1]);
                    Remove_Greater rg = new Remove_Greater(flats, numberOfRooms);
                    rg.execute();
                    ScriptInfo.setInfo(rg.getInfo());
                }
            }
            catch (Exception e) {
            }
            try {
                if (userCommand.substring(0, 12).equalsIgnoreCase("remove_lower")) {
                    String[] parts = userCommand.split(" ", 2);
                    int numberOfRooms = Integer.parseInt(parts[1]);
                    Remove_Lower rl = new Remove_Lower(flats, numberOfRooms);
                    rl.execute();
                    ScriptInfo.setInfo(rl.getInfo());
                }
            }
            catch (Exception e) {
            }
            try {
                if (userCommand.substring(0, 25).equalsIgnoreCase("filter_by_number_of_rooms")) {
                    String[] parts = userCommand.split(" ", 2);
                    int numberOfRooms = Integer.parseInt(parts[1]);
                    Filter_By_Number_Of_Rooms filt = new Filter_By_Number_Of_Rooms(flats, numberOfRooms);
                    filt.execute();
                    ScriptInfo.setInfo(filt.getInfo());
                }
            }
            catch (Exception e) {
            }
        }
    }

    public static int getStatus () {
        return status;
    }
}
