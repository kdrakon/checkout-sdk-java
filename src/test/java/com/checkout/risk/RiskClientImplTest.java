package com.checkout.risk;

import com.checkout.ApiClient;
import com.checkout.ApiCredentials;
import com.checkout.CheckoutConfiguration;
import com.checkout.risk.preauthentication.PreAuthenticationAssessmentRequest;
import com.checkout.risk.preauthentication.PreAuthenticationAssessmentResponse;
import com.checkout.risk.precapture.PreCaptureAssessmentRequest;
import com.checkout.risk.precapture.PreCaptureAssessmentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.checkout.TestHelper.mockDefaultConfiguration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RiskClientImplTest {

    private static final String PRE_AUTHENTICATION_PATH = "risk/assessments/pre-authentication";
    private static final String PRE_CAPTURE_PATH = "risk/assessments/pre-authentication";

    @Mock
    private ApiClient apiClient;

    private RiskClient riskClient;

    @BeforeEach
    public void setup() {
        final CheckoutConfiguration checkoutConfiguration = mockDefaultConfiguration();
        this.riskClient = new RiskClientImpl(apiClient, checkoutConfiguration);
    }

    @Test
    public void shouldRequestPreAuthenticationRiskScan() throws ExecutionException, InterruptedException {

        final PreAuthenticationAssessmentRequest request = mock(PreAuthenticationAssessmentRequest.class);
        final PreAuthenticationAssessmentResponse response = mock(PreAuthenticationAssessmentResponse.class);

        when(apiClient.postAsync(eq(PRE_AUTHENTICATION_PATH), any(ApiCredentials.class), eq(PreAuthenticationAssessmentResponse.class), eq(request), isNull()))
                .thenReturn(CompletableFuture.completedFuture(response));

        final CompletableFuture<PreAuthenticationAssessmentResponse> preAuthenticationAssessmentResponse = riskClient.requestPreAuthenticationRiskScan(request);

        assertNotNull(preAuthenticationAssessmentResponse.get());
        assertEquals(response, preAuthenticationAssessmentResponse.get());

    }

    @Test
    public void shouldRequestPreCaptureRiskScan() throws ExecutionException, InterruptedException {

        final PreCaptureAssessmentRequest request = mock(PreCaptureAssessmentRequest.class);
        final PreCaptureAssessmentResponse response = mock(PreCaptureAssessmentResponse.class);

        when(apiClient.postAsync(eq(PRE_CAPTURE_PATH), any(ApiCredentials.class), eq(PreCaptureAssessmentResponse.class), eq(request), isNull()))
                .thenReturn(CompletableFuture.completedFuture(response));

        final CompletableFuture<PreCaptureAssessmentResponse> responseCompletableFuture = riskClient.requestPreCaptureRiskScan(request);

        assertNotNull(responseCompletableFuture.get());
        assertEquals(response, responseCompletableFuture.get());

    }

}