package com.example.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor
public class Paste {
    @Id
    @GeneratedValue()
    private  int id;

    @Column
    private String paste;

    @Column
    private LocalDateTime expirationDateTime;

    @Column
    private Boolean access;

    @Column
    private String hash;

    @Column
    private LocalDateTime dateTime;

    public Paste(String paste, LocalDateTime expirationDateTime, Boolean access, String hash, LocalDateTime dateTime) {
        this.paste = paste;
        this.expirationDateTime = expirationDateTime;
        this.access = access;
        this.hash = hash;
        this.dateTime = dateTime;
    }
}
