package ru.web.skyforce.models;

import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "data")
@ToString
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne( fetch = FetchType.LAZY)
    private Category category;
    @OneToOne(fetch = FetchType.LAZY)
    private City city;

    private String name;
    private String uri;
    private String email;
    private String website;
    private String phone;
    private String address;

}
