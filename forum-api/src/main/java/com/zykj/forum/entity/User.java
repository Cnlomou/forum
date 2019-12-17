package com.zykj.forum.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(name = "upicurl",columnDefinition = "varchar")
    private String picUrl;
    @Column(name = "ucreate_at",columnDefinition = "timestamp")
    private Date createTime;
    @Column(name = "ulastaccess_at",columnDefinition = "timestamp")
    private Date lastAccessTime;
}
