package com.example

import grails.testing.mixin.integration.Integration
import grails.testing.spock.OnceBefore
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.http.uri.UriBuilder
import org.skyscreamer.jsonassert.JSONAssert
import spock.lang.Specification

@Integration
class OrderShowV2IntegrationSpec extends Specification {
    BlockingHttpClient client

    @OnceBefore
    void init() {
        String baseUrl = "http://localhost:$serverPort"
        this.client  = HttpClient.create(baseUrl.toURL()).toBlocking()
    }

    def "test _links appear in JSON"() {

        when:
        HttpRequest request = HttpRequest.GET(UriBuilder.of('/api/orders/v2/1')
                .queryParam('lang', 'en')
                .build())
        HttpResponse<String> resp = client.exchange(request, String)
        def expectedJsonString = """
        {
            _links: {
                self: {
                    href: "http://localhost:${serverPort}/api/orders?id=1",
                    hreflang: "en",
                    type: "application/hal+json"
                },
                customer: {
                    href: "http://localhost:${serverPort}/api/customers?id=1",
                    hreflang: "en",
                    type: "application/hal+json"
                }
            },
            id: "0A12321",
            shippingCost: 13.54,
            date: "2-08-2017",
            shippingAddress: {
                street: "321 Arrow Ln",
                street2: null,
                city: "Chicago",
                state: "IL",
                zip: 646465
            },
            products: [
                    {
                        id: 11
                    },
                    {
                        id: 1
                    },
                    {
                        id: 6
                    }
            ],
            customer: {
                id: 1
            }
        }
        """
        JSONAssert.assertEquals(expectedJsonString, resp.body(), false)

        then:
        notThrown AssertionError
    }
}
