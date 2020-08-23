package com.sda.javagda40.pcconfigurator.model;

import lombok.*;

import javax.persistence.*;
//import java.util.Set;

@Data // Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Parts { // POJO

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mark;
    private String model;

//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    @EqualsAndHashCode.Exclude
//    private Set<Address> addresses;

    public Parts(String mark, String model) {
        this.mark = mark;
        this.model = model;
    }
}