package com.zykj.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_plate")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plate implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "pname",columnDefinition = "varchar",length = 64)
    private String plateName;

    @OneToMany
    private List<Subject> subjects;
}
