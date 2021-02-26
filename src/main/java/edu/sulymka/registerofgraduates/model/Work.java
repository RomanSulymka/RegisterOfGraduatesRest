package edu.sulymka.registerofgraduates.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Entity
@Table(name = "works")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String position;
    private String company;
    private String startWork;
    private String endWork;

    @ManyToOne
    @JoinColumn(name = "graduates_id")
    private Graduated graduated;

    public Work() {
    }
}
