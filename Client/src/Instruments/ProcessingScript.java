package Instruments;

import java.io.File;
import java.util.ArrayList;


public class ProcessingScript extends Processing {

    public static int commands (String userCommand, String OriginalUC) {                  // обрабатывает введенную пользователем команду
        if ((userCommand.length() == 6 && userCommand.equalsIgnoreCase("update")) ||
                (userCommand.length() == 7 && userCommand.equalsIgnoreCase("update "))) {
            ScriptInfo.setInfo("Для команды \"" + userCommand + "\" требуется указать id нового элемента коллекции. " +
                    "Для просмотра списка команд введите \"help\".");
            return 0;
        }
        else if ((userCommand.length() > 7 && userCommand.contains("update "))) {
            String[] parts = userCommand.split(" ",3);
            if (parts.length != 2) {
                ScriptInfo.setInfo("Для команды update требуется указать только id нового элемента коллекции. " +
                        "Для просмотра списка команд введите \"help\".");
                return 0;
            }
            else {
                try {
                    byte id = Byte.parseByte((parts[1]));
                    if (id <= 0) {
                        ScriptInfo.setInfo("Id элемента коллекции должен быть целым положительным числом, которое меньше 128");
                        return 0;
                    }
                } catch (NumberFormatException e) {
                    ScriptInfo.setInfo("Id элемента коллекции должен быть целым положительным числом, которое меньше 128");
                    return 0;
                }
            }
        }

        else if ((userCommand.length() == 12 && userCommand.equalsIgnoreCase("remove_by_id")) ||
                (userCommand.length() == 13 && userCommand.equalsIgnoreCase("remove_by_id "))) {
            ScriptInfo.setInfo("Для команды \"" + userCommand + "\" требуется указать id элемента коллекции. " +
                    "Для просмотра списка команд введите \"help\".");
            return 0;
        }

        else if ((userCommand.length() > 13 && userCommand.contains("remove_by_id "))) {
            String[] parts = userCommand.split(" ",3);
            if (parts.length != 2) {
                ScriptInfo.setInfo("Для команды \"remove_by_id\" требуется указать только id элемента коллекции. " +
                        "Для просмотра списка команд введите \"help\".");
                return 0;
            }
            else {
                try {
                    byte id = Byte.parseByte((parts[1]));
                    if (id <= 0) {
                        ScriptInfo.setInfo("Id элемента коллекции должен быть целым положительным числом, которое меньше 128");
                        return 0;
                    }
                } catch (NumberFormatException e) {
                    ScriptInfo.setInfo("Id элемента коллекции должен быть целым положительным числом, которое меньше 128");
                    return 0;
                }
            }
        }

        else if ((userCommand.length() == 14 && userCommand.equalsIgnoreCase("execute_script")) ||
                (userCommand.length() == 15 && userCommand.equalsIgnoreCase("execute_script "))) {
            ScriptInfo.setInfo("Для команды \"" + userCommand + "\" требуется указать имя файла. " +
                    "Для просмотра списка команд введите \"help\".");
            return 0;
        }

        else if ((userCommand.length() > 15 && userCommand.contains("execute_script "))) {
            String[] parts = OriginalUC.split(" ",3);
            if (parts.length != 2) {
                ScriptInfo.setInfo("Для команды execute_script требуется указать только имя файла. " +
                        "Для просмотра списка команд введите \"help\".");
                return 0;
            }
            else {
                File file = new File(parts[1]);
                if (! file.exists()) {
                    ScriptInfo.setInfo("Указанный файл не найден");
                    return 0;
                }
            }
        }
        else if ((userCommand.length() == 14 && userCommand.equalsIgnoreCase("remove_greater")) ||
                (userCommand.length() == 15 && userCommand.equalsIgnoreCase("remove_greater "))) {
            ScriptInfo.setInfo("Для команды \"" + userCommand + "\" требуется указать значение поля \"numberOfRooms\" элемента коллекции. " +
                    "Для просмотра списка команд введите \"help\".");
            return 0;
        }

        else if ((userCommand.length() > 15 && userCommand.contains("remove_greater "))) {
            String[] parts = userCommand.split(" ",3);
            if (parts.length != 2) {
                ScriptInfo.setInfo("Для команды \"remove_greater\" требуется указать только значение поля \"numberOfRooms\" элемента коллекции. " +
                        "Для просмотра списка команд введите \"help\".");
                return 0;
            }
            else {
                try {
                    int numberOfRooms = Integer.parseInt(parts[1]);
                    if (numberOfRooms<=0) { ScriptInfo.setInfo("Поле \"numberOfRooms\" должно быть положительным числом"); return 0; }
                }
                catch (Exception e) {
                    ScriptInfo.setInfo("Поле \"numberOfRooms\" должно быть целым числом");
                    return 0;
                }
            }
        }
        else if ((userCommand.length() == 12 && userCommand.equalsIgnoreCase("remove_lower")) ||
                (userCommand.length() == 13 && userCommand.equalsIgnoreCase("remove_lower "))) {
            ScriptInfo.setInfo("Для команды \"" + userCommand + "\" требуется указать значение поля \"numberOfRooms\" элемента коллекции. " +
                    "Для просмотра списка команд введите \"help\".");
            return 0;
        }

        else if ((userCommand.length() > 13 && userCommand.contains("remove_lower "))) {
            String[] parts = userCommand.split(" ",3);
            if (parts.length != 2) {
                ScriptInfo.setInfo("Для команды \"remove_lower\" требуется указать только значение поля \"numberOfRooms\" элемента коллекции. " +
                        "Для просмотра списка команд введите \"help\".");
                return 0;
            }
            else {
                try {
                    int numberOfRooms = Integer.parseInt(parts[1]);
                    if (numberOfRooms<=0) { ScriptInfo.setInfo("Поле \"numberOfRooms\" должно быть положительным числом"); return 0; }
                }
                catch (Exception e) {
                    ScriptInfo.setInfo("Поле \"numberOfRooms\" должно быть числом");
                    return 0;
                }
            }
        }

        else if ((userCommand.length() == 25 && userCommand.equalsIgnoreCase("filter_by_number_of_rooms")) ||
                (userCommand.length() == 26 && userCommand.equalsIgnoreCase("filter_by_number_of_rooms "))) {
            ScriptInfo.setInfo("Для команды \"" + userCommand + "\" требуется указать поле numberOfRooms. " +
                    "Для просмотра списка команд введите \"help\".");
            return 0;
        }

        else if ((userCommand.length() > 26 && userCommand.contains("filter_by_number_of_rooms "))) {
            String[] parts = userCommand.split(" ",3);
            if (parts.length != 2) {
                ScriptInfo.setInfo("Для команды \"filter_by_number_of_rooms\" требуется указать только поле numberOfRooms. " +
                        "Для просмотра списка команд введите \"help\".");
                return 0;
            }
            else {
                try {
                    int num = Integer.parseInt((parts[1]));
                    if (num <= 0) {
                        ScriptInfo.setInfo("Значение поля должно быть целым числом, которое больше 0");
                        return 0;
                    }
                } catch (NumberFormatException e) {
                    ScriptInfo.setInfo("Значение поля должно быть целым числом, которое больше 0");
                    return 0;
                }
            }
        }

        else {
            try {
                Commands command = Commands.valueOf(userCommand);
            }
            catch (IllegalArgumentException NullPointerException){
                ScriptInfo.setInfo("Система не поддерживает комманду \""+userCommand+"\". " + "Для просмотра списка команд введите \"help\".");
                return 0;
            }
        }
        return 1;
    }
}
