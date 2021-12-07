package com.hellost.service;

import com.hellost.model.Quote;
import com.hellost.model.hk.HkHSCCI;
import com.hellost.model.hk.HkHSCEI;
import com.hellost.model.hk.HkHSI;
import com.hellost.model.hs.Hs1A0001;
import com.hellost.model.hs.Hs399001;
import com.hellost.model.hs.Hs399006;
import com.hellost.util.HttpUtils;
import com.zfoo.event.model.event.AppStartEvent;
import com.zfoo.protocol.collection.ArrayUtils;
import com.zfoo.protocol.util.JsonUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class QuoteService implements ApplicationListener<AppStartEvent> {


    @Override
    public void onApplicationEvent(AppStartEvent event) {
//        try {
//            quoteHs1A0001();
//            quoteHs399001();
//            quoteHs399006();
//            quoteHkHSI();
//            quoteHkHSCEI();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


    // http://d.10jqka.com.cn/v6/time/hs_1A0001/last.js               行情数据
    // http://d.10jqka.com.cn/v6/time/hs_399001/last.js
    // http://d.10jqka.com.cn/v6/time/hs_399006/last.js

    // http://d.10jqka.com.cn/v6/time/hk_HSI/last.js
    // http://d.10jqka.com.cn/v6/time/hk_HSCEI/last.js
    // http://d.10jqka.com.cn/v6/time/hk_HSCCI/last.js


    // http://d.10jqka.com.cn/v2/exchangedetail/hs_601952/last12.js      分钟交易明细
    // http://d.10jqka.com.cn/v2/realhead/hs_601952/last.js              股票详细信息
    // http://d.10jqka.com.cn/v2/fiverange/hs_601952/last.js             买五卖无


    public Quote quoteHs1A0001() throws IOException, InterruptedException {
        var client = HttpClient.newBuilder().build();

        var responseBodyHandler = HttpResponse.BodyHandlers.ofString();
        var request = HttpRequest.newBuilder(URI.create("http://d.10jqka.com.cn/v6/time/hs_1A0001/last.js"))
                .headers(ArrayUtils.listToArray(StockService.HEADERS, String.class))
                .GET()
                .build();

        var responseBody = client.send(request, responseBodyHandler).body();
        responseBody = HttpUtils.formatJson(responseBody);
        var response = JsonUtils.string2Object(responseBody, Hs1A0001.class);
        return response.getQuote();
    }

    public Quote quoteHs399001() throws IOException, InterruptedException {
        var client = HttpClient.newBuilder().build();

        var responseBodyHandler = HttpResponse.BodyHandlers.ofString();
        var request = HttpRequest.newBuilder(URI.create("http://d.10jqka.com.cn/v6/time/hs_399001/last.js"))
                .headers(ArrayUtils.listToArray(StockService.HEADERS, String.class))
                .GET()
                .build();

        var responseBody = client.send(request, responseBodyHandler).body();
        responseBody = HttpUtils.formatJson(responseBody);
        var response = JsonUtils.string2Object(responseBody, Hs399001.class);
        return response.getQuote();
    }

    public Quote quoteHs399006() throws IOException, InterruptedException {
        var client = HttpClient.newBuilder().build();

        var responseBodyHandler = HttpResponse.BodyHandlers.ofString();
        var request = HttpRequest.newBuilder(URI.create("http://d.10jqka.com.cn/v6/time/hs_399006/last.js"))
                .headers(ArrayUtils.listToArray(StockService.HEADERS, String.class))
                .GET()
                .build();

        var responseBody = client.send(request, responseBodyHandler).body();
        responseBody = HttpUtils.formatJson(responseBody);
        var response = JsonUtils.string2Object(responseBody, Hs399006.class);
        return response.getQuote();
    }

    public Quote quoteHkHSI() throws IOException, InterruptedException {
        var client = HttpClient.newBuilder().build();

        var responseBodyHandler = HttpResponse.BodyHandlers.ofString();
        var request = HttpRequest.newBuilder(URI.create("http://d.10jqka.com.cn/v6/time/hk_HSI/last.js"))
                .headers(ArrayUtils.listToArray(StockService.HEADERS, String.class))
                .GET()
                .build();

        var responseBody = client.send(request, responseBodyHandler).body();
        responseBody = HttpUtils.formatJson(responseBody);
        var response = JsonUtils.string2Object(responseBody, HkHSI.class);
        return response.getQuote();
    }

    public Quote quoteHkHSCEI() throws IOException, InterruptedException {
        var client = HttpClient.newBuilder().build();

        var responseBodyHandler = HttpResponse.BodyHandlers.ofString();
        var request = HttpRequest.newBuilder(URI.create("http://d.10jqka.com.cn/v6/time/hk_HSCEI/last.js"))
                .headers(ArrayUtils.listToArray(StockService.HEADERS, String.class))
                .GET()
                .build();

        var responseBody = client.send(request, responseBodyHandler).body();
        responseBody = HttpUtils.formatJson(responseBody);
        var response = JsonUtils.string2Object(responseBody, HkHSCEI.class);
        return response.getQuote();
    }

    public Quote quoteHkHSCCI() throws IOException, InterruptedException {
        var client = HttpClient.newBuilder().build();

        var responseBodyHandler = HttpResponse.BodyHandlers.ofString();
        var request = HttpRequest.newBuilder(URI.create("http://d.10jqka.com.cn/v6/time/hk_HSCCI/last.js"))
                .headers(ArrayUtils.listToArray(StockService.HEADERS, String.class))
                .GET()
                .build();

        var responseBody = client.send(request, responseBodyHandler).body();
        responseBody = HttpUtils.formatJson(responseBody);
        var response = JsonUtils.string2Object(responseBody, HkHSCCI.class);
        return response.getQuote();
    }

}
