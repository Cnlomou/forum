package com.zykj.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_subject")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Subject  implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "sname",columnDefinition = "varchar",length = 64)
    private String ssbjectName;
    @Column(name = "spicurl",columnDefinition = "varchar")
    private String picUrl;

    @ManyToOne
    @JoinColumn(name = "pid",referencedColumnName = "id")
    private Plate plate;
}
