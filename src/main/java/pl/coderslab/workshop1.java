package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;


import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class workshop1 {
public static void main(String[] args) {
    try {
        getTab("tasks.csv");
    }catch (IndexOutOfBoundsException e){
        System.out.println("ten plik jest pusty");
    }
    options();

}

public static String[][] getTab(String fileName){
    Path filePath = Paths.get(fileName);
    String[][] fileTab = null;
    try {
        List<String> fileList = Files.readAllLines(filePath);
        fileTab = new String[fileList.size()][fileList.get(0).split(",").length];
        for (int i = 0; i < fileList.toArray().length; i++) {
            for (int j = 0; j < fileList.get(0).split(",").length; j++) {
                fileTab[i][j]=fileList.get(i).split(",")[j];
            }
        }
    }catch(IOException e){
        System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "there is no file like that :(");
        resetC();
    }

    return fileTab;

}
public static void list() {
    for (int i = 0; i < getTab("tasks.csv").length; i++) {
        System.out.print(i+". ");
        for (int j = 0; j < getTab("tasks.csv")[i].length; j++) {
            System.out.print(getTab("tasks.csv")[i][j]);
        }
        System.out.println();
    }
}
public static void remove(){
    try {
        Scanner scan = new Scanner(System.in);
        String[][] fileTab = getTab("tasks.csv");
        System.out.println(ConsoleColors.CYAN+"\nwpisz back by powrócić");
        resetC();
        System.out.println("podaj niżej numer elementu do usunięcia");
        String remove = scan.nextLine();
        if (NumberUtils.isParsable(remove) && Integer.parseInt(remove)>=0) {
            fileTab = ArrayUtils.remove(fileTab, Integer.parseInt(remove));
        } else if (remove.equals("back")) {
            options();
        } else {
            System.out.println(ConsoleColors.RED+"musisz podać liczbę wieksza badz rowna 0");
            resetC();
            remove();
        }
        FileWriter myWriter = new FileWriter("tasks.csv");
        for (int i = 0; i < fileTab.length; i++) {
            for (int j = 0; j < fileTab[i].length; j++) {
                myWriter.write(fileTab[i][j]+",");
            }
            if(fileTab.length-1>i) {
                myWriter.write("\n");
            }
        }
        myWriter.close();
    }catch(IOException e){
    }
}
public static void add() {
    Scanner scan = new Scanner(System.in);
    try {
        FileWriter myWriter = new FileWriter("tasks.csv", true);
        System.out.println("wprowadź poniżej nazwę zadania");
        myWriter.write("\n"+scan.nextLine()+", ");
        System.out.println("wprowadź poniżej termin zadania");
        myWriter.write(scan.nextLine() + ", ");
        System.out.println("wprowad poniżej, czy zadanie jest ważne?(true/false)");
        myWriter.write(scan.nextLine() + ", ");
        myWriter.close();
    }catch (IOException e){
        System.out.println("nie ma takiego pliku");
    }
}

public static void options(){
    Scanner scanCases = new Scanner(System.in);
    System.out.println(ConsoleColors.BLUE + "Wybierz proszę opcję z listy poniżej");
    resetC();
    System.out.println("list");
    System.out.println("add");
    System.out.println("remove");
    System.out.println("exit");
    switch (scanCases.nextLine()) {
        case "exit":
            System.out.println(ConsoleColors.RED + "bye bye");
            resetC();
            System.exit(0);
        case "add":
            add();
            options();
        case "remove":
            remove();
            options();
        case "list":
            list();
            options();
        default:
            System.out.println("wybierz poprawną opcję");
    }
}

    public static void resetC() {
    System.out.print(ConsoleColors.RESET);
    }

}
