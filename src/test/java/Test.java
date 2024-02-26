import java.util.ArrayList;
import java.util.List;

public class Test {
  public static void main(String[] args) {
    List<Article> articles = new ArrayList<>();
    articles.add(new Article(1, "제목1", "내용1"));
    articles.add(new Article(2, "중고 물품 판매합니다.", "내용2"));
    articles.add(new Article(3, "팰 월드 육성 방법", "식량 채집1"));

    String searchKeyword = "1";
    List<Article> filteredArticles = new ArrayList<>();

    for(Article article : articles) {
      if(article.title.contains(searchKeyword) || article.body.contains(searchKeyword)) {
        filteredArticles.add(article);
      }
    }

    System.out.println(filteredArticles);

  }
}

class Article {
  int id;
  String title;
  String body;

  Article(int id, String title, String body) {
    this.id = id;
    this.title = title;
    this.body = body;
  }

  @Override
  public String toString() {
    return String.format("{id : %d, title : \"%s\", body : \"%s\"}", id, title, body);
  }
}