package com.zykj.forum.entity.account;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "uname",columnDefinition = "varchar",length = 255)
    private String name;
    @Column(name = "urname",columnDefinition = "varchar",length = 24)
    private String realName;
    @Column(name = "uphone",columnDefinition = "char",length = 11)
    private String phone;
    @Column(name = "ubir",columnDefinition = "Date")
    private Date birthday;
    @Column(name = "uaddr",columnDefinition = "varchar")
    private String address;
    @Column(name = "uemail",columnDefinition = "varchar")
    private String email;
}
