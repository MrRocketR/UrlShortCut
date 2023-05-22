package ru.job4j.urlShortCut.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "urls")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name ="code")
    private String code;
    @Column(name = "url")
    private String url;
    @ManyToOne
    @JoinColumn(name="site_id")
    private Site site;
    @Column(name = "total")
    private int total;
}