package com.zykj.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_posts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Posts implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "ptitle",columnDefinition = "varchar",length = 128)
    private String title;
    @Column(name = "pcontent",columnDefinition = "text")
    private String content;
    @Column(name = "plike",columnDefinition = "int")
    private Integer like;
    @Column(name = "pvisit",columnDefinition = "int")
    private Integer visit;
    @Column(name = "pcreate_time",columnDefinition = "timestamp")
    private Date create_at;
    @Column(name = "pupdate_time",columnDefinition = "timestamp")
    private Date update_at;


    @ManyToOne
    @JoinColumn(name = "sid",referencedColumnName = "id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid",referencedColumnName = "id")
    private User user;
}
