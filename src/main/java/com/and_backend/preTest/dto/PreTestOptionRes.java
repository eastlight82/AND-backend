package com.and_backend.preTest.dto;

import com.and_backend.preTest.PreTestOpt;

public record PreTestOptionRes(Long optionId, String text) {
    public static PreTestOptionRes from(PreTestOpt o){ return new PreTestOptionRes(o.getPreTestOptId(), o.getText()); }
}