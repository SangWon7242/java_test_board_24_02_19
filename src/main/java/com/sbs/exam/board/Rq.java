package com.sbs.exam.board;

import java.util.Map;

public class Rq {
  String url;
  Map<String, String> params;
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

  public String getParam(String paramName, String defaultValue) {
    if(params.containsKey(paramName) == false) {
      return defaultValue;
    }

    return params.get(paramName);
  }

  public int getIntParam(String paramName, int defaultValue) {
    if(params.containsKey(paramName) == false) {
      return defaultValue;
    }

    try {
      return Integer.parseInt(params.get(paramName));
    }
    catch (NumberFormatException e) {
      return defaultValue;
    }
  }
}