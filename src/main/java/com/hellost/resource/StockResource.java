package com.hellost.resource;

import com.zfoo.storage.model.anno.Id;
import com.zfoo.storage.model.anno.Resource;

@Resource
public class StockResource {

    @Id
    private String code;

    private String nick;

    public String getCode() {
        return code;
    }

    public String getNick() {
        return nick;
    }
}
