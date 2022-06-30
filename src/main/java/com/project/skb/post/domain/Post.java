package com.project.skb.post.domain;


import com.project.skb.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "post")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "type")
    private String type;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    @Builder
    public Post(String type, String title, String content){
        this.type = type;
        this.title = title;
        this.content = content;
    }

}
