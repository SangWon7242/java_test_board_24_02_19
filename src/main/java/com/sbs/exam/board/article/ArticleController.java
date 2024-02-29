package com.sbs.exam.board.article;

import com.sbs.exam.board.Rq;
import com.sbs.exam.board.Util;
import com.sbs.exam.board.article.dto.Article;
import com.sbs.exam.board.article.service.ArticleService;
import com.sbs.exam.board.container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ArticleController {
  private ArticleService articleService;
  private List<Article> articles;

  public ArticleController() {
    articleService = Container.getArticleService();
    articleService.makeTestData();
    articles = articleService.getArticles();
  }

  public void actionWrite(Rq rq) {
    System.out.println("== 게시물 작성 ==");
    System.out.printf("제목 : ");
    String title = Container.sc.nextLine();

    if(title.length() == 0) {
      System.out.println("제목을 입력해주세요.");
      return;
    }

    System.out.printf("내용 : ");
    String body = Container.sc.nextLine();

    if(body.length() == 0) {
      System.out.println("내용을 입력해주세요.");
      return;
    }

    int id = articleService.write(title, body);

    System.out.printf("%d번 게시물이 생성되었습니다.\n", id);
  }

  public void showList(Rq rq) {
    Map<String, String> params = rq.getParams();

    System.out.println("== 게시물 리스트 ==");
    System.out.println("===================");
    System.out.println("번호 / 제목");
    System.out.println("===================");

    String searchKeyword = rq.getParam("searchKeyword", "");

    // articles : 정렬되지 않은 리모콘의 복사본(객체 주소) 있다.
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

    String orderBy = rq.getParam("orderBy", "idDesc");
    boolean orderByIdDesc = orderBy.equals("idDesc");

    if (params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")) {
      orderByIdDesc = false;
    }

    if (orderByIdDesc) {
      sortedArticles = Util.reverseList(sortedArticles);
    }
    // 정렬 기능 끝

    for (Article article : sortedArticles) {
      System.out.printf("%d / %s\n", article.getId(), article.getTitle());
    }
  }

  public void showDetail(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    Article article = articleService.findById(id);

    if (article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    System.out.println("== 게시물 상세내용 ==");
    System.out.printf("번호 : %d\n", article.getId());
    System.out.printf("제목 : %s\n", article.getTitle());
    System.out.printf("내용 : %s\n", article.getBody());
  }

  public void actionModify(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    Article article = articleService.findById(id);

    if (article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    System.out.printf("새 제목 : ");
    String newTitle = Container.sc.nextLine();
    System.out.printf("새 내용 : ");
    String newBody = Container.sc.nextLine();

    articleService.modify(id, newTitle, newBody);

    System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
  }

  public void actionDelete(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    Article article = articleService.findById(id);

    if (article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    articleService.remove(article);

    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
  }
}
