package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    static final String PATH = "tasks.csv";
    static String[][] tasks;

    public static void main(String[] args) {

        tasks = getInfo(PATH);
        System.out.println(ConsoleColors.RED+"Good day!");
        System.out.println("Welcome to the Task Manager."+ConsoleColors.RESET);
        menu();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String text = scanner.nextLine();
            switch (text) {
                case "exit":
                    saveAndExit(PATH, tasks);
                    System.out.println(ConsoleColors.RED + "Good bye!");
                    System.exit(0); //wyjście z programu kod "0"//
                    break;
                case "add":
                    add();
                    break;
                case "remove":
                    remove(tasks);
                    break;
                case "list":
                    System.out.println(ConsoleColors.PURPLE+"Tasks in progress: "+ConsoleColors.RESET);
                    list(tasks);
                    break;
                default:
                    System.out.println("Please select a correct option.");
            }
            menu();
        }
    }

    //MENU//
    public static void menu() {
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        System.out.println(ConsoleColors.YELLOW+"add");
        System.out.println(ConsoleColors.YELLOW+"remove");
        System.out.println(ConsoleColors.YELLOW+"list");
        System.out.println(ConsoleColors.YELLOW+"exit" + ConsoleColors.RESET);

    }

    //GET INFO//
    public static String[][] getInfo(String file) {
        Path path = Paths.get(file);
        String[][] tab2 = null;
        try {
            List<String> words = Files.readAllLines(path);
            tab2 = new String[words.size()][words.get(0).split(",").length];

            for (int i = 0; i < words.size(); i++) {
                String[] split = words.get(i).split(",");
                for (int j = 0; j < split.length; j++) {
                    tab2[i][j] = split[j];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tab2;
    }

    //LIST//
    public static void list(String[][] tab3) {
        for (int i = 0; i < tab3.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < tab3[i].length; j++) {
                System.out.print(tab3[i][j] + " ");
            }
            System.out.println();
        }

    }

    //ADD//
    public static void add(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(ConsoleColors.YELLOW + "Please add task description");
        String desc = scanner.nextLine();
        System.out.println(ConsoleColors.YELLOW + "Please add task due date");
        String date = scanner.nextLine();
        System.out.println(ConsoleColors.YELLOW + "Is your task important: true/false" +ConsoleColors.RESET);
        String urgent = scanner.nextLine();
            //dodanie tabeli dynamicznie//
        tasks =  Arrays.copyOf(tasks, tasks.length + 1);
            //uzupełnienie nowej wartości tabeli wpisanymi danymi//
        tasks[tasks.length-1] = new String[3];
        tasks[tasks.length-1][0] = desc;
        tasks[tasks.length-1][1] = date;
        tasks[tasks.length-1][2] = urgent;
        System.out.println("Task has been added successfully");



    }
    //REMOVE//
    public static void remove(String[][] tab) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please tell, which number to remove.");
        int num = scanner.nextInt();
        //Sprawdzenie czy poprawny zakres i usunięcie rekordu//
        try {
            if (num < tab.length && num >= 0) {
                tasks = ArrayUtils.remove(tab, num);
                System.out.println("Selected task has been removed successfully ");
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("There is no such number on the list");

        }

    }
    //SAVE & EXIT//
    public static void saveAndExit(String file, String[][] tab) {
        Path path = Paths.get(file);
        //zapisanie zmian do stringa
        String[] lines = new String[tab.length];
        for (int i = 0; i < tab.length; i++) {
            lines[i] = String.join(",", tab[i]);
        }
        //zapis do pliku
        try {
            Files.write(path, Arrays.asList(lines));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}


