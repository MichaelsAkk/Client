package Classes;

public class House {
    private String name; //Поле не может быть null
    private Integer year; //Поле может быть null, Значение поля должно быть больше 0
    private long numberOfFlatsOnFloor; //Значение поля должно быть больше 0
    private long numberOfLifts; //Значение поля должно быть больше 0

    public House(){}

    public House(String name, Integer year, long numberOfFlatsOnFloor, long numberOfLifts) {
        if (name==null) throw new IllegalArgumentException("Поле \"House.name\" не должно содержать null");
        else this.name = name;

        if (year != null) {
            if (year <= 0) throw new IllegalArgumentException("Поле \"House.year\" должно быть положительным числом");
            else this.year = year;
        }

        if (numberOfFlatsOnFloor<=0) throw new IllegalArgumentException("Поле \"House.numberOfFlatsOnFloor\" должно быть положительным числом");
        else this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;

        if (numberOfLifts<=0) throw new IllegalArgumentException("Поле \"House.numberOfLifts\" должно быть положительным числом");
        else this.numberOfLifts = numberOfLifts;
    }

    public String getName () {
        return name;
    }
    public Integer getYear () {
        return year;
    }
    public long getNumberOfFlatsOnFloor () {
        return numberOfFlatsOnFloor;
    }
    public long getNumberOfLifts () {
        return numberOfLifts;
    }

    public void setName (String name) {
        this.name = name;
    }
    public void setYear (Integer year) {
        this.year = year;
    }
    public void setNumberOfFlatsOnFloor (long numberOfFlatsOnFloor) { this.numberOfFlatsOnFloor = numberOfFlatsOnFloor; }
    public void setNumberOfLifts (long numberOfLifts) {
        this.numberOfLifts = numberOfLifts;
    }

    @Override
    public String toString() {
        return "name = "+name+"; year = "+year+"; "+numberOfFlatsOnFloor+" flats on floor, "+numberOfLifts+" lifts";
    }
}