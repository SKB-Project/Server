package com.project.skb.post.domain;


import com.project.skb.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
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

    @CreatedDate
    @Column(name = "date_time")
    private LocalDateTime dateTime;


    @Column(name = "view_count")
    private Long viewCount; // 조회수 기능을 위한 게시글 조회수 변수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    @Builder
    public Post(String type, String title, String content, Long viewCount){
        this.type = type;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
    }

    public void insertUser(User user) {
        this.user = user;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }
}
