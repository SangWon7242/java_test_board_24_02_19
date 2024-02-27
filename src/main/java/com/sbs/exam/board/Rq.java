package com.sbs.exam.board;

import java.util.Map;

public class Rq {
  String url;
  Map params;
  String urlPath;

  Rq(String url) {
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
}