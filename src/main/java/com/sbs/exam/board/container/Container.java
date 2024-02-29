package com.sbs.exam.board.container;

import com.sbs.exam.board.article.ArticleController;
import com.sbs.exam.board.article.repository.ArticleRepository;
import com.sbs.exam.board.article.service.ArticleService;
import com.sbs.exam.board.member.MemberController;
import com.sbs.exam.board.member.repository.MemberRepository;
import com.sbs.exam.board.member.service.MemberService;
import com.sbs.exam.board.session.Session;
import lombok.Getter;

import java.util.Scanner;

public class Container {
  public static Scanner sc;
  public static Session session;

  @Getter
  private static MemberRepository memberRepository;
  @Getter
  private static ArticleRepository articleRepository;

  @Getter
  private static MemberService memberService;
  @Getter
  private static ArticleService articleService;

  @Getter
  private static MemberController memberController;
  @Getter
  private static ArticleController articleController;

  static {
    sc = new Scanner(System.in);
    session = new Session();

    memberRepository = new MemberRepository();
    articleRepository = new ArticleRepository();

    memberService = new MemberService();
    articleService = new ArticleService();

    memberController = new MemberController();
    articleController = new ArticleController();
  }

  public static Session getSession() {
    return session;
  }
}
