package edu.sulymka.registerofgraduates.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Entity
@Table
public class Graduated implements Serializable {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String gender;
    private String imageUrl;
    private String jobTitle;
    private String graduationYearFromBachelor;
    private String graduationYearFromMaster;
    private String graduationYearFromOrkSpecialist;
    private String linkedinUrl;

    @JsonManagedReference
    @OneToMany(mappedBy = "graduated")
    private List<Work> works;

}
