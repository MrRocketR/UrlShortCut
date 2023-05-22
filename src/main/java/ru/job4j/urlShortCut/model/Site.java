package ru.job4j.urlShortCut.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sites")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name ="login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "site")
    private String site;
    @OneToMany(mappedBy = "site")
    private Set<Url> urls;
}
