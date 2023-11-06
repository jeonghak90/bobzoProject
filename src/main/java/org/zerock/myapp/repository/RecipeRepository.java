package org.zerock.myapp.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.Recipe;
import org.zerock.myapp.domain.Users;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{
    List<Recipe> findByFkUsers(Users users);
    List<Recipe> findAllByOrderByNumDesc();
    Recipe findByNum(Long num);

    @Modifying
    @Query("UPDATE Recipe r SET r.cnt = r.cnt + 1 WHERE r.num = :num")
    void updateViewCount(@Param("num") Long num);

} // end interface
