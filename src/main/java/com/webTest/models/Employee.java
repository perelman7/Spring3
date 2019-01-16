package com.webTest.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter @Setter
    private int id;

    @Column(name = "surname")
    @NotNull
    @Size(min = 2)
    @Getter @Setter
    private String surname;

    @Column(name = "name")
    @NotNull
    @Size(min = 2)
    @Getter @Setter
    private String name;

    @Column(name = "fathername")
    @Getter @Setter
    private String fatherName;

    @Column(name = "date_of_berth")
    @NotNull
    @Getter @Setter
    private String dob;

    @ManyToOne
    @JoinColumn(name = "dep_id")
    @NotNull
    @Getter @Setter
    private Department department;

    public Employee(String surname, String name, String fatherName, String dob, Department department) {
        this.surname = surname;
        this.name = name;
        this.fatherName = fatherName;
        this.dob = dob;
        this.department = department;
    }
}
