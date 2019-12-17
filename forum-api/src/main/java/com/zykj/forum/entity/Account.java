package com.zykj.forum.entity;

import com.zykj.forum.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "tb_account")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "aacc",columnDefinition = "varchar",length = 32)
    private String account;
    @Column(name = "apword",columnDefinition = "varchar")
    private String password;
    @Column(name = "asalt",columnDefinition = "varchar")
    private String salt;

    @OneToOne(targetEntity = User.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "uid",referencedColumnName = "id")
    private User user;

    @Column(name = "aphone",columnDefinition = "bool")
    private Boolean isPhone;
}
