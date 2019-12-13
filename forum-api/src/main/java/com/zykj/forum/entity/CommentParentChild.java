package com.zykj.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "tb_comment_parent_child")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentParentChild {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid",referencedColumnName = "id")
    private Comment parent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cid",referencedColumnName = "id")
    private Comment child;
}
