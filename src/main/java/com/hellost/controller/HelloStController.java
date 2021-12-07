package com.hellost.controller;


import com.hellost.resource.StockResource;
import com.hellost.service.QuoteService;
import com.hellost.service.StockService;
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.scheduler.model.anno.Scheduler;
import com.zfoo.scheduler.util.TimeUtils;
import com.zfoo.storage.model.anno.ResInjection;
import com.zfoo.storage.model.vo.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;

@Component
public class HelloStController {

    private static final Logger logger = LoggerFactory.getLogger(HelloStController.class);

    @ResInjection
    private Storage<String, StockResource> stockResources;

    @Autowired
    private QuoteService quoteService;
    @Autowired
    private StockService stockService;

    @Scheduler(cron = "0 * * * * ?")
    public void cronQuotePM() throws ParseException, IOException, InterruptedException {
        var simpleDateStr = TimeUtils.dateFormatForDayString(TimeUtils.now());
        var startTime = TimeUtils.stringToDate(StringUtils.format("{} 09:30:00", simpleDateStr)).getTime();
        var endTime = TimeUtils.stringToDate(StringUtils.format("{} 11:30:00", simpleDateStr)).getTime();
        if (!TimeUtils.timeIn(TimeUtils.now(), startTime, endTime)) {
            return;
        }
        quote();
    }

    @Scheduler(cron = "0 * * * * ?")
    public void cronQuoteAM() throws ParseException, IOException, InterruptedException {
        var simpleDateStr = TimeUtils.dateFormatForDayString(TimeUtils.now());
        var startTime = TimeUtils.stringToDate(StringUtils.format("{} 13:00:00", simpleDateStr)).getTime();
        var endTime = TimeUtils.stringToDate(StringUtils.format("{} 15:00:00", simpleDateStr)).getTime();
        if (!TimeUtils.timeIn(TimeUtils.now(), startTime, endTime)) {
            return;
        }
        quote();
    }

    @Scheduler(cron = "0/10 * * * * ?")
    public void cronTest() throws ParseException, IOException, InterruptedException {
        quote();
    }

    private void quote() throws IOException, InterruptedException {
        // 打印行情数据
        var quoteHs1A0001 = quoteService.quoteHs1A0001();
        var quoteHs399001 = quoteService.quoteHs399001();
        var quoteHs399006 = quoteService.quoteHs399006();
        var quoteHkHSI = quoteService.quoteHkHSI();
        var quoteHkHSCEI = quoteService.quoteHkHSCEI();
        var quoteHkHSCCI = quoteService.quoteHkHSCCI();
        logger.info("-----------------------------");
        logger.info("hs{}{}{}{}{}{}", StringUtils.TAB_ASCII, quoteHs1A0001.increaseRatio(), StringUtils.TAB_ASCII, quoteHs399001.increaseRatio(), StringUtils.TAB_ASCII, quoteHs399006.increaseRatio());
        logger.info("hk{}{}{}{}{}{}", StringUtils.TAB_ASCII, quoteHkHSI.increaseRatio(), StringUtils.TAB_ASCII, quoteHkHSCEI.increaseRatio(), StringUtils.TAB_ASCII, quoteHkHSCCI.increaseRatio());

        // 打印个股数据
        for (var stockResource : stockResources.getAll()) {
            var code = stockResource.getCode();
            var nick = stockResource.getNick();
            var fiveRange = stockService.fiveRange(code);
            logger.info("{}{}{}", nick, StringUtils.TAB_ASCII, fiveRange.increaseRatio());
        }
    }


}
