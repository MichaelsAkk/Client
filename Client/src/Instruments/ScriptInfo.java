package Instruments;

public class ScriptInfo {
    private static String info;

    public static String getInfo() {
        return info;
    }

    public static void setInfo(String infoFrom) {
        info += infoFrom + "\n";
    }

    public static void setStartInfo (String s) {
        info = s;
    }
}
