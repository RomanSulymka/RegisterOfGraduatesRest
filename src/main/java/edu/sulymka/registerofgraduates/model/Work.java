package edu.sulymka.registerofgraduates.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Entity
@Table(name = "works")
public class Work implements Serializable {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String position;
    private String company;
    private String startWork;
    private String endWork;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "graduates_id")
    private Graduated graduated;
}
