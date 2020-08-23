package com.sda.javagda40.pcconfigurator.model;

import com.sun.xml.bind.v2.model.core.ID;
        import lombok.*;
        import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;

    @Enumerated(value = EnumType.STRING)
    private EconomyClass economyClass;

    @OneToMany(mappedBy = "platform", fetch = FetchType.LAZY)
    private Set<MoBo> moBos;
}
