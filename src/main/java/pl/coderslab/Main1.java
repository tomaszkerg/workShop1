package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main1 {

    public static void main(String[] args) {

        options();

    }

    public static String[][] getFile(String fileN) {
        Path path = Paths.get(fileN);
        try {
            Files.exists(path);
        } catch (Exception ex) {
            System.out.println("taki plik nie istnieje");
        }
            String[][] tasks = null;

            try {
                List<String> lista = Files.readAllLines(path);
                tasks = new String[lista.size()][lista.get(0).split(",").length];

                for (int i = 0; i < lista.size(); i++) {
                    String[] oneLine = lista.get(i).split(",");
                    for (int j = 0; j < tasks[i].length; j++) {
                        tasks[i][j] = oneLine[j];
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tasks;
        }

    public static void options() {
        System.out.println("proszę wpisać opcję");
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
        Scanner scanOption = new Scanner(System.in);
        String scan = scanOption.nextLine();
            switch (scan) {
                case "exit":
                    System.out.println("bye bye");
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
            }
        }
            public static void list(){
                String[][] tasks = getFile("tasks.csv");
                for (int i = 0; i < tasks.length; i++) {
                    System.out.print(i+". ");
                    for (int j = 0; j < tasks[i].length; j++) {
                        System.out.print(tasks[i][j]);
                    }
                    System.out.println("\n");
                }
            }
            public static void remove(){
                String[][] tasks = getFile("tasks.csv");
                String remove = "";
                Scanner scanOption = new Scanner(System.in);
                System.out.println("podaj numer do usuniecia");
                remove = scanOption.nextLine();
                while (Integer.parseInt(remove) <= 0) {
                    System.out.println("niepoprawna liczba, podaj jeszcze raz: ");
                    scanOption.nextLine();
                }
                try {
                    if (Integer.parseInt(remove) < tasks.length) {
                        tasks = ArrayUtils.remove(tasks, Integer.parseInt(remove));
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("Element not exist in tab");
                }
                try {
                    PrintWriter zapis = new PrintWriter("tasks.csv");
                    for (int i = 0; i < tasks.length; i++) {
                        if(tasks.length>i-1) {
                            for (int j = 0; j < tasks[i].length; j++) {
                                if (j < 2) {
                                    zapis.print(tasks[i][j] + ",");
                                } else {
                                    zapis.print(tasks[i][j]);
                                }
                            }if(i < tasks.length-1) {
                                zapis.println();
                            }
                        }
                    }
                    zapis.close();
                } catch (FileNotFoundException e) {
                }
            }
            public static void add(){
                String add = "";
                Scanner scanOption = new Scanner(System.in);
                System.out.println("Podaj opis zadania");
                add = add + scanOption.nextLine();
                System.out.println("podaj termin ukonczenia");
                add = add + ", " + scanOption.nextLine();
                System.out.println("czy zadanie jest ważne: true/false");
                add = add + ", " + scanOption.nextLine();
                try {
                    FileWriter fw = new FileWriter("tasks.csv", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("\n" + add);
                    bw.close();
                } catch (IOException e) {

                }
            }
        }



