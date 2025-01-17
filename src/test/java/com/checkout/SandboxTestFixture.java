package com.checkout;

import com.checkout.four.CheckoutApi;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class SandboxTestFixture {

    protected static final String SELF = "self";

    protected com.checkout.CheckoutApi defaultApi;
    protected CheckoutApi fourApi;

    public SandboxTestFixture(final PlatformType platformType) {
        switch (platformType) {
            case DEFAULT:
                this.defaultApi = CheckoutSdk.defaultSdk()
                        .staticKeys()
                        .publicKey(requireNonNull(System.getenv("CHECKOUT_PUBLIC_KEY")))
                        .secretKey(requireNonNull(System.getenv("CHECKOUT_SECRET_KEY")))
                        .environment(Environment.SANDBOX)
                        .build();
                break;
            case FOUR:
                this.fourApi = CheckoutSdk.fourSdk()
                        .staticKeys()
                        .publicKey(requireNonNull(System.getenv("CHECKOUT_FOUR_PUBLIC_KEY")))
                        .secretKey(requireNonNull(System.getenv("CHECKOUT_FOUR_SECRET_KEY")))
                        .environment(Environment.SANDBOX)
                        .build();
                break;
            case FOUR_OAUTH:
                this.fourApi = CheckoutSdk.fourSdk()
                        .oAuth()
                        .clientCredentials(
                                requireNonNull(System.getenv("CHECKOUT_FOUR_OAUTH_CLIENT_ID")),
                                requireNonNull(System.getenv("CHECKOUT_FOUR_OAUTH_CLIENT_SECRET")))
                        .environment(Environment.SANDBOX)
                        .enableFilesApi(Environment.SANDBOX)
                        .build();

                break;
        }
    }

    protected <T> T blocking(final CompletableFuture<T> future) {
        try {
            return future.get();
        } catch (final Exception e) {
            assertTrue(e.getCause() instanceof CheckoutApiException);
            final CheckoutApiException checkoutException = (CheckoutApiException) e.getCause();
            fail(checkoutException);
        }
        return null;
    }

    protected <T> void assertNotFound(final CompletableFuture<T> future) {
        try {
            future.get();
            fail();
        } catch (final Exception e) {
            assertTrue(e.getCause() instanceof CheckoutApiException);
            assertEquals("The API response status code (" + 404 + ") does not indicate success.", e.getCause().getMessage());
        }
    }

    /**
     * Take a quick nap
     */
    protected void nap() {
        nap(2);
    }

    /**
     * Take a custom nap
     */
    protected void nap(final int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (final InterruptedException ignore) {
            fail();
        }
    }

}
