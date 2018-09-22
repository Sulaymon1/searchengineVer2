package ru.web.skyforce.models;

import lombok.*;

import javax.persistence.*;

/**
 * Date 22.09.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/

@Setter
@Getter
@Entity
@Table(name = "statistics")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String city;

    private Long count;

}
