package ClientApp;

import Commands.*;

import java.io.IOException;
import java.util.ArrayList;

public class ProcessingAnswer {
    private static ArrayList<String> history;
    private static Help help;
    private static Show show;
    private static Clear clear;
    private static Info info;
    private static Add add;
    private static Update update;
    private static Remove_By_Id rmi;
    private static Remove_Greater rg;
    private static Remove_Lower rl;
    private static History his;
    private static Average_Of_Number_Of_Rooms avg;
    private static Filter_By_Number_Of_Rooms filt;
    private static Print_Ascending pr;
    private static Execute_Script es;

    public static ArrayList<String> getHistory() {
        return history;
    }

    public static Object print(Object answer, String userCommand) {

        System.out.println("Ответ сервера: ");

        if (history != null) {
            if (history.size() < 8) {
                String[] parts = userCommand.split(" ");
                history.add(parts[0]);
            } else {
                history.remove(0);
                String[] parts = userCommand.split(" ");
                history.add(parts[0]);
            }
        } else {
            history = new ArrayList<>();
            String[] parts = userCommand.split(" ");
            history.add(parts[0]);
        }

        try {
            help = (Help) answer;
            System.out.println(help.getInfo());
            return null;
        } catch (ClassCastException e) {
        }
        catch (NullPointerException e) {
            System.err.println("Не удалось получить ответ от сервера");
            return null;
        }

        try {
            clear = (Clear) answer;
            System.out.println(clear.getInfo());
            return null;
        } catch (ClassCastException e) {
        }

        try {
            show = (Show) answer;
            System.out.println(show.getInfo());
            return null;
        } catch (ClassCastException e) {
        }

        try {
            info = (Info) answer;
            System.out.println(info.getInfo());
            return null;
        } catch (ClassCastException e) {
        }

        try {
            add = (Add) answer;
            System.out.println(add.getInfo());
            return null;
        } catch (ClassCastException e) {
        }

        try {
            update = (Update) answer;
            if (update.getInfo().equalsIgnoreCase("+")) {
                System.out.println("Заполните следующие поля: ");
                update.fieldsUpdate();
                return update;
            } else {
                System.out.println(update.getInfo());
                return null;
            }
        } catch (ClassCastException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            rmi = (Remove_By_Id) answer;
            System.out.println(rmi.getInfo());
            return null;
        } catch (ClassCastException e) {
        }

        try {
            rg = (Remove_Greater) answer;
            System.out.println(rg.getInfo());
            return null;
        } catch (ClassCastException e) {
        }

        try {
            rl = (Remove_Lower) answer;
            System.out.println(rl.getInfo());
            return null;
        } catch (ClassCastException e) {
        }

        try {
            his = (History) answer;
            System.out.println(his.getInfo());
            return null;
        } catch (ClassCastException e) {
        }

        try {
            avg = (Average_Of_Number_Of_Rooms) answer;
            System.out.println(avg.getInfo());
            return null;
        } catch (ClassCastException e) {
        }

        try {
            filt = (Filter_By_Number_Of_Rooms) answer;
            System.out.println(filt.getInfo());
            return null;
        } catch (ClassCastException e) {
        }

        try {
            pr = (Print_Ascending) answer;
            System.out.println(pr.getInfo());
            return null;
        } catch (ClassCastException e) {
        }

        try {
            es = (Execute_Script) answer;
            System.out.println(es.getInfo());
            return null;
        } catch (ClassCastException e) {
        }

        return null;

    }
}
