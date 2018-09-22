package ru.web.skyforce.dto;

import lombok.*;


/**
 * Created by Sulaymon on 22.09.2018.
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchDTO {
    private Long id;
    private String keyword;
    private String city;
    private Integer currentPage;
}
