package com.tony.crudspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
// @Data // lombok
public class Lesson {
    @Id // diz a JPA que isto é um chave primaria
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String youtubeUrl;
    /** So salvaremos o final do link */

    /**
     * Mapeando o Lesson com A entidade Course OneToMany e aqui em Lesson ManyToOne
     * Temos a opção FetchType.LAZY q so carregará este mapeamento quando usarmos o
     * GetLesson();
     * optional = false que dizer q esta coluna tem que ser obrigatoria quando
     * houver um GetLesson();
     * 
     * @JoinColumn tem que sair de Course e vir para aqui no ManyToOne para dar nome
     *             a coluna
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)

    // @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    /*
     * esta propriedade foi descontinuada, onde fazia com que o GET verado pelo
     * Lombok
     * fosse bloqueado, e com resolvir o problema ciclico de a
     * entidade Course precisa desta coluna Course_Id e por sua
     * vez a
     * a entidade Lenson que depende da entidade Course, com
     * gerava um erro quando iamos fazer um Get de courses
     * Solução, remover o Lombok e gerar os Gets sem gerar o
     * Get de course, somente gerar o Set de Course, para
     * permitir salvar informações, ou seja va desearilizar somente fazendo o SET e
     * não o Get
     */
    private Course course;



    /**Gets e Sets */

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    @Override
    public boolean equals(Object obj) {        
        return super.equals(obj);
    }

    @Override
    public int hashCode() {        
        return super.hashCode();
    }

    @Override
    public String toString() {        
        return super.toString();
    }

}
