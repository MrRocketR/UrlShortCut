package ru.job4j.urlShortCut.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SIteDto {

    /**
     * Site
     */

    private int siteId;
    private String login;
    private String password;
    private String site;
    private boolean registration;

    /**
     * Shorted
     */
    private int ShortedId;
    private String url;
    private String code;
    private int total;
}
