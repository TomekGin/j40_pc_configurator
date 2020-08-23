package com.sda.javagda40.pcconfigurator;

import com.sda.javagda40.pcconfigurator.database.HibernateUtil;
import com.sda.javagda40.pcconfigurator.database.PartsDao;
import com.sda.javagda40.pcconfigurator.database.EntityDao;
import com.sda.javagda40.pcconfigurator.handlers.CpuHandler;
import com.sda.javagda40.pcconfigurator.handlers.MoboHandler;
import com.sda.javagda40.pcconfigurator.model.Parts;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HibernateUtil.getOurSessionFactory();
        System.out.println("Initial version.");
        Scanner scanner = new Scanner(System.in);

        MoboHandler moboHandler = new MoboHandler();
        CpuHandler cpuHandler = new CpuHandler();
        String command;

        do {
            System.out.println("Wprowadz komende: ");
            printAllOptions();
            command = scanner.nextLine();
            // serwis aukcyjny allegro
            // words = { "serwis", "aukcyjny", "allegro"}
            String[] words = command.split(" ");

            // parts list
            if (words[0].equalsIgnoreCase("mobo")) {
                moboHandler.handle(words);
            } else if (words[0].equalsIgnoreCase("cpu")) {
                cpuHandler.handle(words);
            } else if (words[0].equalsIgnoreCase("parts") &&
                    words[1].equalsIgnoreCase("list")) {
                handleListParts(words);
            } else if (words[0].equalsIgnoreCase("parts") &&
                    words[1].equalsIgnoreCase("add")) {
                handleAddParts(words);
            }
        } while (!command.equalsIgnoreCase("quit"));
    }

    private static void printAllOptions() {
        System.out.println("- [parts list] ");
        System.out.println("- [parts add {mark} {model}] ");
    }

    private static void handleAddParts(String[] words) {
        PartsDao PartsDao = new PartsDao();
        EntityDao<Parts> PartsEntityDao = new EntityDao<>();
        if (!PartsDao.existsPartsModel(words[2])) {
            Parts parts = Parts.builder()
                    .mark(words[2])
                    .model(words[3])
                    .build();

            PartsEntityDao.saveOrUpdate(parts);
            System.out.println("Part saved.");
        } else {
            System.err.println("Part cannot be saved. Model already exists.");
        }
    }

    private static void handleListParts(String[] words) {
        EntityDao<Parts> PartsEntityDao = new EntityDao<>();
        PartsEntityDao
                .findAll(Parts.class)
                .forEach(System.out::println);
    }
}