package ru.job4j.urlShortCut.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "shortUrl")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name ="code")
    private String code;
    @ManyToMany
    @JoinColumn(name="urlId")
    private Url url;
    @Column(name = "total")
    private int total;
}
