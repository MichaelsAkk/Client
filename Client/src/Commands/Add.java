package Commands;

import Classes.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.HashSet;

public class Add implements Serializable {

    private transient HashSet<Flat> flats;
    private transient Flat flat;
    private Integer id;
    private String info;
    private String name;
    private Integer x;
    private Integer y;
    private Float area;
    private int numberOfRooms;
    private boolean new1;
    private View view;
    private Transport transport;
    private String houseName;
    private Integer year;
    private long numberOfFlatsOnFloor;
    private long numberOfLifts;

    public Add() { this.info = null; }
    public Add (HashSet<Flat> flats) {
        this.flats = flats;
        this.info = null;
    }

    public void setFlats(HashSet<Flat> flats) {
        this.flats = flats;
    }

    public String getInfo() {
        return this.info;
    }

    public void execute() {
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

        LocalDate randomDate = null;
        if (year == null) {
            GregorianCalendar gc = new GregorianCalendar();
            int randomYear = 1900 + (int) Math.round(Math.random() * (2020 - 1900));
            gc.set(gc.YEAR, randomYear);
            int randomDay = 1 + (int) Math.round(Math.random() * (gc.getActualMaximum(gc.DAY_OF_YEAR) - 1900));
            gc.set(gc.DAY_OF_YEAR, randomDay);
            randomDate = LocalDate.of(gc.get(gc.YEAR), gc.get(gc.MONTH) + 1, gc.get(gc.DAY_OF_MONTH));
        } else {
            GregorianCalendar gc = new GregorianCalendar();
            int randomYear = year + (int) Math.round(Math.random() * (2020 - year));
            gc.set(gc.YEAR, randomYear);
            int randomDay = 1 + (int) Math.round(Math.random() * (gc.getActualMaximum(gc.DAY_OF_YEAR) - 1));
            gc.set(gc.DAY_OF_YEAR, randomDay);
            randomDate = LocalDate.of(gc.get(gc.YEAR), gc.get(gc.MONTH) + 1, gc.get(gc.DAY_OF_MONTH));
        }

        flat = new Flat(id, name, new Coordinates(x, y), randomDate, area,
                numberOfRooms, new1, view, transport,
                new House(houseName, year, numberOfFlatsOnFloor, numberOfLifts));
        flats.add(flat);
        info = "Элемент создан";
        flats = null;
        flat = null;
    }

    public int fields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String result; Integer id = null; String name = null; Integer x = null; Integer y = null;
        Integer houseYear = -1; String houseName = null; View view = null; Transport transport = null; Float area = -1.0f;
        int numberOfRooms = -1; boolean new1 = false; long numberOfFlatsOnFloor = -1; long numberOfLifts = -1;

        int control = 0;
        while (control==0) {
            System.out.print("Name: ");
            result = reader.readLine();
            if (result.equalsIgnoreCase("null")) {
                System.out.println("Ошибка ввода: поле \"name\" содержит null");
            } else if (result.equalsIgnoreCase("")) {
                System.out.println("Ошибка ввода: поле \"name\" не может быть пустым");
            } else { name = result; control++; }
        }

        System.out.println ("Coordinates:");

