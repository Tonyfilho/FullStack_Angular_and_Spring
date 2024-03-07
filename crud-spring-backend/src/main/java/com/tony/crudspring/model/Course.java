package com.tony.crudspring.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data // Pertence ao lombok, e ja gera Get, Set ToString e RashCode
@Entity // vai ser uma tabela na DB do JPA
@SQLDelete(sql = "UPDATE Course SET status = 'inactive' WHERE status = 'active' and id = ?") // usando o Where
// @SQLDelete(sql = "UPDATE Course SET status = 'inativo' WHERE id = ?")//
// @SQLDelete() permite criar uma query para padronizar um DELETE no SQL
// @Where(clause = "status = 'active'")
public class Course {

    @Id // diz a JPA que isto é um chave primaria
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @Length(min = 2, max = 100)
    @NotNull
    @NotBlank
    @Column(length = 100, nullable = false)
    private String name;

    @Length(max = 100)
    @NotNull
    @NotBlank
    @Pattern(regexp = "back-end|front-end")
    @Column(length = 10, nullable = false)
    private String category;

    @Length(max = 8)
    @NotNull
    @NotBlank
    @Pattern(regexp = "active|inactive")
    @Column(length = 10, nullable = false)
    private String status = "active";

    /** 1 para muitos desta forma aqui é economizado perfomace pois temos menos uma query em relação a CourseWithEnums */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course") /**
                                           * cascade = CascadeType.ALL quer dizer qualquer alteração na Entidade de
                                           * Course, será tb verificada se houve na entidade Lesson, se for removido um
                                           * Course será tb removido da entidade Lesson estes dados
                                           */
   
    private List<Lesson> lessons = new ArrayList<>();
}
