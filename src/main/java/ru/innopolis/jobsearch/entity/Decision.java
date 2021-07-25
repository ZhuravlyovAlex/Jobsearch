package ru.innopolis.jobsearch.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "decisions", uniqueConstraints=@UniqueConstraint(columnNames={"vacancy_id", "cv_id"}))
@Data//ломбок аннотация: генерирует геттеры, сеттеры, иквалс, хеш код методы
@NoArgsConstructor//ломбок аннотация: конструктор без аргуметов
public class Decision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Vacancy.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "vacancy_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Vacancy vacancy;

    @ManyToOne(targetEntity = CV.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "cv_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CV cv;

    @Column
    private Boolean decision;
}
