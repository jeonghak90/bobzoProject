package org.zerock.myapp.controller;


import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.myapp.domain.Recipe;
import org.zerock.myapp.domain.RecipeDTO;
import org.zerock.myapp.service.RecipeService;

import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Log4j2
@NoArgsConstructor


@RequestMapping("/recipe/*")
@Controller
public class RecipeController {

    @Setter(onMethod_ = @Autowired)
    private RecipeService recipeService;

    @GetMapping("/write")
    String write(Model model){
        log.trace("write() Invoked.");

        model.addAttribute("recipeDTO", new RecipeDTO()); // 폼을 위한 빈 RecipeDTO 생성
        return "/recipe/write"; // Thymeleaf 템플릿 이름

    } // write 글작성

    @PostMapping("/updateRecipe")                           // 이것만 하면 userid가 세팅이 안된다 그래서 Principal 로 로그인된 UserId를 받아온다.
    public String updateRecipe(@ModelAttribute("recipeDTO") RecipeDTO recipeDTO, Principal principal){

        if(principal != null) {
            String loggedInUserId = principal.getName();
            recipeService.recipeWrite(recipeDTO, loggedInUserId);
        }

        return "redirect:/recipe/page/1";
    } // updateRecipe


        @GetMapping("/page/{page}")
    public String showPagedRecipeList(@PathVariable("page") int page, Model model){
        int pageSize = 10;                     //    1페이지부터 시작 page-1 -> 0p
            Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "num"));

            Page<Recipe> recipePage = recipeService.getPagedRecipes(pageable);

            model.addAttribute("recipes", recipePage);
            return "recipe/page";
     } // showPagedRecipeList



    @GetMapping("/readRecipe/{recipeNum}")
    public String readRecipe(@PathVariable("recipeNum") Long recipeNum, Model model) {
        recipeService.updateViewCount(recipeNum);

        Recipe recipe = recipeService.getRecipeByNum(recipeNum);
        model.addAttribute("recipe", recipe);
        return "recipe/readRecipe";
    }


} // end class
