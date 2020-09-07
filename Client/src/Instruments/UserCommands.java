package Instruments;

import Classes.Flat;

import Commands.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

public abstract class UserCommands { // обработка введенных пользователем данных

    protected static int status;                            // 1-команда была введена пользователем верно, 0-неверно
    public static void check (String userCommand, ArrayList<String> history) {       //Определяет status
        Instruments.UserCommands.status = Processing.commands (userCommand.toLowerCase(), userCommand);
        if (Instruments.UserCommands.status == 1) {
            System.out.println("Выполняем...");
            if (history.size()<8) {
                String[] parts = userCommand.split(" ");
                history.add(parts[0]);
            }
            else {
                history.remove(0);
                String[] parts = userCommand.split(" ");
                history.add(parts[0]);
            }
        }
    }

    public static void execute(String userCommand, BufferedReader reader, HashSet<Flat> flats, LocalDateTime today, File file, ArrayList<String> history, ArrayList<String> scripts) throws IOException {      // Выполняет команду, если status == 1
        if (Instruments.UserCommands.status == 1){
            switch(userCommand.toLowerCase()) {
                case ("help"):
                    Help help = new Help();
                    help.execute();
                    break;
                case ("exit"):
                    Exit exit = new Exit (reader);
                    exit.execute();
                    break;
                case ("show"):
                    Show show = new Show(flats);
                    show.execute();
                    break;
                case ("info"):
                    Info info = new Info(flats, today);
                    info.execute();
                    break;
                case ("add"):
                    Add add = new Add(flats);
                    if (add.fields()==1) {
                        add.execute();
                    }
                    else System.out.println("Сбой создания элемента коллекции");
                    break;
                case ("clear"):
                    Clear clear = new Clear(flats);
                    clear.execute();
                    break;
                case ("save"):
                    Save save = new Save (flats, file);
                    save.execute();
                    break;
                case ("history"):
                    History his = new History(history);
                    his.execute();
                    break;
                case ("average_of_number_of_rooms"):
                    Average_Of_Number_Of_Rooms avg = new Average_Of_Number_Of_Rooms(flats);
                    avg.execute();
                    break;
                case ("print_ascending"):
                    Print_Ascending pr = new Print_Ascending(flats);
                    pr.execute();
                    break;
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
                        Update update = new Update(flats, id);
                        if (update.fieldsUpdate() == 1) System.out.println("Элемент обновлен");
                        else System.out.println("Сбой обновления элемента коллекции");
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
                    }
                }
            }
            catch (Exception e) {
            }
            try {
                if (userCommand.substring(0, 14).equalsIgnoreCase("execute_script")) {
                    String[] parts = userCommand.split(" ", 2);
                    Execute_Script es = new Execute_Script(parts[1], flats, history, today, reader, file, scripts);
                    es.execute();
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
                }
            }
            catch (Exception e) {
            }
        }
    }
}
