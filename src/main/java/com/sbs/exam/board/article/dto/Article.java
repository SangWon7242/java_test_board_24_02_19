package com.sbs.exam.board.article.dto;

import lombok.*;

// @Data : Getter, Setter, toString을 자동 생성
@Data
// @NoArgsConstructor : 파라미터 없는 기본자 생성자 생성
@NoArgsConstructor
// @AllArgsConstructor : 모든 필드 값을 파라미터로 받는 생성자 생성
@AllArgsConstructor
public class Article {
  private int id;
  private String title;
  private String body;
}
