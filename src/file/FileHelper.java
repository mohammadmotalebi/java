package file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.sql.Timestamp;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;

public class FileHelper {
    public static void updater(Object obj , String path){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(gson.toJson(obj));
            fileWriter.flush();
            fileWriter.close();
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }

    public static String getDate() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.toString();
    }

    public static void logHeader(File log , String username , String password) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(log , true));
            out.write("USER:" + username+"\n");
            out.write("CREATED_AT:" + getDate()+"\n");
            out.write("PASSWORD:" + password+"\n");
            out.flush();
            out.close();
        }
        catch (IOException e) {}
    }

    public static void logDelete(File log) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(log , true));
            Scanner myReader = new Scanner(log);
            int counter = 1;
            ArrayList<String> myfile = new ArrayList<String>();
            while(myReader.hasNext()) {
                if(counter == 4) {
                    myfile.add("DELETED_AT:" + getDate()+"\n");
                    counter++;
                    continue;
                }
                myfile.add(myReader.nextLine());
                counter++;
            }
            out.close();
            FileWriter myWriter = new FileWriter(log);
            for(String line : myfile) {
                myWriter.write(line+"\n");
                myWriter.flush();
            }
        }
        catch (IOException e) {}
    }

    public static void logMoveTo(File log , String massage) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(log , true));
            out.write("moved_to " + getDate() + " " + massage+"\n");
            out.flush();
            out.close();
        }
        catch (IOException e) {}
    }

    public static void logSingIn(File log) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(log , true));
            out.write("sign_in " + getDate()+"\n");
            out.flush();
            out.close();
        }
        catch (IOException e) {}
    }

    public static void logSelect(File log , String massage) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(log , true));
            out.write("select " + getDate() + " " + massage+"\n");
            out.flush();
            out.close();
        }
        catch (IOException e) {}
    }

    public static void logAdd(File log , String massage) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(log , true));
            out.write("add " + getDate() + " " + massage+"\n");
            out.flush();
            out.close();
        }
        catch (IOException e) {}
    }

    public static void logRemove(File log , String massage) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(log , true));
            out.write("remove " + getDate() + " " + massage+"\n");
            out.flush();
            out.close();
        }
        catch (IOException e) {}
    }

    public static void logBuy(File log , String massage) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(log , true));
            out.write("buy " + getDate() + " " + massage+"\n");
            out.flush();
            out.close();
        }
        catch (IOException e) {}
    }

    public static void logSell(File log , String massage) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(log , true));
            out.write("sell " + getDate() + " " + massage+"\n");
            out.flush();
            out.close();
        }
        catch (IOException e) {}
    }

    public static void logError(File log , String massage) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(log , true));
            out.write("error " + getDate() + " " + massage+"\n");
            out.flush();
            out.close();
        }
        catch (IOException e) {}
    }

    public static void logCommand(File log , String massage) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(log , true));
            out.write("error " + getDate() + " " + massage+"\n");
            out.flush();
            out.close();
        }
        catch (IOException e) {}
    }

    public static void logSignOut(File log) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(log , true));
            out.write("sign_out " + getDate()+"\n");
            out.flush();
            out.close();
        }
        catch (IOException e) {}
    }

}