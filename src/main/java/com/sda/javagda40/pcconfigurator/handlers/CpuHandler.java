package com.sda.javagda40.pcconfigurator.handlers;

import com.sda.javagda40.pcconfigurator.database.CpuDao;
import com.sda.javagda40.pcconfigurator.database.EntityDao;
import com.sda.javagda40.pcconfigurator.model.Cpu;
import com.sda.javagda40.pcconfigurator.model.EconomyClass;
import com.sda.javagda40.pcconfigurator.model.MoBo;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CpuHandler {
    private Scanner scanner = new Scanner(System.in);
    private EntityDao<Cpu> cpuEntityDao = new EntityDao<>();
    private EntityDao<MoBo> moBoEntityDao = new EntityDao<>();
    private CpuDao cpuDao = new CpuDao();

    public void handle(String[] words) {
        if (words[1].equalsIgnoreCase("add")) {
            handleCpuAdd();
        }else if(words[1].equalsIgnoreCase("addmobo")){
            handleAddMobo();
        }
    }

    private void handleCpuAdd() {
        Cpu cpu = new Cpu();

        System.out.println("Provide mark:");
        String mark = scanner.nextLine();
        cpu.setMark(mark);

        System.out.println("Provide model:");
        String model = scanner.nextLine();
        cpu.setModel(model);

        System.out.println("Provide socket:");
        String socket = scanner.nextLine();
        cpu.setSocket(socket);

        System.out.println("Provide economy class:");
        EconomyClass economy = EconomyClass.valueOf(scanner.nextLine().toUpperCase());
        cpu.setEconomyClass(economy);

        cpuEntityDao.saveOrUpdate(cpu);
    }

    public void handleAddMobo(){
        Cpu cpu = null;
        MoBo mobo = null;
        do {
            List<Cpu> cpuList = cpuEntityDao.findAll(Cpu.class);
            System.out.println("List of CPU:");
            cpuList.stream().forEach(System.out::println);
            System.out.println("");
            System.out.println("Provide CPU id:");
            Long cpuId = Long.parseLong(scanner.nextLine());
            Optional<Cpu> optionalCpu = cpuEntityDao.findById(Cpu.class, cpuId);
            if(optionalCpu.isPresent()){
                cpu = optionalCpu.get();
            }
        }while (cpu ==null);

        do {
            List<MoBo> moBoList = moBoEntityDao.findAll(MoBo.class);
            System.out.println("List of MoBo:");
            moBoList.stream().forEach(System.out::println);
            System.out.println("");
            System.out.println("Provide Mobo id:");
            Long moboId = Long.parseLong(scanner.nextLine());
            Optional<MoBo> optionalMoBo = moBoEntityDao.findById(MoBo.class, moboId);
            if(optionalMoBo.isPresent()){
                mobo = optionalMoBo.get();
            }
        }while (mobo ==null);

        cpuDao.saveMoboRelation(cpu, mobo);

        System.out.println("Powiązanie zapisane.");
    }
}
