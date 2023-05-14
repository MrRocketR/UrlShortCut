package ru.job4j.urlShortCut.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shortUrl")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShortUrl {
    private int id;
    private String shortUrl;
    private Url url;
}
