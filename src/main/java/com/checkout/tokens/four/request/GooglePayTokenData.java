package com.checkout.tokens.four.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public final class GooglePayTokenData {

    private String signature;

    private String protocolVersion;

    private String signedMessage;

}
