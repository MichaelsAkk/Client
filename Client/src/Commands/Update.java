package Commands;

import Classes.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.HashSet;

public class Update implements Serializable {
    private transient HashSet<Flat> flats;
    private transient Flat flat;
    private Integer elementId;
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
    private int creationYear;

    public Update () {
        this.info = null;
    }

    public Update (HashSet<Flat> flats, Integer elementId) {
        this.flats = flats;
        this.elementId = elementId;
        this.info = null;
    }

    public Update(Integer id) {
        this.info = null;
        this.elementId = id;
    }

    public void setFlats(HashSet<Flat> flats) {
        this.flats = flats;
    }

    public String getInfo() {
        return this.info;
    }

    public String getName() {
        return this.name;
    }

    public void execute() {
        if (flats != null) {
            for (Flat f : flats) {
                if (f.getId().toString().equalsIgnoreCase(elementId.toString())) {
                    flat = f;
                    break;
                }
            }
        }
        if (flat==null) info = "Элемента с данным id не существует в коллекции";
        else {
            flat.setName(name);
            flat.setCoordinates(new Coordinates(x, y));
            flat.setArea(area);
            flat.setNumberOfRooms(numberOfRooms);
            flat.setNew(new1);
            flat.setView(view);
            flat.setTransport(transport);
            flat.setHouse(new House(houseName, year, numberOfFlatsOnFloor, numberOfLifts));
            flat = null;
            flats = null;
            info = "Элемент успешно обновлен";
        }
    }

    public void check() {
        int control = 0;
        if (flats != null) {
            for (Flat f : flats) {
                if (f.getId().toString().equalsIgnoreCase(elementId.toString())) {
                    control = 1;
                    flat = f;
                    break;
                }
            }
        }
        if (control == 0) {
            info = "Элемента с данным id не существует в коллекции";
        } else {
            name = flat.getName(); x = flat.getCoordinates().getX();
            y = flat.getCoordinates().getY(); area = flat.getArea();
            numberOfRooms = flat.getNumberOfRooms(); new1 = flat.getNew();
            view = flat.getView(); transport = flat.getTransport();
            houseName = flat.getHouse().getName(); year = flat.getHouse().getYear();
            numberOfFlatsOnFloor = flat.getHouse().getNumberOfFlatsOnFloor();
            numberOfLifts = flat.getHouse().getNumberOfLifts();
            creationYear = flat.getCreationDate().getYear();
            flat = null; flats = null;
            info = "+";
        }
    }

    public int fieldsUpdate() throws IOException {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String result;
            String name = null;
            Integer x = null;
            Integer y = null;
            Integer houseYear = -1;
            String houseName = null;
            View view = null;
            Transport transport = null;
            Float area = -1.0f;
            int numberOfRooms = -1;
            boolean new1 = false;
            long numberOfFlatsOnFloor = -1;
            long numberOfLifts = -1;
            System.out.println("Если для данного поля не требуется обновление, введите \"-\"");

            int control = 0;
            while (control==0) {
                System.out.print("Name (" + this.name + "): ");
                result = reader.readLine();
                if (result.equalsIgnoreCase("null")) {
                    System.out.println("Ошибка ввода: поле \"name\" содержит null");
                } else if (result.equalsIgnoreCase("")) {
                    System.out.println("Ошибка ввода: поле \"name\" не может быть пустым");
                } else if (result.equalsIgnoreCase("-")) {
                    name = this.name; control++;
                } else { name = result; control++; }
            }

            System.out.println("Coordinates:");

            control = 0;
            while (control==0) {
                System.out.print("X (" + this.x + "): ");
                try {
                    result = reader.readLine();
                    if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) {
                        System.out.println("Ошибка ввода: поле \"x\" содержит null");
                    } else if (result.equalsIgnoreCase("-")) {
                        x = this.x; control++;
                    } else {
                        x = Integer.parseInt(result);
                        if (x <= -474) {
                            System.out.println("Ошибка ввода: поле \"x\" должно быть больше -474");
                        }
                        else control++;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка ввода: поле \"x\" не является целым числом");
                } catch (IOException e) {
                    System.out.println("Ошибка ввода");
                }
            }

