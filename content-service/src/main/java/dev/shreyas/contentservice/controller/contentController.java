package dev.shreyas.contentservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.shreyas.contentservice.model.Article;
import dev.shreyas.contentservice.service.ArticleClient;

@RestController
@RequestMapping("/api/content")
public class contentController {

    public final ArticleClient articleClient;

    public contentController(ArticleClient articleClient) {
        this.articleClient = articleClient;
    }

    @GetMapping("/articles")
    public ResponseEntity<List<Article>> findAllArticles() {
        return articleClient.findAll();
    }

    @GetMapping("/articles/{id}")
    public Optional<Article> findById(@PathVariable Integer id) {
        return articleClient.findOne(id);
    }

    @PostMapping("/articles")
    public void create(@RequestBody Article article) {
        articleClient.create(article);
    }

    @PutMapping("/articles/{id}")
    public void update(@RequestBody Article article, @PathVariable Integer id) {
        articleClient.update(article,id);
    }

    @DeleteMapping("/articles/{id}")
    public void delete(@PathVariable Integer id) {
        articleClient.delete(id);
    }
}
