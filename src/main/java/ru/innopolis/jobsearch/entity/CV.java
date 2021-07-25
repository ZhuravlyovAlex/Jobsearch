package ru.innopolis.jobsearch.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "cvs")
@Data
@NoArgsConstructor
public class CV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotBlank(message = "Поле специальность обязательно для заполнения")
    private String specialtyName;

    @Column
    private String description;

    @Column
    @NotBlank(message = "Поле заработная плата обязательно для заполнения")
    private Double salary;

    @Column
    @NotBlank(message = "Поле отображения обязательно для заполнения")
    private Boolean visible;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;
}
