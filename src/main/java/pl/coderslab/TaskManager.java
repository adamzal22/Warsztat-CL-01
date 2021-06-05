package pl.coderslab;

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
    static final String[] MENU = {"add", "remove", "list", "exit"};
    static String[][] tasks;

    public static void main(String[] args) {

        tasks = getInfo(PATH);
        menu(MENU);
        Scanner scanner = new Scanner(System.in);
        String text = "";
        while (!text.equals("exit")) {
            text = scanner.nextLine();
            if (text.equals("list")) {
                list();

            }
            }
        }


//MENU//
    public static void menu(String[] tab) {
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        System.out.println(ConsoleColors.RESET);
        for (String choice : tab){
            System.out.println(choice);
        }
    }

    //GET INFO//
    public static String[][] getInfo(String file) {
        Path dir = Paths.get(file);
        if (!Files.exists(dir)) {
            System.out.println("File does not exist");
            System.exit(0);
        }
        String[][] tab2 = null;
        try {

            List<String> strings = Files.readAllLines(dir);
            tab2 = new String[strings.size()][strings.get(0).split(",").length];

            for (int i = 0; i < strings.size(); i++) {

                String[] split = strings.get(i).split(",");

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
    public static void list() {
                    Path path1 = Paths.get("tasks.csv");
                    int j = 0;
                    try {
                        for (String line : Files.readAllLines(path1)) {
                            System.out.println(j + ": " + line);
                            j++;
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


