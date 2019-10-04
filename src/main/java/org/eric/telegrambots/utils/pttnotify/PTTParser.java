package org.eric.telegrambots.utils.pttnotify;

import org.eric.telegrambots.exception.NotFoundException;
import org.eric.telegrambots.model.pttnotify.Post;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PTTParser {
    private final static String BOARD_URL_FORMAT = "https://www.ptt.cc/bbs/%s/index.html";
    private final static String PTT_BASE_URL_FORMAT = "https://www.ptt.cc%s";

    private String board;

    private PTTParser() {
    }

    public PTTParser(String board) {
        this.board = board;
    }

    public List<Post> getPosts(int limitPage, int limitLike) {
        return getPostsRecursion(this.board, limitPage, limitLike, null, null);
    }

    private List<Post> getPostsRecursion(String board, int limitPage, int limitLike, String prevPageUrl, List<Post> acc) {
        if (limitPage == 0) {
            // sort by id
            acc.sort(Comparator.comparingLong(Post::getId));
            return acc;
        }

        // init acc
        acc = (acc == null) ? new ArrayList<Post>() : acc;

        Document doc;
        try {
            // 判斷要parser的url是第一頁還是之後的頁面
            String fullUrl = prevPageUrl == null ? String.format(BOARD_URL_FORMAT, board) : String.format(PTT_BASE_URL_FORMAT, prevPageUrl);
            // 需加入cookie 跳過滿18歲的確認
            doc = Jsoup.connect(fullUrl).cookie("over18", "1").get();
        } catch (IOException e) {
            throw new NotFoundException(String.format("Can't find ptt board: %s", this.board));
        }

        // 下一頁的按鈕 url
        String prevUrl = doc.body()
                .select(".btn-group.btn-group-paging a[href]").get(1)
                .attr("href");

        // 爬出文章的 url title like
        Elements elements = doc.body().select("div.r-list-container.action-bar-margin.bbs-screen div");

        // filter 第一頁的置底公告
        if (prevPageUrl == null) {
            // 只拿置底線以上的element
            elements = elements.select(".r-list-sep").prevAll();
        }

        List<Post> urls = elements.stream()
                .filter((element) -> element.select(".title a").size() > 0) // filter 被刪除的文章
                .filter((element) -> this.getLikeFromElement(element) > limitLike) // filter 推文數
                .map((element) -> {
                    int like = this.getLikeFromElement(element);
                    String url = element.select(".title a").attr("href");
                    long id = Integer.parseInt(url.split("\\.")[1]);

                    Post post = new Post();
                    post.setBoard(this.board);
                    post.setId(id);
                    post.setUrl(String.format(PTT_BASE_URL_FORMAT, url));
                    post.setTitle(element.select(".title a").text());
                    post.setLike(like);

                    return post;
                })
                .collect(Collectors.toList());

        acc.addAll(urls);
        limitPage--;

        return getPostsRecursion(board, limitPage, limitLike, prevUrl, acc);

    }

    private int getLikeFromElement(Element element) {
        int result;
        String like = element.select(".nrec span").text();
        if (like.equals("爆")) {
            result = 999;
        } else if (like.startsWith("X")) {
            result = -1;
        } else {
            result = StringUtil.isBlank(like) ? 0 : Integer.parseInt(like);
        }

        return result;
    }

    public static void main(String[] args) {
        PTTParser pttParser = new PTTParser("Beauty");
        List<Post> posts = pttParser.getPosts(1, 0);

        posts.stream().forEach((post) -> {
            System.out.println(post.getBoard());
            System.out.println(post.getId());
            System.out.println(post.getLike());
            System.out.println(post.getTitle());
            System.out.println(post.getUrl());
            System.out.println("=============");
        });
    }
}
