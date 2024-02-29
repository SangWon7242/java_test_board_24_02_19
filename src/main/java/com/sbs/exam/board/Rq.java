package com.sbs.exam.board;

import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.member.dto.Member;
import com.sbs.exam.board.session.Session;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class Rq {
  public String url;
  public Map<String, String> params;
  public String urlPath;

  public Rq(String url) {
    this.url = url;
    params = Util.getParamsFromUrl(url);
    urlPath = Util.getUrlPathFromUrl(url);
  }

  public Map getParams() {
    return params;
  }

  public String getUrlPath() {
    return urlPath;
  }

  public void setCommand(String url) {
    urlPath = Util.getUrlPathFromUrl(url);
    params = Util.getParamsFromUrl(url);
  }

  public String getParam(String paramName, String defaultValue) {
    if (params.containsKey(paramName) == false) {
      return defaultValue;
    }

    return params.get(paramName);
  }

  public int getIntParam(String paramName, int defaultValue) {
    if (params.containsKey(paramName) == false) {
      return defaultValue;
    }

    try {
      return Integer.parseInt(params.get(paramName));
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public Object getSessionAttr(String key) {
    Session session = Container.getSession();

    return session.getAttribute(key);
  }


  public void setSessionAttr(String key, Member value) {
    Session session = Container.getSession();

    session.setAttribute(key, value);
  }

  public void removeSessionAttr(String loginedMember) {
    Session session = Container.getSession();

    session.removeAttribute(loginedMember);
  }

  public boolean hasSessionAttr(String loginedMember) {
    Session session = Container.getSession();

    return session.hasAttribute(loginedMember);
  }

  public boolean isLogined() {
    return hasSessionAttr("loginedMember");
  }

  public boolean isLogout() {
    return !isLogined();
  }

  public void login(Member member) {
    setSessionAttr("loginedMember", member);
  }

  public void logout() {
    removeSessionAttr("loginedMember");
  }

  public Member getLoginedMember() {
    return (Member) getSessionAttr("loginedMember");
  }
}