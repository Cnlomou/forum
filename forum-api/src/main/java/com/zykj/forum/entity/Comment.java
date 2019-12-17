package com.zykj.forum.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;

    @ManyToOne
    @JoinColumn(name = "pid",referencedColumnName = "id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Posts posts;

}
