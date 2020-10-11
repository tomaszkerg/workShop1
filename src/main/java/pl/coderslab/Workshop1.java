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

public class Workshop1 {
    static String file = "tasks.csv";

public static void main(String[] args) {
    try {
        createTab(file);
    }catch (IndexOutOfBoundsException e){
        System.out.println("ten plik jest pusty: " + file);
    }
    options();

}



public static String[][] createTab(String fileName){
    Path filePath = Paths.get(fileName);
    String[][] fileTab = null;
    try {
        List<String> fileList = Files.readAllLines(filePath);
        int rowsLength = fileList.size();
        int columnsLength = fileList.get(0).split(",").length;

        fileTab = new String[rowsLength][columnsLength];
        for (int i = 0; i < fileList.size(); i++) {
            for (int j = 0; j < columnsLength; j++) {
                fileTab[i][j]=fileList.get(i).split(",")[j];
            }
        }
    }catch(IOException e){
        System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "there is no file " + file);
        resetColor();
    }

    return fileTab;

}
public static void showTab() {
    for (int i = 0; i < createTab("tasks.csv").length; i++) {
        System.out.print(i+". ");
        for (int j = 0; j < createTab("tasks.csv")[i].length; j++) {
            System.out.print(createTab("tasks.csv")[i][j]);
        }
        System.out.println();
    }
}
public static void remove(){
    try {
        Scanner scan = new Scanner(System.in);
        String[][] fileTab = createTab("tasks.csv");
        System.out.println(ConsoleColors.CYAN+"\nwpisz back by powrócić");
        resetColor();
        System.out.println("podaj niżej numer elementu do usunięcia");
        String remove = scan.nextLine();
        if (NumberUtils.isParsable(remove) && Integer.parseInt(remove)>=0) {
            fileTab = ArrayUtils.remove(fileTab, Integer.parseInt(remove));
        } else if (remove.equals("back")) {
            options();
        } else {
            System.out.println(ConsoleColors.RED+"musisz podać liczbę wieksza badz rowna 0");
            resetColor();
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
    resetColor();
    System.out.println("list");
    System.out.println("add");
    System.out.println("remove");
    System.out.println("exit");
    switch (scanCases.nextLine()) {
        case "exit":
            System.out.println(ConsoleColors.RED + "bye bye");
            resetColor();
            System.exit(0);
        case "add":
            add();
            options();
        case "remove":
            remove();
            options();
        case "list":
            showTab();
            options();
        default:
            System.out.println("wybierz poprawną opcję");
    }
}

    public static void resetColor() {
        System.out.print(ConsoleColors.RESET);
    }

}
