package Commands;

import Classes.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;

public class ScriptAdd implements Serializable {
    private transient HashSet<Flat> flats;
    private transient Flat flat;
    private int i;
    private transient ArrayList<String> scriptCommands;
    private String info;

    public ScriptAdd (HashSet<Flat> flats, ArrayList<String> scriptCommands, int i) {
        this.flats = flats;
        this.scriptCommands = scriptCommands;
        this.i = i;
    }

    public String getInfo() {
        return this.info;
    }

    public int fields() {
        String result; Integer id = null; String name; Integer x = null; Integer y = null;
        Integer houseYear = -1; String houseName = null; View view = null; Transport transport = null; Float area = -1.0f;
        int numberOfRooms = -1; boolean new1 = false; long numberOfFlatsOnFloor = -1; long numberOfLifts = -1;

        int control = 0;

        id = 1 + (int) Math.round(Math.random()*100);
        int idCopy = id;
        if (flats != null) {
            int con2 = id;
            while (control==0) {
                for (Flat f : flats) {
                    if (f.getId() == idCopy) {
                        id++;
                    }
                }
                if (con2 == id) control++;
                else con2 = id;
            }
        }

        result = scriptCommands.get(i+1);
        if (result.equalsIgnoreCase("null")) { info = "Ошибка ввода: поле \"name\" содержит null"; return 0; }
        else if (result.equalsIgnoreCase("")) { info = "Ошибка ввода: поле \"name\" не может быть пустым"; return 0; }
        else name = result;

        result = scriptCommands.get(i+2);
        try {
            if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) { info = "Ошибка ввода: поле \"x\" содержит null"; return 0; }
            x = Integer.parseInt(result);
            if (x<=-474) { info = "Ошибка ввода: поле \"x\" должно быть больше -474"; return 0; }
        } catch (NumberFormatException e) {
            info = "Ошибка ввода: поле \"x\" не является целым числом";
            return 0;
        }

