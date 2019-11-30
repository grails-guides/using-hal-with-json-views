package com.example

import grails.testing.mixin.integration.Integration
import grails.testing.spock.OnceBefore
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import spock.lang.Specification

@Integration
class DeleteEndpointNotBeingGeneratedSpec extends Specification {
    BlockingHttpClient client

    @OnceBefore
    void init() {
        String baseUrl = "http://localhost:$serverPort"
        this.client  = HttpClient.create(baseUrl.toURL()).toBlocking()
    }

    def "test Grails does not generate endpoints for update/create/delete operations at Domain classes annotated with @Resource(readOnly=true)"() {
        when:
        HttpRequest request = HttpRequest.DELETE('/api/order/1')
        client.exchange(request)

        then:
        HttpClientResponseException e = thrown()
        e.status == HttpStatus.NOT_FOUND
    }
}
