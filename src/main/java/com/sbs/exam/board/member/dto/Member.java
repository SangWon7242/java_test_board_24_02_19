package com.sbs.exam.board.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
  private int id;
  private String loginId;
  private String loginPw;
  private String name;
}