        result = scriptCommands.get(i+3);
        try {
            if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) { info = "Ошибка ввода: поле \"y\" содержит null"; return 0; }
            y = Integer.parseInt(result);
        } catch (NumberFormatException e) {
            info = "Ошибка ввода: поле \"y\" не является целым числом";
            return 0;
        }

        result = scriptCommands.get(i+4);
        try {
            if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) { info = "Ошибка ввода: поле \"area\" содержит null";
                return 0;}
            else {
                area = Float.parseFloat(result);
                if (area <= 0) {
                    info = "Ошибка ввода: поле \"area\" должно быть больше 0";
                    return 0;
                }
            }
        } catch(NumberFormatException e){
            info = "Ошибка ввода: поле \"area\" должно быть числом (дробная часть указывается после символа \".\")";
            return 0;
        }

        result = scriptCommands.get(i+5);
        try {
            numberOfRooms = Integer.parseInt(result);
            if (numberOfRooms <= 0) {
                info = "Ошибка ввода: поле \"numberOfRooms\" должно быть целым положительным числом";
                return 0;
            }
        } catch (NumberFormatException e) {
            info = "Ошибка ввода: поле \"numberOfRooms\" не является целым числом";
            return 0;
        }

        result = scriptCommands.get(i+6);
        if (!result.equalsIgnoreCase("true") && !result.equalsIgnoreCase("false")) {
            info = "Ошибка ввода: поле \"new\" должно быть либо \"true\", либо \"false\" ";
            return 0;
        }
        else if (result.equalsIgnoreCase("true")) new1 = true;
        else new1 = false;


        result = scriptCommands.get(i+7);
        try {
            if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) {
                info = "Ошибка ввода: поле \"view\" содержит null";
                return 0;
            }
            else {
                view = View.valueOf(result.toUpperCase());
            }
        }
        catch(IllegalArgumentException e){
            info = "Ошибка ввода: поле \"view\" не содержит значение из указанного списка";
            return 0;
        }

        result = scriptCommands.get(i+8);
        try {
            if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) transport = null;
            else {
                transport = Transport.valueOf(result.toUpperCase());
            }
        }
        catch(IllegalArgumentException e){
            info = "Ошибка ввода: поле \"transport\" не содержит значение из указанного списка";
            return 0;
        }

        result = scriptCommands.get(i+9);
        if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) { info = "Ошибка ввода: поле \"House.name\" содержит null"; return 0; }
        else houseName = result;

        result = scriptCommands.get(i+10);
        try {
            if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) houseYear = null;
            else {
                houseYear = Integer.parseInt(result);
                if (houseYear <= 0) { info = "Ошибка ввода: поле \"House.year\" не является целым положительным числом"; return 0; }
            }
        } catch(NumberFormatException e){
            info = "Ошибка ввода: поле \"House.year\" не является целым числом";
            return 0;
        }

        LocalDate randomDate = null;
        if (houseYear == null) {
            GregorianCalendar gc = new GregorianCalendar();
            int randomYear = 1900 + (int) Math.round(Math.random() * (2020 - 1900));
            gc.set(gc.YEAR, randomYear);
            int randomDay = 1 + (int) Math.round(Math.random() * (gc.getActualMaximum(gc.DAY_OF_YEAR) - 1900));
            gc.set(gc.DAY_OF_YEAR, randomDay);
            randomDate = LocalDate.of(gc.get(gc.YEAR), gc.get(gc.MONTH) + 1, gc.get(gc.DAY_OF_MONTH));
        } else {
            GregorianCalendar gc = new GregorianCalendar();
            int randomYear = houseYear + (int) Math.round(Math.random() * (2020 - houseYear));
            gc.set(gc.YEAR, randomYear);
            int randomDay = 1 + (int) Math.round(Math.random() * (gc.getActualMaximum(gc.DAY_OF_YEAR) - 1));
            gc.set(gc.DAY_OF_YEAR, randomDay);
            randomDate = LocalDate.of(gc.get(gc.YEAR), gc.get(gc.MONTH) + 1, gc.get(gc.DAY_OF_MONTH));
        }

        result = scriptCommands.get(i+11);
        try {
            numberOfFlatsOnFloor = Long.parseLong(result);
            if (numberOfFlatsOnFloor <= 0) { info = "Ошибка ввода: поле \"House.NumberOfFlatsOnFloor\" не является целым положительным числом"; return 0; }
        } catch(NumberFormatException e){
            info = "Ошибка ввода: поле \"House.NumberOfFlatsOnFloor\" не является целым числом";
            return 0;
        }

        result = scriptCommands.get(i+12);
        try {
            numberOfLifts = Long.parseLong(result);
            if (numberOfLifts <= 0) { info = "Ошибка ввода: поле \"House.NumberOfLifts\" не является целым положительным числом"; return 0; }
        } catch(NumberFormatException e){
            info = "Ошибка ввода: поле \"House.NumberOfLifts\" не является целым числом";
            return 0;
        }

        if (houseYear==null) {
            if (id != null && name != null && x != null && y != null && area != -1.0f &&
                    numberOfRooms != -1 && view != null && houseName != null &&
                    numberOfFlatsOnFloor != -1 && numberOfLifts != -1) {
                flat = new Flat(id, name, new Coordinates(x, y), randomDate, area, numberOfRooms, new1, view, transport, new House(houseName, houseYear, numberOfFlatsOnFloor, numberOfLifts));
            }
        }
        else {
            if (id != null && name != null && x != null && y != null && area != -1.0f &&
                    numberOfRooms != -1 && view != null && houseName != null && houseYear != -1 &&
                    numberOfFlatsOnFloor != -1 && numberOfLifts != -1) {
                flat = new Flat(id, name, new Coordinates(x, y), randomDate, area, numberOfRooms, new1, view, transport, new House(houseName, houseYear, numberOfFlatsOnFloor, numberOfLifts));
            }
        }
        return 1;
    }

    public void execute() {
        flats.add(flat);
        info = "Элемент создан";
    }
}
