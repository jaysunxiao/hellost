package com.hellost.service;

import com.hellost.model.five.FiveRange;
import com.hellost.model.five.FiveRangeResult;
import com.hellost.util.HttpUtils;
import com.zfoo.protocol.collection.ArrayUtils;
import com.zfoo.protocol.util.JsonUtils;
import com.zfoo.protocol.util.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
public class StockService {

    public static final List<String> HEADERS = List.of(
            "accept", "*/*",
            "Accept-Language", "zh-CN,zh;q=0.9",
            "Referer", "http://q.10jqka.com.cn/",
            "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36"
    );


    public FiveRange fiveRange(String code) throws IOException, InterruptedException {
        var client = HttpClient.newBuilder().build();

        var url = StringUtils.format("http://d.10jqka.com.cn/v2/fiverange/hs_{}/last.js", code);

        var responseBodyHandler = HttpResponse.BodyHandlers.ofString();
        var request = HttpRequest.newBuilder(URI.create(url))
                .headers(ArrayUtils.listToArray(HEADERS, String.class))
                .GET()
                .build();

        var responseBody = client.send(request, responseBodyHandler).body();
        responseBody = HttpUtils.formatJson(responseBody);
        var response = JsonUtils.string2Object(responseBody, FiveRangeResult.class);
        return response.getItems();
    }

}
