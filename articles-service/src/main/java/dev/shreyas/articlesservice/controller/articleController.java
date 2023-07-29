package dev.shreyas.articlesservice.controller;
import dev.shreyas.articlesservice.model.Article;
import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles")
public class articleController {
    private final ArrayList<Article> articles = new ArrayList<>();
    
    @GetMapping()
    private ArrayList<Article> findAll(){
        return(articles);
    }

    @GetMapping("/{id}")
    private Optional<Article> findById(@PathVariable int id){
        return articles.stream().filter(a -> a.id().equals(id)).findFirst();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private void create(@RequestBody Article article){
        articles.add(article);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private void put(@PathVariable int id, @RequestBody Article article){
        Optional<Article> currentArticle = articles.stream().filter(a -> a.id().equals(id)).findFirst();
        currentArticle.ifPresent(v -> articles.set(articles.indexOf(v), article));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private void delete(@PathVariable int id){
        articles.removeIf(v -> v.id().equals(id));
    }

    @PostConstruct
    private void init(){
        Article article = new Article(1,"This is a test","Test");
        articles.add(article);
    }
}
