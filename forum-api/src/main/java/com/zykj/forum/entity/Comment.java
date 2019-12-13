package com.zykj.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "ccontent",columnDefinition = "text")
    private String content;
    @Column(name = "ccreate_at",columnDefinition = "timestamp")
    private Date create_at;

    @ManyToOne
    @JoinColumn(name = "uid",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "pid",referencedColumnName = "id")
    private Posts posts;

}