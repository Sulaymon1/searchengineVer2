package ru.web.skyforce.models;

import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(unique = true)
    private String categoryNameToLower;

}