        control = 0;
        while (control==0) {
            System.out.print("X: ");
            try {
                result = reader.readLine();
                if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) {
                    System.out.println("Ошибка ввода: поле \"x\" содержит null");
                } else {
                    x = Integer.parseInt(result);
                    if (x <= -474) {
                        System.out.println("Ошибка ввода: поле \"x\" должно быть больше -474");
                    } else control++;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода: поле \"x\" не является целым числом");
            } catch (IOException e) {
                System.out.println("Ошибка ввода");
            }
        }

        control = 0;
        while (control==0) {
            System.out.print("Y: ");
            try {
                result = reader.readLine();
                if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) {
                    System.out.println("Ошибка ввода: поле \"y\" содержит null");
                } else {
                    y = Integer.parseInt(result); control++;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода: поле \"y\" не является целым числом");
            } catch (IOException e) {
                System.out.println("Ошибка ввода");
            }
        }

        control = 0;
        while (control==0) {
            System.out.print("Area: ");
            try {
                result = reader.readLine();
                if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) {
                    System.out.println("Ошибка ввода: поле \"area\" содержит null");
                } else {
                    area = Float.parseFloat(result);
                    if (area <= 0) {
                        System.out.println("Ошибка ввода: поле \"area\" должно быть больше 0");
                    }
                    else control++;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода: поле \"area\" должно быть числом (дробная часть указывается после символа \".\")");
            } catch (IOException e) {
                System.out.println("Ошибка ввода");
            }
        }

        control = 0;
        while (control==0) {
            System.out.print("numberOfRooms: ");
            try {
                result = reader.readLine();
                numberOfRooms = Integer.parseInt(result);
                if (numberOfRooms <= 0) {
                    System.out.println("Ошибка ввода: поле \"numberOfRooms\" должно быть целым положительным числом");
                } else
                    control++;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода: поле \"numberOfRooms\" не является целым числом");
            } catch (IOException e) {
                System.out.println("Ошибка ввода");
            }
        }

        control = 0;
        while (control==0) {
            System.out.print("New? (true/false): ");
            try {
                result = reader.readLine();
                if (!result.equalsIgnoreCase("true") && !result.equalsIgnoreCase("false")) {
                    System.out.println("Ошибка ввода: поле \"new\" должно быть либо \"true\", либо \"false\" ");
                } else {
                    if (result.equalsIgnoreCase("true")) new1 = true;
                    else new1 = false;
                    control++;
                }
            } catch (IOException e) {
                System.out.println("Ошибка ввода");
            }
        }

        control = 0;
        while (control==0) {
            System.out.print("View (YARD, BAD, GOOD, TERRIBLE): ");
            try {
                result = reader.readLine();
                if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) {
                    System.out.println("Ошибка ввода: поле \"view\" содержит null");
                } else {
                    view = View.valueOf(result.toUpperCase());
                    control++;
                }
            } catch (IOException e) {
                System.out.println("Ошибка ввода");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка ввода: поле \"view\" не содержит значение из указанного списка");
            }
        }

        control = 0;
        while (control==0) {
            System.out.print("Transport (FEW, LITTLE, NORMAL, ENOUGH): ");
            try {
                result = reader.readLine();
                if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) { transport = null; control++; }
                else {
                    transport = Transport.valueOf(result.toUpperCase());
                    control++;
                }
            } catch (IOException e) {
                System.out.println("Ошибка ввода");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка ввода: поле \"transport\" не содержит значение из указанного списка");
            }
        }

        System.out.println ("House: ");

        control = 0;
        while (control==0) {
            System.out.print("Name: ");
            result = reader.readLine();
            if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) {
                System.out.println("Ошибка ввода: поле \"House.name\" содержит null");
            } else { houseName = result; control++; }
        }

        control = 0;
        while (control==0) {
            System.out.print("Year: ");
            try {
                result = reader.readLine();
                if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) { houseYear = null; control++; }
                else {
                    houseYear = Integer.parseInt(result);
                    if (houseYear <= 0) {
                        System.out.println("Ошибка ввода: поле \"House.year\" не является целым положительным числом");
                    }
                    else control++;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода: поле \"House.year\" не является целым числом");
            } catch (IOException e) {
                System.out.println("Ошибка ввода");
            }
        }

        control = 0;
        while (control==0) {
            System.out.print("Number of flats on floor: ");
            try {
                result = reader.readLine();
                numberOfFlatsOnFloor = Long.parseLong(result);
                if (numberOfFlatsOnFloor <= 0) {
                    System.out.println("Ошибка ввода: поле \"House.NumberOfFlatsOnFloor\" не является целым положительным числом");
                } else control++;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода: поле \"House.NumberOfFlatsOnFloor\" не является целым числом");
            } catch (IOException e) {
                System.out.println("Ошибка ввода");
            }
        }

        control = 0;
        while (control==0) {
            System.out.print("Number of lifts: ");
            try {
                result = reader.readLine();
                numberOfLifts = Long.parseLong(result);
                if (numberOfLifts <= 0) {
                    System.out.println("Ошибка ввода: поле \"House.NumberOfLifts\" не является целым положительным числом");
                }
                else control++;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода: поле \"House.NumberOfLifts\" не является целым числом");
            } catch (IOException e) {
                System.out.println("Ошибка ввода");
            }
        }

        if (name != null && x != null && y != null && area != -1.0f &&
                numberOfRooms != -1 && view != null && houseName != null &&
                numberOfFlatsOnFloor != -1 && numberOfLifts != -1) {
            this.name = name; this.x = x; this.y = y; this.area = area;
            this.numberOfRooms = numberOfRooms; this.new1 = new1; this.view = view;
            this.transport = transport; this.houseName = houseName;
            this.year = houseYear; this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
            this.numberOfLifts = numberOfLifts;
        }

        return 1;
    }

    public String toStrings() {
        return "add (name: "+name+"; coordinates: (x: "+x+"; y:"+y+"); area: "+area+"; " +
                "number of rooms: "+numberOfRooms+"; new: "+new1+"; view: "+view+"; " +
                "transport: "+transport+"; house: (name: "+houseName+"; year: "+year+"; " +
                "number of flats on floor: "+numberOfFlatsOnFloor+"; " +
                "number of lifts: "+numberOfLifts+"))";

    }
}
