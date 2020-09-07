package ClientApp;

import Commands.*;
import Instruments.Processing;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class ClientUserCommands { // обработка введенных пользователем данных
    protected static int status;               // 1-команда была введена пользователем верно, 0-неверно

    public static void check (String userCommand) {       //Определяет status
        ClientUserCommands.status = Processing.commands (userCommand.toLowerCase(), userCommand);
    }

    public static Object getCommand(String userCommand, BufferedReader reader) throws IOException {      // Выполняет команду, если status == 1
        if (ClientUserCommands.status == 1){
            switch(userCommand.toLowerCase()) {
                case ("help"):
                    Help help = new Help();
                    return help;
                case ("exit"):
                    reader.close();
                    System.out.println("Работа Client app завешрена");
                    System.exit(0);
                case ("show"):
                    Show show = new Show();
                    return show;
                case ("info"):
                    Info info = new Info();
                    return info;
                case ("add"):
                    Add add = new Add();
                    add.fields();
                    return add;
                case ("clear"):
                    Clear clear = new Clear();
                    return clear;
                case ("history"):
                    History his = new History(ProcessingAnswer.getHistory());
                    return his;
                case ("average_of_number_of_rooms"):
                    Average_Of_Number_Of_Rooms avg = new Average_Of_Number_Of_Rooms();
                    return avg;
                case ("print_ascending"):
                    Print_Ascending pr = new Print_Ascending();
                    return pr;
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
                        Update update = new Update(id);
                        return update;
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
                        if (id<=0) {
                            System.out.println("Ошибка ввода: id не является положительным числом");
                            control = 0;
                        }
                    }
                    catch (Exception e) {
                        System.out.println("Ошибка ввода: id не является целым числом");
                        control = 0;
                    }
                    if (control != 0) {
                        Remove_By_Id rmi = new Remove_By_Id(id);
                        return rmi;
                    }
                }
            }
            catch (Exception e) {
            }
            try {
                if (userCommand.substring(0, 14).equalsIgnoreCase("execute_script")) {
                    String[] parts = userCommand.split(" ", 2);
                    Execute_Script es = new Execute_Script(parts[1], ProcessingAnswer.getHistory());
                    ArrayList<String> scripts = new ArrayList<>(); scripts.add(parts[1]);
                    es.setScripts(scripts);
                    es.getCommandsFromFile();
                    return es;
                }
            }
            catch (Exception e) {
            }
            try {
                if (userCommand.substring(0, 14).equalsIgnoreCase("remove_greater")) {
                    String[] parts = userCommand.split(" ", 2); int control = 1;
                    try {
                        int numberOfRooms = Integer.parseInt(parts[1]);
                        if (numberOfRooms<=0) {
                            System.out.println("Ошибка ввода: numberOfRooms не является положительным числом");
                            control = 0;
                        }
                        if (control != 0) {
                            Remove_Greater rg = new Remove_Greater(numberOfRooms);
                            return rg;
                        }
                    } catch (Exception e) {
                        System.out.println("Ошибка ввода: id не является целым числом");
                    }
                }
            }
            catch (Exception e) {
            }
            try {
                if (userCommand.substring(0, 12).equalsIgnoreCase("remove_lower")) {
                    String[] parts = userCommand.split(" ", 2); int control = 1;
                    try {
                        int numberOfRooms = Integer.parseInt(parts[1]);
                        if (numberOfRooms<=0) {
                            System.out.println("Ошибка ввода: numberOfRooms не является положительным числом");
                            control = 0;
                        }
                        if (control != 0) {
                            Remove_Lower rl = new Remove_Lower(numberOfRooms);
                            return rl;
                        }
                    } catch (Exception e) {
                        System.out.println("Ошибка ввода: id не является целым числом");
                    }
                }
            }
            catch (Exception e) {
            }
            try {
                if (userCommand.substring(0, 25).equalsIgnoreCase("filter_by_number_of_rooms")) {
                    String[] parts = userCommand.split(" ", 2);
                    int numberOfRooms = Integer.parseInt(parts[1]);
                    Filter_By_Number_Of_Rooms filt = new Filter_By_Number_Of_Rooms(numberOfRooms);
                    return filt;
                }
            }
            catch (Exception e) {
            }
        }
        return null;
    }

    public static int getStatus () {
        return status;
    }
}
