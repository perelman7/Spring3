package com.webTest.models;

import lombok.*;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "departments")
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter @Setter
    private int id;


    @Column(name = "name_dep")
    @NotNull
    @Size(min = 2, max = 25)
    @Getter @Setter
    private String depName;

    @Column(name = "description")
    @NotNull
    @Size(min = 2, max = 25)
    @Getter @Setter
    private String description;

    public Department(String name, String description) {
        this.depName = name;
        this.description = description;
    }
}
