package pl.coderslab;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Workshop2 {
    static final String fileName = "tasks.csv";
    static List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        try {
            getDataFromFile(fileName);
            printMenu();
            mainController();
        } catch (FileNotFoundException e) {
            System.err.println("ten plik jest pusty: " + fileName);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }

    private static void getDataFromFile(String fileName) throws FileNotFoundException {
        FileReader file = new FileReader(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] splitLine = scanner.nextLine().split(",");
            taskList.add(new Task(splitLine[0], splitLine[1], Boolean.valueOf(splitLine[2])));
        }
    }

    private static void printMenu() {
        System.out.println("Opcje programu:");
        System.out.println("1 - Wyświetlenie taskow");
        System.out.println("2 - dodaj nowy task");
        System.out.println("3 - usun task");
        System.out.println("4 - koniec");
    }

    private static void mainController() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz opcje: ");
        try {
            int userInput = scanner.nextInt();
            while (userInput != 4) {
                switch (userInput) {
                    case 1:
                        show();
                        break;
                    case 2:
                        add();
                        break;
                    case 3:
                        delete();
                        break;
                    default:
                        System.out.println("Podales bledną opcję. Podaj jeszcze raz");
                }
                printMenu();
                System.out.println("Wybierz opcje: ");
                userInput = scanner.nextInt();
            }
        } catch (InputMismatchException e) {
            System.out.println("Niewlasciwy typ danych");
        }
        closeApp();
    }

    private static void closeApp() throws IOException {
        System.out.println("Zamykanie applikacji");
        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (Task task : taskList) {
            bufferedWriter.write(task.getTaskName() + "," + task.getDate() + "," + task.isImportant);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    private static void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ktory task chcesz usunac");
        int userInput = scanner.nextInt() - 1;
        taskList.remove(userInput);
        System.out.println("Usunieto task");
    }

    private static void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwe taska: ");
        String taskName = scanner.nextLine();
        System.out.println("Podaj date taska: ");
        String date = scanner.nextLine();
        System.out.println("Podaj czy wazny task: ");
        String important = scanner.nextLine();
        Boolean isImportant = Boolean.valueOf(important);
        taskList.add(new Task(taskName, date, isImportant));
        System.out.println("Dodano nowy task");
    }

    private static void show() {
        int i = 1;
        for (Task task : taskList) {
            System.out.println(i + "." + task.getTaskName() + ", " + task.getDate() + ", " + task.getImportant());
            i++;
        }
    }
}
