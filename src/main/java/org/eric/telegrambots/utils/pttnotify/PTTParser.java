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
import java.util.List;
import java.util.stream.Collectors;

public class PTTParser {
    private final static String BOARD_URL_FORMAT = "https://www.ptt.cc/bbs/%s/index.html";
    private final static String PTT_BASE_URL = "https://www.ptt.cc%s";

    private static List<Post> getPosts(String board, int limitPage) {
        return getPostsRecursion(board, limitPage, null, null);
    }

    private static List<Post> getPostsRecursion(String board, int limitPage, String prevPageUrl, List<Post> acc) {
        if (limitPage == 0) {
            return acc;
        }

        String fullUrl = prevPageUrl == null ? String.format(BOARD_URL_FORMAT, board) : String.format(PTT_BASE_URL, prevPageUrl);
        acc = acc == null ? new ArrayList<Post>() : acc;

        try {
            Document doc = Jsoup.connect(fullUrl)
                    .cookie("over18", "1")
                    .get();

            // 下一頁的按鈕 url
            String prevUrl = doc.body()
                    .select(".btn-group.btn-group-paging a[href]").get(1)
                    .attr("href");

            // 爬出文章的 url title like
            Elements elements = doc.body().select("div.r-ent");
            List<Post> urls = elements.stream()
                    .filter((element) -> element.select(".title a").size() > 0)
                    .map((element) -> {
                        String like = element.select(".nrec span").text();
                        String url = element.select(".title a").attr("href");
                        long id = Integer.parseInt(url.split("\\.")[1]);

                        Post post = new Post();
                        post.setId(id);
                        post.setUrl(url);
                        post.setTitle(element.select(".title a").text());
                        post.setLike(StringUtil.isBlank(like) ? 0 : Integer.parseInt(like));

                        return post;
                    }).collect(Collectors.toList());

            acc.addAll(urls);
            limitPage--;

            return getPostsRecursion(board, limitPage, prevUrl, acc);

        } catch (IOException e) {
            throw new NotFoundException("Can't find ptt board");
        }

    }

    public static void main(String[] args) {
        List<Post> urls = PTTParser.getPosts("Gossiping", 3);
        urls.stream().forEach((post) -> {
            System.out.println(post.getId());
            System.out.println(post.getLike());
            System.out.println(post.getTitle());
            System.out.println(post.getUrl());
            System.out.println("=============");
        });
    }
}
