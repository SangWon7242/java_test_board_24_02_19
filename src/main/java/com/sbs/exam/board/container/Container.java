package com.sbs.exam.board.container;

import com.sbs.exam.board.article.ArticleController;

import java.util.Scanner;

public class Container {
  public static Scanner sc;

  public static ArticleController articleController;

  static {
    sc = new Scanner(System.in);

    articleController = new ArticleController();
  }
}
