package com.sparta.individualassignment.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.sparta.individualassignment.dto.CommentRequestDto;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User username;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Blog blog;

    public Comment(CommentRequestDto requestDto){
        this.comment = requestDto.getComment();
        this.username = requestDto.getUsername();
        this.blog = requestDto.getBlog();
    }

    public long update (CommentRequestDto requestDto){
        this.comment = requestDto.getComment();
        this.id = requestDto.getUsername().getId();
        return id;
    }
}
