package com.ansh.board.model;

import lombok.AccessLevel;
//import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "board")
//public class BoardVO extends TimeEntity {
public class BoardVO {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    /*
    @Builder
    public BoardVO(Long id, String title, String content, String writer) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }*/
}