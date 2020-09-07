package Instruments;

import Classes.Flat;

import java.io.*;
import java.util.HashSet;

public class Reader {
    public static HashSet<Flat> readFile(File file) throws FileNotFoundException{ // Читает файл в формате json и возвращает заполненную коллекцию
        return Processing.strToSet(Processing.fileToString(file));
    }

}
