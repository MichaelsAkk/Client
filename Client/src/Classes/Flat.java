package Classes;

import java.time.LocalDate;

public class Flat implements Comparable<Flat> {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float area; //Значение поля должно быть больше 0
    private int numberOfRooms; //Значение поля должно быть больше 0
    private boolean new1;
    private View view; //Поле не может быть null
    private Transport transport; //Поле может быть null
    private House house; //Поле не может быть null

    public Flat() {}

    public Flat(Integer id, String name, Coordinates coordinates, LocalDate creationDate, Float area, int numberOfRooms,
                boolean new1, View view, Transport transport, House house) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.new1 = new1;
        this.view = view;
        this.transport = transport;
        this.house = house;
    }

    public Integer getId () {
        return id;
    }
    public String getName () {
        return name;
    }
    public Coordinates getCoordinates () {
        return coordinates;
    }
    public LocalDate getCreationDate () {
        return creationDate;
    }
    public Float getArea () {
        return area;
    }
    public int getNumberOfRooms () { return numberOfRooms; }
    public boolean getNew () {
        return new1;
    }
    public View getView () {
        return view;
    }
    public Transport getTransport () {
        return transport;
    }
    public House getHouse () {
        return house;
    }

    public void setId (Integer id) {
        this.id = id;
    }
    public void setName (String name) {
        this.name = name;
    }
    public void setCoordinates (Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    public void setArea (Float area) {
        this.area = area;
    }
    public void setNumberOfRooms (int numberOfRooms) { this.numberOfRooms = numberOfRooms; }
    public void setNew (boolean new1) {
        this.new1 = new1;
    }
    public void setView (View view) { this.view = view; }
    public void setTransport (Transport transport) {
        this.transport = transport;
    }
    public void setHouse (House house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "Flat (" +
                "id = " + id + ';' +
                " name = " + name + ';' +
                " coordinates = (" + coordinates.toString() + ");" +
                " creationDate = (" + creationDate.toString() + ");" +
                " area = " + area + ';' +
                " number of rooms = " + numberOfRooms + ';' +
                " new = " + new1 + ';' +
                " view = " + view + ';' +
                " transport = " + transport + ';' +
                " house = (" + house.toString()  + ")\n";
    }

    @Override
    public int compareTo(Flat f) {
        return this.numberOfRooms - f.getNumberOfRooms();
    }
}