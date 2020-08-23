package com.sda.javagda40.pcconfigurator.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String mark;
    private String model;
    private String type;
    private String frequency;
    private String latency;

    @Enumerated(value = EnumType.STRING)
    private EconomyClass economyClass;

    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<MoBo> mobos;

}
