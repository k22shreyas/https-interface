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

    @GetMapping
    public ArrayList<Article> findAll() throws InterruptedException {
        Thread.sleep(1000);
        return articles;
    }

    @GetMapping("/{id}")
    public Optional<Article> findById(@PathVariable Integer id) {
        return articles.stream().filter(article -> article.id().equals(id)).findFirst();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Article article) {
        this.articles.add(article);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Article article, @PathVariable Integer id) {
        var currentArticle = articles.stream().filter(a -> a.id().equals(id)).findFirst();
        currentArticle.ifPresent(value -> this.articles.set(articles.indexOf(value), article));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        this.articles.removeIf(a -> a.id().equals(id));
    }

    @PostConstruct
    private void init() {
        Article article = new Article(1,"Hello, World!","This is my first post");
        this.articles.add(article);
    }
}
