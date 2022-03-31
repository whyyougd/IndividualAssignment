package com.sparta.individualassignment.model;

import com.sparta.individualassignment.dto.BlogRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@NoArgsConstructor
@Getter
@Entity
public class Blog extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.REMOVE)
    List<Comment> comment = new ArrayList<>();

    public Blog(String username, String title, String contents){
        this.username = username;
        this.title = title;
        this.contents = contents;
    }

    public Blog(BlogRequestDto requestDto){
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void update(BlogRequestDto requestDto) {
        this.username =requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }


//    public void update(BlogRequestDto requestDto, Long userId){
//        this.userId = userId;
//        this.username = requestDto.getUsername();
//        this.title = requestDto.getTitle();
//        this.contents = requestDto.getContents();
//    }
}