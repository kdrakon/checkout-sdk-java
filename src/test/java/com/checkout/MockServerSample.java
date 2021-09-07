package com.checkout;

import com.checkout.common.IdResponse;
import com.checkout.customers.CustomerRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.logging.MockServerLogger;
import org.mockserver.matchers.Times;
import org.mockserver.model.Header;
import org.mockserver.socket.PortFactory;
import org.mockserver.socket.tls.KeyStoreFactory;

import javax.net.ssl.HttpsURLConnection;
import java.util.concurrent.ExecutionException;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.Cookie.cookie;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockServerExtension.class)
class MockServerSample extends SandboxTestFixture {

    MockServerSample() {
        super(PlatformType.DEFAULT);
    }

    private MockServerClient mockServer;

    private static int freePort;

    @BeforeEach
    public void setUp() {
        HttpsURLConnection.setDefaultSSLSocketFactory(new KeyStoreFactory(new MockServerLogger()).sslContext().getSocketFactory());
        //mockServer = ClientAndServer.startClientAndServer(PortFactory.findFreePort());
        freePort = PortFactory.findFreePort();
        mockServer = ClientAndServer.startClientAndServer(1080);
        expectation();
    }

    @Test
    void test() throws ExecutionException, InterruptedException {
        CustomerRequest request = CustomerRequest.builder().build();
        IdResponse idResponse = getApi().customersClient().create(request).get();
        System.out.println("idResponse = " + idResponse);
    }

    private static void expectation() {
        new MockServerClient("localhost", 1080)
                .withSecure(true)
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/customers")
                )
                .respond(
                        response()
                                .withBody("{\n" +
                                        "\"id\": \"cus_y3oqhf46pyzuxjbcn2giaqnb44\"\n" +
                                        "}")
                );
    }


    @AfterEach
    public void stopServer() {
        mockServer.stop();
    }
}