            control = 0;
            while (control==0) {
                System.out.print("Y (" + this.y + "): ");
                try {
                    result = reader.readLine();
                    if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) {
                        System.out.println("Ошибка ввода: поле \"y\" содержит null");
                    } else if (result.equalsIgnoreCase("-")) {
                        y = this.y; control++;
                    } else {
                        y = Integer.parseInt(result);
                        control++;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка ввода: поле \"y\" не является целым числом");
                } catch (IOException e) {
                    System.out.println("Ошибка ввода");
                }
            }

            control = 0;
            while (control==0) {
                System.out.print("Area (" + this.area + "): ");
                try {
                    result = reader.readLine();
                    if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) {
                        System.out.println("Ошибка ввода: поле \"area\" содержит null");
                    } else if (result.equalsIgnoreCase("-")) {
                        area = this.area;
                        control++;
                    }
                    else {
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
                System.out.print("numberOfRooms (" + this.numberOfRooms + "): ");
                try {
                    result = reader.readLine();
                    if (result.equalsIgnoreCase("-")) { numberOfRooms = this.numberOfRooms; control++; }
                    else {
                        numberOfRooms = Integer.parseInt(result);
                        if (numberOfRooms <= 0) {
                            System.out.println("Ошибка ввода: поле \"numberOfRooms\" должно быть целым положительным числом");
                        }
                        else control++;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка ввода: поле \"numberOfRooms\" не является целым числом");
                } catch (IOException e) {
                    System.out.println("Ошибка ввода");
                }
            }

            control = 0;
            while (control==0) {
                System.out.print("New? (" + this.new1 + "): ");
                try {
                    result = reader.readLine();
                    if (result.equalsIgnoreCase("-")) { new1 = this.new1; control++; }
                    else if (!result.equalsIgnoreCase("true") && !result.equalsIgnoreCase("false")) {
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
                System.out.print("View (YARD, BAD, GOOD, TERRIBLE) (" + this.view + "): ");
                try {
                    result = reader.readLine();
                    if (result.equalsIgnoreCase("-")) {
                        view = this.view;
                        control++;
                    } else if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) {
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
                System.out.print("Transport (FEW, LITTLE, NORMAL, ENOUGH) (" + this.transport + "): ");
                try {
                    result = reader.readLine();
                    if (result.equalsIgnoreCase("-")) { transport = this.transport; control++; }
                    else if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) { transport = null; control++; }
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

            System.out.println("House: ");

            control = 0;
            while (control==0) {
                System.out.print("Name (" + this.houseName + "): ");
                result = reader.readLine();
                if (result.equalsIgnoreCase("-")) { houseName = this.houseName; control++; }
                else if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) {
                    System.out.println("Ошибка ввода: поле \"House.name\" содержит null");
                } else { houseName = result; control++; }
            }

            control = 0;
            while (control==0) {
                System.out.print("Year (не более " + this.creationYear + ", т.к. это сгенерированный год для квартиры): ");
                try {
                    result = reader.readLine();
                    if (result.equalsIgnoreCase("-")) { houseYear = this.year; control++; }
                    else if (result.equalsIgnoreCase("null") || result.equalsIgnoreCase("")) { houseYear = null; control++; }
                    else {
                        houseYear = Integer.parseInt(result);
                        if (houseYear <= 0) {
                            System.out.println("Ошибка ввода: поле \"House.year\" не является целым положительным числом");
                        }
                        if (houseYear > this.creationYear) {
                            System.out.println("Ошибка ввода: поле \"House.year\" должно быть не более " + this.creationYear);
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
                System.out.print("Number of flats on floor (" + this.numberOfFlatsOnFloor + "): ");
                try {
                    result = reader.readLine();
                    if (result.equalsIgnoreCase("-")) {
                        numberOfFlatsOnFloor = this.numberOfFlatsOnFloor;
                        control++;
                    }
                    else {
                        numberOfFlatsOnFloor = Long.parseLong(result);
                        if (numberOfFlatsOnFloor <= 0) {
                            System.out.println("Ошибка ввода: поле \"House.NumberOfFlatsOnFloor\" не является целым положительным числом");
                        }
                        else control++;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка ввода: поле \"House.NumberOfFlatsOnFloor\" не является целым числом");
                } catch (IOException e) {
                    System.out.println("Ошибка ввода");
                }
            }

            control = 0;
            while (control==0) {
                System.out.print("Number of lifts (" + this.numberOfLifts + "): ");
                try {
                    result = reader.readLine();
                    if (result.equalsIgnoreCase("-")) {
                        numberOfLifts = this.numberOfLifts;
                        control++;
                    }
                    else {
                        numberOfLifts = Long.parseLong(result);
                        if (numberOfLifts <= 0) {
                            System.out.println("Ошибка ввода: поле \"House.NumberOfLifts\" не является целым положительным числом");
                        }
                        else control++;
                    }
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
                this.transport = transport;
                this.houseName = houseName; this.year = houseYear;
                this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
                this.numberOfLifts = numberOfLifts;
            }
            return 1;
    }

    public String toStrings() {
        return "update " + elementId + " (name: "+name+"; coordinates: (x: "+x+"; y:"+y+"); area: "+area+"; " +
                "number of rooms: "+numberOfRooms+"; new: "+new1+"; view: "+view+"; " +
                "transport: "+transport+"; house: (name: "+houseName+"; year: "+year+"; " +
                "number of flats on floor: "+numberOfFlatsOnFloor+"; " +
                "number of lifts: "+numberOfLifts+"))";

    }

    public String toStringsNoArguments() {
        return "update " + elementId;

    }
}
