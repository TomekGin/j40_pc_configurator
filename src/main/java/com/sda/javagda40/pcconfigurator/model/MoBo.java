package com.sda.javagda40.pcconfigurator.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data // Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MoBo { // POJO
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mark;
    private String model;
    private String pci;

    @Enumerated(value = EnumType.STRING)
    private EconomyClass economyClass;

    @ManyToOne()
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Platform platform;

    @ManyToMany(mappedBy = "mobos") // czyli dodajemy mobo do cpu i zapisujemy cpu
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Cpu> cpus;

    @ManyToMany(mappedBy = "mobos")// czyli dodajemy mobo do gpu i zapisujemy gpu
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Gpu> gpus;

    @ManyToMany(mappedBy = "mobos")// czyli dodajemy mobo do gpu i zapisujemy gpu
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Ram> rams;
}
