package org.zerock.myapp.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Data
@ToString(exclude = "recipeListCategories")

@Table(name = "Categories")
@Entity
public class Categories {
    @Id
    @Column(name = "CategoriesSeq")
    @SequenceGenerator(
            name = "CategoriesGenerator",
            sequenceName = "CategoriesSeq",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(
            generator =  "CategoriesGenerator",
            strategy = GenerationType.SEQUENCE)
    private Integer num;

    private String name;




    @OneToMany(
            targetEntity = Recipe.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "fkCategories"
    )
    private List<Recipe> recipeListCategories = new ArrayList<>();

} // end class
