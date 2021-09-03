package com.checkout.payments.beta;

import com.checkout.payments.beta.response.PaymentResponse;
import com.checkout.payments.beta.response.source.ResponseCardSource;
import com.checkout.payments.beta.response.source.ResponseIdSource;
import com.checkout.payments.beta.voids.VoidRequest;
import com.checkout.payments.beta.voids.VoidResponse;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class VoidPaymentsTestIT extends AbstractPaymentsTestIT {

    @Test
    void shouldVoidCardPayment() {

        final PaymentResponse<ResponseCardSource> paymentResponse = makeCardPayment(false);

        final VoidRequest voidRequest = VoidRequest.builder()
                .reference(UUID.randomUUID().toString())
                .build();

        final VoidResponse voidResponse = blocking(paymentsClient.voidPayment(paymentResponse.getId(), voidRequest));

        assertNotNull(voidResponse);
        assertNotNull(voidResponse.getActionId());
        assertNotNull(voidResponse.getReference());
        assertEquals(1, voidResponse.getLinks().size());

    }

    @Test
    void shouldVoidIdSourcePayment() {

        final PaymentResponse<ResponseIdSource> paymentResponse = makeIdSourcePayment();

        final VoidRequest voidRequest = VoidRequest.builder()
                .reference(UUID.randomUUID().toString())
                .build();

        final VoidResponse voidResponse = blocking(paymentsClient.voidPayment(paymentResponse.getId(), voidRequest));

        assertNotNull(voidResponse);
        assertNotNull(voidResponse.getActionId());
        assertNotNull(voidResponse.getReference());
        assertEquals(1, voidResponse.getLinks().size());

    }

    @Test
    void shouldVoidTokenSourcePayment() {

        final PaymentResponse<ResponseCardSource> paymentResponse = makeTokenPayment();

        final VoidRequest voidRequest = VoidRequest.builder()
                .reference(UUID.randomUUID().toString())
                .build();

        final VoidResponse voidResponse = blocking(paymentsClient.voidPayment(paymentResponse.getId(), voidRequest));

        assertNotNull(voidResponse);
        assertNotNull(voidResponse.getActionId());
        assertNotNull(voidResponse.getReference());
        assertEquals(1, voidResponse.getLinks().size());

    }

}
