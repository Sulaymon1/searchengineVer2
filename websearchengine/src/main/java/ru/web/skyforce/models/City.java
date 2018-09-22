package ru.web.skyforce.models;

import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String state;
    private String stateToLower;
    private String name;
    private String nameToLower;
    private String shortenedState;
}
