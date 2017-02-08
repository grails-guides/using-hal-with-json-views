package com.example

import grails.plugins.rest.client.RestBuilder
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Specification

@Integration
@Rollback
class DeleteEndpointNotBeingGeneratedSpec extends Specification {

    def "test Grails does not generate endpoints for update/create/delete operations at Domain classes annotated with @Resource(readOnly=true)"() {
        given:
        RestBuilder rest = new RestBuilder()

        when:
        def resp = rest.delete("http://localhost:${serverPort}/api/order/1") {
            header("Accept", "application/json")
        }

        then:
        resp.status == 404
    }
}
