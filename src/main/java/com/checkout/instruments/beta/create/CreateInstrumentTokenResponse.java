package com.checkout.instruments.beta.create;

import com.checkout.common.beta.CardCategory;
import com.checkout.common.beta.CardType;
import com.checkout.common.beta.CustomerResponse;
import com.checkout.common.beta.InstrumentType;
import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public final class CreateInstrumentTokenResponse extends CreateInstrumentResponse {

    private final InstrumentType type = InstrumentType.CARD;

    private String fingerprint;

    @SerializedName("expiry_month")
    private Integer expiryMonth;

    @SerializedName("expiry_year")
    private Integer expiryYear;

    private String scheme;

    private String last4;

    private String bin;

    @SerializedName("card_type")
    private CardType cardType;

    @SerializedName("card_category")
    private CardCategory cardCategory;

    private String issuer;

    @SerializedName("issuer_country")
    private String issuerCountry;

    @SerializedName("product_id")
    private String productId;

    @SerializedName("product_type")
    private String productType;

    @SerializedName("customer")
    private CustomerResponse customer;

}