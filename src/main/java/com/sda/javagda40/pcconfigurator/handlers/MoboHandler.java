package com.sda.javagda40.pcconfigurator.handlers;

import com.sda.javagda40.pcconfigurator.database.EntityDao;
import com.sda.javagda40.pcconfigurator.model.EconomyClass;
import com.sda.javagda40.pcconfigurator.model.MoBo;

import java.util.Scanner;

public class MoboHandler {
    private Scanner scanner = new Scanner(System.in);
    private EntityDao<MoBo> moBoEntityDao = new EntityDao<>();

    public void handle(String[] words){
        if(words[1].equalsIgnoreCase("add")){
            handleMoboAdd();
        }
    }

    private void handleMoboAdd() {
        MoBo mobo = new MoBo();

        System.out.println("Provide mark:");
        String mark = scanner.nextLine();
        mobo.setMark(mark);

        System.out.println("Provide model:");
        String model = scanner.nextLine();
        mobo.setModel(model);

        System.out.println("Provide pci:");
        String pci = scanner.nextLine();
        mobo.setPci(pci);

        System.out.println("Provide economy class:");
        EconomyClass economy = EconomyClass.valueOf(scanner.nextLine().toUpperCase());
        mobo.setEconomyClass(economy);

        moBoEntityDao.saveOrUpdate(mobo);
    }
}
