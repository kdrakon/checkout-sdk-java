package com.checkout.beta;

import com.checkout.customers.beta.CustomersClient;
import com.checkout.disputes.beta.DisputesClient;
import com.checkout.instruments.beta.InstrumentsClient;
import com.checkout.payments.beta.PaymentsClient;
import com.checkout.risk.RiskClient;
import com.checkout.tokens.beta.TokensClient;
import com.checkout.workflows.beta.WorkflowsClient;

public interface CheckoutApi {

    TokensClient tokensClient();

    PaymentsClient paymentsClient();

    CustomersClient customersClient();

    DisputesClient disputesClient();

    InstrumentsClient instrumentsClient();

    RiskClient riskClient();

    WorkflowsClient workflowsClient();

}

