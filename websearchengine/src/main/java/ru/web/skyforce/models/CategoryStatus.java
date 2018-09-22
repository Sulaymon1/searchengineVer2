package ru.web.skyforce.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Date 22.09.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
@Entity
@Table(name = "categorystatus")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CategoryStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Category category;


    private Boolean inProcess;
    private Boolean ready;
}
