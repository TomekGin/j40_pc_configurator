package com.sda.javagda40.pcconfigurator.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Psu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String standard;
    private String mark;
    private String model;
    private int power;

    @Enumerated(value = EnumType.STRING)
    private EconomyClass economyClass;

    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Cpu> cpus;
}