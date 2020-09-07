package Classes;

public class Coordinates {
    private Integer x; //Значение поля должно быть больше -474, Поле не может быть null
    private Integer y; //Поле не может быть null

    public Coordinates() {}

    public Coordinates (Integer x, Integer y) {
        if (x<=-474) throw new IllegalArgumentException("Поле \"x\" должно быть больше -474");
        else if (x==null) throw new IllegalArgumentException("Поле \"x\" не должно содержать null");
        else this.x = x;

        if (y==null) throw new IllegalArgumentException("Поле \"y\" не должно содержать null");
        else this.y = y;
    }

    public Integer getX () {
        return x;
    }
    public Integer getY () {
        return y;
    }

    public void setX (Integer x) {
        this.x = x;
    }
    public void setY (Integer y) {
        this.y = y;
    }
    public void setXY (Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "x = " + x +
                " y = "+y;
    }
}