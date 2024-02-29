package com.sbs.exam.board.article.repository;

import com.sbs.exam.board.Util;
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

  public List<Article> getArticles(String searchKeyword, String orderBy) {
    List<Article> filteredArticles = articles;

    // 검색 기능 시작
    if (searchKeyword.length() > 0) {
      filteredArticles = new ArrayList<>();

      for (Article article : articles) {
        boolean matched = article.getTitle().contains(searchKeyword) || article.getBody().contains(searchKeyword);

        if (matched) {
          filteredArticles.add(article);
        }
      }
    }
    // 검색 기능 끝

    // 정렬 기능 시작
    List<Article> sortedArticles = filteredArticles;

    boolean orderByIdDesc = orderBy.equals("idDesc");

    if (orderByIdDesc) {
      sortedArticles = Util.reverseList(sortedArticles);
    }
    // 정렬 기능 끝

    return sortedArticles;
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
