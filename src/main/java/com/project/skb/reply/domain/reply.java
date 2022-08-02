package com.project.skb.reply.domain;

import com.project.skb.user.domain.User;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

public class reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long replyId;

    @Column(name = "post_id")
    private Long postId;

    @ManyToOne
    @Column(name = "user_id")
    private User user;

    @Column(name = "content")
    private String content;

    @CreatedDate
    @Column(name = "date_time")
    private LocalDateTime dateTime;
}
