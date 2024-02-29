package com.sbs.exam.board.article.service;

import com.sbs.exam.board.article.dto.Article;
import com.sbs.exam.board.article.repository.ArticleRepository;
import com.sbs.exam.board.container.Container;

import java.util.List;

public class ArticleService {
  private ArticleRepository articleRepository;

  public ArticleService() {
    articleRepository = Container.getArticleRepository();
  }

  public void makeTestData() {
    // 테스트 게시물을 100개로 늘림
    for(int i = 1; i <= 100; i++) {
      String title = "제목" + i;
      String body = "내용" + i;
      write(title, body);
    }
  }

  public int write(String title, String body) {
    return articleRepository.write(title, body);
  }

  public List<Article> getArticles(String searchKeyword, String orderBy) {
    return articleRepository.getArticles(searchKeyword, orderBy);
  }

  public Article findById(int id) {
    return articleRepository.findById(id);
  }

  public void remove(Article article) {
    articleRepository.remove(article);
  }

  public void modify(int id, String title, String body) {
    articleRepository.modify(id, title, body);
  }
}
