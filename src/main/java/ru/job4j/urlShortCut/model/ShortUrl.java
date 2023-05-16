package ru.job4j.urlShortCut.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "shortUrl")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name ="code")
    private String code;
    @Column(name = "url")
    private String url;
    @ManyToMany
    @JoinColumn(name="urlId")
    private Site site;
    @Column(name = "total")
    private int total;
}
