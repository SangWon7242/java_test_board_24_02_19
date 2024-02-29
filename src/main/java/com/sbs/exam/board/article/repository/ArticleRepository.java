package com.sbs.exam.board.article.repository;

import com.sbs.exam.board.article.dto.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
  private int lastId;
  private List<Article> articles;

  public ArticleRepository() {
    lastId = 0;
    articles = new ArrayList<>();
  }

  public int write(String title, String body) {
    int id = lastId + 1;
    articles.add(new Article(id, title, body));
    lastId = id;

    return id;
  }

  public List<Article> getArticles() {
    return articles;
  }

  public Article findById(int id) {
    for (Article article : articles) {
      if (article.getId() == id) {
        return article;
      }
    }

    return null;
  }

  public void remove(Article article) {
    articles.remove(article);
  }

  public void modify(int id, String title, String body) {
    Article article = findById(id);

    article.setTitle(title);
    article.setBody(body);
  }
}
