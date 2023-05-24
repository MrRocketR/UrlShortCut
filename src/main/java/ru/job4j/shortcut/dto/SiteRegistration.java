package ru.job4j.shortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SiteRegistration {
    private boolean registration;
    private String login;
    private String password;
}
