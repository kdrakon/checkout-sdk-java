package com.checkout.payments.links;

import com.checkout.ApiClient;
import com.checkout.CheckoutConfiguration;
import com.checkout.payments.AbstractClient;

import java.util.concurrent.CompletableFuture;

public class PaymentLinksClientImpl extends AbstractClient implements PaymentLinksClient {

    public static final String PAYMENT_LINKS = "/payment-links";

    public PaymentLinksClientImpl(final ApiClient apiClient, final CheckoutConfiguration configuration) {
        super(apiClient, configuration);
    }

    @Override
    public CompletableFuture<PaymentLinkDetailsResponse> getAsync(final String reference) {
        return apiClient.getAsync(PAYMENT_LINKS + "/" + reference, credentials, PaymentLinkDetailsResponse.class);
    }

    @Override
    public CompletableFuture<PaymentLinkResponse> createAsync(final PaymentLinkRequest paymentLinkRequest) {
        return apiClient.postAsync(PAYMENT_LINKS, credentials, PaymentLinkResponse.class, paymentLinkRequest, null);
    }
}
