package com.hellost.model.exchange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jaysunxiao
 * @version 3.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Exchange {

    // 交易时间
    @JsonProperty("1")
    private String time;

    // 交易价格
    @JsonProperty("10")
    private String price;

    @JsonProperty("12")
    private String unknown12;

    // 交易数量
    @JsonProperty("49")
    private String num;

    @JsonProperty("625295")
    private String unknown625295;

    @JsonProperty("His")
    private String his;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUnknown12() {
        return unknown12;
    }

    public void setUnknown12(String unknown12) {
        this.unknown12 = unknown12;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getUnknown625295() {
        return unknown625295;
    }

    public void setUnknown625295(String unknown625295) {
        this.unknown625295 = unknown625295;
    }

    public String getHis() {
        return his;
    }

    public void setHis(String his) {
        this.his = his;
    }
}
