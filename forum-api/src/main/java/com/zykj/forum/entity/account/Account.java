package com.zykj.forum.entity.account;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "tb_account")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "aacc",columnDefinition = "varchar",length = 32)
    private String account;
    @Column(name = "apword",columnDefinition = "varchar",length = 16)
    private String password;
    @OneToOne(targetEntity = User.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "u_id",referencedColumnName = "id")
    private User user;
}
