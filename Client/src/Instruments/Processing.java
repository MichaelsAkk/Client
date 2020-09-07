package Instruments;

import Classes.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import Commands.Execute_Script;

public abstract class Processing {

    public static int commands (String userCommand, String OriginalUC) {                  // обрабатывает введенную пользователем команду
        if ((userCommand.length() == 6 && userCommand.equalsIgnoreCase("update")) ||
                (userCommand.length() == 7 && userCommand.equalsIgnoreCase("update "))) {
            System.out.println("Для команды \"" + userCommand + "\" требуется указать id нового элемента коллекции. " +
                    "Для просмотра списка команд введите \"help\".");
            return 0;
        }
        else if ((userCommand.length() > 7 && userCommand.contains("update "))) {
            String[] parts = userCommand.split(" ",3);
            if (parts.length != 2) {
                System.out.println("Для команды update требуется указать только id нового элемента коллекции. " +
                        "Для просмотра списка команд введите \"help\".");
                return 0;
            }
            else {
                try {
                    byte id = Byte.parseByte((parts[1]));
                    if (id <= 0) {
                        System.out.println("Id элемента коллекции должен быть целым положительным числом, которое меньше 128");
                        return 0;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Id элемента коллекции должен быть целым положительным числом, которое меньше 128");
                    return 0;
                }
            }
        }

        else if ((userCommand.length() == 12 && userCommand.equalsIgnoreCase("remove_by_id")) ||
                (userCommand.length() == 13 && userCommand.equalsIgnoreCase("remove_by_id "))) {
            System.out.println("Для команды \"" + userCommand + "\" требуется указать id элемента коллекции. " +
                    "Для просмотра списка команд введите \"help\".");
            return 0;
        }

        else if ((userCommand.length() > 13 && userCommand.contains("remove_by_id "))) {
            String[] parts = userCommand.split(" ",3);
            if (parts.length != 2) {
                System.out.println("Для команды \"remove_by_id\" требуется указать только id элемента коллекции. " +
                        "Для просмотра списка команд введите \"help\".");
                return 0;
            }
            else {
                try {
                    byte id = Byte.parseByte((parts[1]));
                    if (id <= 0) {
                        System.out.println("Id элемента коллекции должен быть целым положительным числом, которое меньше 128");
                        return 0;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Id элемента коллекции должен быть целым положительным числом, которое меньше 128");
                    return 0;
                }
            }
        }

        else if ((userCommand.length() == 14 && userCommand.equalsIgnoreCase("execute_script")) ||
                (userCommand.length() == 15 && userCommand.equalsIgnoreCase("execute_script "))) {
            System.out.println("Для команды \"" + userCommand + "\" требуется указать имя файла. " +
                    "Для просмотра списка команд введите \"help\".");
            return 0;
        }

        else if ((userCommand.length() > 15 && userCommand.contains("execute_script "))) {
            String[] parts = OriginalUC.split(" ",3);
            if (parts.length != 2) {
                System.out.println("Для команды execute_script требуется указать только имя файла. " +
                        "Для просмотра списка команд введите \"help\".");
                return 0;
            }
            else {
                File file = new File(parts[1]);
                if (! file.exists()) {
                    System.out.println("Указанный файл не найден");
                    return 0;
                }
            }
        }
        else if ((userCommand.length() == 14 && userCommand.equalsIgnoreCase("remove_greater")) ||
                (userCommand.length() == 15 && userCommand.equalsIgnoreCase("remove_greater "))) {
            System.out.println("Для команды \"" + userCommand + "\" требуется указать значение поля \"numberOfRooms\" элемента коллекции. " +
                    "Для просмотра списка команд введите \"help\".");
            return 0;
        }

        else if ((userCommand.length() > 15 && userCommand.contains("remove_greater "))) {
            String[] parts = userCommand.split(" ",3);
            if (parts.length != 2) {
                System.out.println("Для команды \"remove_greater\" требуется указать только значение поля \"numberOfRooms\" элемента коллекции. " +
                        "Для просмотра списка команд введите \"help\".");
                return 0;
            }
            else {
                try {
                    int numberOfRooms = Integer.parseInt(parts[1]);
                    if (numberOfRooms<=0) { System.out.println("Поле \"numberOfRooms\" должно быть положительным числом"); return 0; }
                }
                catch (Exception e) {
                    System.out.println("Поле \"numberOfRooms\" должно быть целым числом");
                    return 0;
                }
            }
        }
        else if ((userCommand.length() == 12 && userCommand.equalsIgnoreCase("remove_lower")) ||
                (userCommand.length() == 13 && userCommand.equalsIgnoreCase("remove_lower "))) {
            System.out.println("Для команды \"" + userCommand + "\" требуется указать значение поля \"numberOfRooms\" элемента коллекции. " +
                    "Для просмотра списка команд введите \"help\".");
            return 0;
        }

        else if ((userCommand.length() > 13 && userCommand.contains("remove_lower "))) {
            String[] parts = userCommand.split(" ",3);
            if (parts.length != 2) {
                System.out.println("Для команды \"remove_lower\" требуется указать только значение поля \"numberOfRooms\" элемента коллекции. " +
                        "Для просмотра списка команд введите \"help\".");
                return 0;
            }
            else {
                try {
                    int numberOfRooms = Integer.parseInt(parts[1]);
                    if (numberOfRooms<=0) { System.out.println("Поле \"numberOfRooms\" должно быть положительным числом"); return 0; }
                }
                catch (Exception e) {
                    System.out.println("Поле \"numberOfRooms\" должно быть числом");
                    return 0;
                }
            }
        }

        else if ((userCommand.length() == 25 && userCommand.equalsIgnoreCase("filter_by_number_of_rooms")) ||
                (userCommand.length() == 26 && userCommand.equalsIgnoreCase("filter_by_number_of_rooms "))) {
            System.out.println("Для команды \"" + userCommand + "\" требуется указать поле numberOfRooms. " +
                    "Для просмотра списка команд введите \"help\".");
            return 0;
        }

        else if ((userCommand.length() > 26 && userCommand.contains("filter_by_number_of_rooms "))) {
            String[] parts = userCommand.split(" ",3);
            if (parts.length != 2) {
                System.out.println("Для команды \"filter_by_number_of_rooms\" требуется указать только поле numberOfRooms. " +
                        "Для просмотра списка команд введите \"help\".");
                return 0;
            }
            else {
                try {
                    int num = Integer.parseInt((parts[1]));
                    if (num <= 0) {
                        System.out.println("Значение поля должно быть целым числом, которое больше 0");
                        return 0;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Значение поля должно быть целым числом, которое больше 0");
                    return 0;
                }
            }
        }

        else {
            try {
                Commands command = Commands.valueOf(userCommand);
            }
            catch (IllegalArgumentException NullPointerException){
                System.out.println("Система не поддерживает комманду \""+userCommand+"\". " + "Для просмотра списка команд введите \"help\".");
                return 0;
            }
        }
        return 1;
    }

    public static String fileToString(File file) throws FileNotFoundException {  // читает файл и возвращает строку
        FileInputStream fis = null;
        InputStreamReader isr = null;
        int b = 0;
        String s = "";
        try {
            fis = new FileInputStream(file.getName());
            isr = new InputStreamReader(fis, "UTF-8");
            while ((b = isr.read()) != -1) {
                s = s + (char) b;
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Файл " + file.getName() + " не найден");
        }
        catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
        finally {
            try {
                fis.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (NullPointerException e) {
            }
            try {
                isr.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (NullPointerException e) {
            }
        }
        if (s != "") return s;
        else return "0";
    }

    public static HashSet<Flat> strToSet (String s) {                     // заполняет возвращает коллекцию типа Flat из указанной строки
        HashSet<Flat> flats = new HashSet<>();
        if (s != "0") {
            String[] parts = s.split("}}");
            if (parts.length == 1 && !parts[0].equalsIgnoreCase("[]")) {
                System.out.println("Ошибка чтения файла: файл не записан в формате json");
                return flats;
            }
            else if (parts.length == 1 && parts[0].equalsIgnoreCase("[]")) { System.out.println("Файл не заполнен"); return flats; }
            else {
                int metka = 0;
                String[] parts2;
                Integer id = null;
                Integer x = null;
                Integer y = null;
                Integer year = null;
                Integer month = null;
                Integer day = null;
                Integer houseYear = -1;
                String name = null;
                String houseName = null;
                String view = null;
                String transport = " ";
                Float area = -1.0f;
                int numberOfRooms = -1;
                boolean new1 = false;
                long numberOfFlatsOnFloor = -1;
                long numberOfLifts = -1;
                for (int i = 0; i <= parts.length - 2; i++) {
                    parts2 = parts[i].split(":");
                    id = null;
                    x = null;
                    y = null;
                    year = null;
                    month = null;
                    day = null;
                    houseYear = -1;
                    name = null;
                    houseName = null;
                    view = null;
                    transport = " ";
                    area = -1.0f;
                    numberOfRooms = -1;
                    new1 = false;
                    numberOfFlatsOnFloor = -1;
                    numberOfLifts = -1;
                    if (parts2.length != 20 && parts2.length != 19 && parts2.length != 18)  {
                        System.out.println("Ошибка чтения файла: файл не записан в формате json");
                        return flats;
                    }
                    else {
                        if (parts2.length == 20) metka = 20; else if (parts2.length == 19) metka = 19; else metka = 18;
                        for (int j = 0; j <= parts2.length - 1; j++) {
                            if (j == 1) {
                                if (parts2[j].substring(0, parts2[j].indexOf(",\"")).equalsIgnoreCase("null")) {
                                    System.out.println("Ошибка чтения файла: поле \"id\" содержит null");
                                    return flats;
                                }
                                else {
                                    try {
                                        id = Integer.parseInt(parts2[j].substring(0, parts2[j].indexOf(",\"")));
                                        if (id <= 0) {
                                            System.out.println("Ошибка чтения файла: поле \"id\" записано не как целое положительное число");
                                            return flats;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Ошибка чтения файла: поле \"id\" записано не как целое положительное число");
                                        return flats;
                                    }
                                }
                            }
                            if (j == 2) {
                                name = parts2[j].substring(1, parts2[j].indexOf("\","));
                                if (name.equalsIgnoreCase("null")) {
                                    System.out.println("Ошибка чтения файла: поле \"name\" содержит null");
                                    return flats;
                                }
                            }
                            if (j == 4) {
                                if (parts2[j].substring(0, parts2[j].indexOf(",\"")).equalsIgnoreCase("null")) {
                                    System.out.println("Ошибка чтения файла: поле \"x\" содержит null");
                                    return flats;
                                }
                                else {
                                    try {
                                        x = Integer.parseInt(parts2[j].substring(0, parts2[j].indexOf(",\"")));
                                        if (x <= -474) {
                                            System.out.println("Ошибка чтения файла: поле \"x\" записано не как целое число большее -474");
                                            return flats;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Ошибка чтения файла: поле \"x\" записано не как целое число");
                                        return flats;
                                    }
                                }
                            }
                            if (j == 5) {
                                if (parts2[j].substring(0, parts2[j].indexOf("}")).equalsIgnoreCase("null")) {
                                    System.out.println("Ошибка чтения файла: поле \"y\" содержит null");
                                    return flats;
                                }
                                else {
                                    try {
                                        y = Integer.parseInt(parts2[j].substring(0, parts2[j].indexOf("}")));
                                    } catch (Exception e) {
                                        System.out.println("Ошибка чтения файла: поле \"y\" записано не как целое число");
                                        return flats;
                                    }
                                }
                            }
                            if (j == 7) {
                                try {
                                    year = Integer.parseInt(parts2[j].substring(0, parts2[j].indexOf(",\"")));
                                    if ((year > 2020) || (year < 1900)) {
                                        System.out.println("Ошибка чтения файла: поле \"year\" записано не в диапазоне от 1900 до 2020");
                                        return flats;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Ошибка чтения файла: поле \"year\" записано не как целое положительное число");
                                    return flats;
                                }
                            }
                            if (j == 8) {
                                try {
                                    month = Integer.parseInt(parts2[j].substring(0, parts2[j].indexOf(",\"")));
                                    if ((month > 12) || (month <= 0)) {
                                        System.out.println("Ошибка чтения файла: поле \"month\" не входит в диапазон 1-12");
                                        return flats;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Ошибка чтения файла: поле \"month\" записано не как целое положительное число");
                                    return flats;
                                }
                            }
                            if (j == 9) {
                                try {
                                    day = Integer.parseInt(parts2[j].substring(0, parts2[j].indexOf("}")));
                                    if ((day > 31) || (day <= 0)) {
                                        System.out.println("Ошибка чтения файла: поле \"day\" не входит в диапазон 1-31");
                                        return flats;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Ошибка чтения файла: поле \"day\" записано не как целое положительное число");
                                    return flats;
                                }
                            }
                            if (j == 10) {
                                if (parts2[j].substring(0, parts2[j].indexOf(",\"")).equalsIgnoreCase("null")) area = null;
                                else {
                                    try {
                                        area = Float.parseFloat(parts2[j].substring(0, parts2[j].indexOf(",\"")));
                                        if (area <= 0) {
                                            System.out.println("Ошибка чтения файла: поле \"area\" записано не как вещественное положительное число");
                                            return flats;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Ошибка чтения файла: поле \"area\" записано не как вещественное число");
                                        return flats;
                                    }
                                }
                            }
                            if (j == 11) {
                                try {
                                    numberOfRooms = Integer.parseInt(parts2[j].substring(0, parts2[j].indexOf(",\"")));
                                    if (numberOfRooms <= 0) {
                                        System.out.println("Ошибка чтения файла: поле \"numberOfRooms\" записано не как целоее положительное число");
                                        return flats;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Ошибка чтения файла: поле \"numberOfRooms\" записано не как целое число");
                                    return flats;
                                }
                            }
                            if (j == 12) {
                                if (parts2[j].substring(0, parts2[j].indexOf(",\"")).equalsIgnoreCase("true")) new1 = true;
                                else new1 = false;
                            }
                            if (j == 13) {
                                view = parts2[j].substring(1, parts2[j].indexOf("\","));
                                if (view.equalsIgnoreCase("null")) {
                                    System.out.println("Ошибка чтения файла: недопустимое значение в поле \"view\": null");
                                    return flats;
                                }
                                else {
                                    try {
                                        View view2 = View.valueOf(view.toUpperCase());
                                    } catch (Exception e) {
                                        System.out.println("Ошибка чтения файла: недопустимое значение в поле \"view\"");
                                        return flats;
                                    }
                                }
                            }
                            if (j == 14 && metka == 20) {

                                transport = parts2[j].substring(1, parts2[j].indexOf("\","));
                                try {
                                    Transport transport2 = Transport.valueOf(transport);
                                } catch (Exception e) {
                                    System.out.println("Ошибка чтения файла: недопустимое значение в поле \"transport\"");
                                    return flats;
                                }


                                houseName = parts2[j+2].substring(1, parts2[j+2].indexOf("\","));
                                if (houseName.equalsIgnoreCase("null")) {
                                    System.out.println("Ошибка чтения файла: поле \"House:name\" содержит null");
                                    return flats;
                                }

                                if (parts2[j+3].substring(0, parts2[j+3].indexOf(",\"")).equalsIgnoreCase("null"))
                                    houseYear = null;

                                else {
                                    try {
                                        houseYear = Integer.parseInt(parts2[j+3].substring(0, parts2[j+3].indexOf(",\"")));
                                        if ((houseYear < 0) || (houseYear > 2020)) {
                                            System.out.println("Ошибка чтения файла: поле \"House:year\" записано не как целое положительное число, не превышающее 2020");
                                            return flats;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Ошибка чтения файла: поле \"House:year\" записано не как целое число");
                                        return flats;
                                    }
                                }
                                try {
                                    numberOfFlatsOnFloor = Long.parseLong(parts2[j+4].substring(0, parts2[j+4].indexOf(",\"")));
                                    if (numberOfFlatsOnFloor <= 0) {
                                        System.out.println("Ошибка чтения файла: поле \"House: numberOfFlatsOnFloor\" записано не как целое положительное число");
                                        return flats;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Ошибка чтения файла: поле \"House: numberOfFlatsOnFloor\" записано не как целое число");
                                    return flats;
                                }


                                try {
                                    numberOfLifts = Long.parseLong(parts2[j+5]);
                                    if (numberOfLifts <= 0) {
                                        System.out.println("Ошибка чтения файла: поле \"House: numberOfLifts\" записано не как целое положительное число");
                                        return flats;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Ошибка чтения файла: поле \"House: numberOfLifts\" записано не как целое число");
                                    return flats;
                                }

                            }
                            if (j == 14 && metka == 19) {
                                if (parts2[j].substring(0, 1).equalsIgnoreCase("{")) {

                                    transport = null;

                                    houseName = parts2[j+1].substring(1, parts2[j+1].indexOf("\","));
                                    if (houseName.equalsIgnoreCase("null")) {
                                        System.out.println("Ошибка чтения файла: поле \"House:name\" содержит null");
                                        return flats;
                                    }

                                    if (parts2[j+2].substring(0, parts2[j+2].indexOf(",\"")).equalsIgnoreCase("null"))
                                        houseYear = null;
                                    else {
                                        try {
                                            houseYear = Integer.parseInt(parts2[j+2].substring(0, parts2[j+2].indexOf(",\"")));
                                            if ((houseYear < 0) || (houseYear > 2020)) {
                                                System.out.println("Ошибка чтения файла: поле \"House:year\" записано не как целое положительное число, не превышающее 2020");
                                                return flats;
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Ошибка чтения файла: поле \"House:year\" записано не как целое число");
                                            return flats;
                                        }
                                    }

                                    try {
                                        numberOfFlatsOnFloor = Long.parseLong(parts2[j+3].substring(0, parts2[j+3].indexOf(",\"")));
                                        if (numberOfFlatsOnFloor <= 0) {
                                            System.out.println("Ошибка чтения файла: поле \"House: numberOfFlatsOnFloor\" записано не как целое положительное число");
                                            return flats;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Ошибка чтения файла: поле \"House: numberOfFlatsOnFloor\" записано не как целое число");
                                        return flats;
                                    }

                                    try {
                                        numberOfLifts = Long.parseLong(parts2[j+4]);
                                        if (numberOfLifts <= 0) {
                                            System.out.println("Ошибка чтения файла: поле \"House: numberOfLifts\" записано не как целое положительное число");
                                            return flats;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Ошибка чтения файла: поле \"House: numberOfLifts\" записано не как целое число");
                                        return flats;
                                    }
                                }
                                else {

                                    transport = parts2[j].substring(1, parts2[j].indexOf("\","));
                                    if (transport.equalsIgnoreCase("null")) transport = null;
                                    else {
                                        try {
                                            Transport transport2 = Transport.valueOf(transport);
                                        } catch (Exception e) {
                                            System.out.println("Ошибка чтения файла: недопустимое значение в поле \"transport\"");
                                            return flats;
                                        }
                                    }

                                    houseName = parts2[j+2].substring(1, parts2[j+2].indexOf("\","));
                                    if (houseName.equalsIgnoreCase("null")) {
                                        System.out.println("Ошибка чтения файла: поле \"House:name\" содержит null");
                                        return flats;
                                    }

                                    houseYear = null;

                                    try {
                                        numberOfFlatsOnFloor = Long.parseLong(parts2[j+3].substring(0, parts2[j+3].indexOf(",\"")));
                                        if (numberOfFlatsOnFloor <= 0) {
                                            System.out.println("Ошибка чтения файла: поле \"House: numberOfFlatsOnFloor\" записано не как целое положительное число");
                                            return flats;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Ошибка чтения файла: поле \"House: numberOfFlatsOnFloor\" записано не как целое число");
                                        return flats;
                                    }

                                    try {
                                        numberOfLifts = Long.parseLong(parts2[j+4]);
                                        if (numberOfLifts <= 0) {
                                            System.out.println("Ошибка чтения файла: поле \"House: numberOfLifts\" записано не как целое положительное число");
                                            return flats;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Ошибка чтения файла: поле \"House: numberOfLifts\" записано не как целое число");
                                        return flats;
                                    }
                                }
                            }
                            if (j == 14 && metka == 18) {
                                transport = null; houseYear = null;

                                houseName = parts2[j+1].substring(1, parts2[j+1].indexOf("\","));
                                if (houseName.equalsIgnoreCase("null")) {
                                    System.out.println("Ошибка чтения файла: поле \"House:name\" содержит null");
                                    return flats;
                                }

                                try {
                                    numberOfFlatsOnFloor = Long.parseLong(parts2[j+2].substring(0, parts2[j+2].indexOf(",\"")));
                                    if (numberOfFlatsOnFloor <= 0) {
                                        System.out.println("Ошибка чтения файла: поле \"House: numberOfFlatsOnFloor\" записано не как целое положительное число");
                                        return flats;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Ошибка чтения файла: поле \"House: numberOfFlatsOnFloor\" записано не как целое число");
                                    return flats;
                                }

                                try {
                                    numberOfLifts = Long.parseLong(parts2[j+3]);
                                    if (numberOfLifts <= 0) {
                                        System.out.println("Ошибка чтения файла: поле \"House: numberOfLifts\" записано не как целое положительное число");
                                        return flats;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Ошибка чтения файла: поле \"House: numberOfLifts\" записано не как целое число");
                                    return flats;
                                }
                            }
                        }

                        if (houseYear != null) {
                            if (transport != null) {
                                if (id != null && name != null && x != null && y != null && year != null && month != null && day != null && area != -1.0f &&
                                        numberOfRooms != -1 && view != null && transport != " " && houseName != null && houseYear != -1 &&
                                        numberOfFlatsOnFloor != -1 && numberOfLifts != -1) {
                                    flats.add(new Flat(id, name, new Coordinates(x, y), LocalDate.of(year, month, day), area, numberOfRooms, new1, View.valueOf(view), Transport.valueOf(transport), new House(houseName, houseYear, numberOfFlatsOnFloor, numberOfLifts)));
                                }
                            } else {
                                if (id != null && name != null && x != null && y != null && year != null && month != null && day != null && area != -1.0f &&
                                        numberOfRooms != -1 && view != null && transport != " " && houseName != null && houseYear != -1 &&
                                        numberOfFlatsOnFloor != -1 && numberOfLifts != -1) {
                                    flats.add(new Flat(id, name, new Coordinates(x, y), LocalDate.of(year, month, day), area, numberOfRooms, new1, View.valueOf(view), null, new House(houseName, houseYear, numberOfFlatsOnFloor, numberOfLifts)));
                                }
                            }
                        } else {
                            if (transport != null) {
                                if (id != null && name != null && x != null && y != null && year != null && month != null && day != null && area != -1.0f &&
                                        numberOfRooms != -1 && view != null && transport != " " && houseName != null &&
                                        numberOfFlatsOnFloor != -1 && numberOfLifts != -1) {
                                    flats.add(new Flat(id, name, new Coordinates(x, y), LocalDate.of(year, month, day), area, numberOfRooms, new1, View.valueOf(view), Transport.valueOf(transport), new House(houseName, houseYear, numberOfFlatsOnFloor, numberOfLifts)));
                                }
                            } else {
                                if (id != null && name != null && x != null && y != null && year != null && month != null && day != null && area != -1.0f &&
                                        numberOfRooms != -1 && view != null && transport != " " && houseName != null &&
                                        numberOfFlatsOnFloor != -1 && numberOfLifts != -1) {
                                    flats.add(new Flat(id, name, new Coordinates(x, y), LocalDate.of(year, month, day), area, numberOfRooms, new1, View.valueOf(view), null, new House(houseName, houseYear, numberOfFlatsOnFloor, numberOfLifts)));
                                }
                            }
                        }
                    }
                }
                return flats;
            }

        }
        else { System.out.println("Файл не заполнен"); return flats; }
    }

    public static ArrayList<String> scriptToString (File script, ArrayList<String> scripts) {
        ArrayList<String> result = new ArrayList<>();
        try {
            FileReader fr = new FileReader(script);
            BufferedReader reader = new BufferedReader(fr);
            String line; String[] parts;
            while (( line = reader.readLine()) != null) {
                parts = line.split(" ");
                if (!parts[0].equalsIgnoreCase("execute_script")) {
                    result.add(line);
                } else {
                    for (int i = 0; i<scripts.size(); i++) {
                        if (scripts.get(i).equalsIgnoreCase(parts[1])) {
                            System.out.println("Ошибка: наблюдается рекурсия.");
                            return result;
                        }
                    }
                    scripts.add(parts[1]);
                    Execute_Script es = new Execute_Script(parts[1]);
                    es.setScripts(scripts);
                    es.getCommandsFromFile();
                    for (int i = 0; i<es.getScriptCommands().size(); i++) {
                        result.add(es.getScriptCommands().get(i));
                    }
                }
            }
            fr.close();
            reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Указанный файл не найден");
            return result;
        }
        catch (IOException e) {
            System.out.println("Ошибка чтения файла");
            return result;
        }
        return result;
    }

    public static String getFilePath(String variable) {
        String path = System.getenv(variable);
        if (path == null) {
            return "";
        }
        else return path;
    }
}
