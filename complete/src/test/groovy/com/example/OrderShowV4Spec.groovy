package com.example

import grails.plugin.json.view.test.JsonViewTest
import grails.test.hibernate.HibernateSpec
import org.skyscreamer.jsonassert.JSONAssert

class OrderShowV4Spec extends HibernateSpec implements JsonViewTest {

    def "test customer users _embedded appear in JSON"() {
        setup:
        mappingContext.addPersistentEntity(Customer)

        def (Category clothing, Category furniture, Category tools) = BootStrap.fixtureCategories()
        def products = BootStrap.fixtureProducts(clothing, furniture, tools)
        def customers = BootStrap.fixtureCustomers()
        def orders  = BootStrap.fixtureOrders(products, customers)

        when:
        def order = orders.first()
        def result = render(view: "/order/show_v4", model:[order: order])

        then:
        result.json._embedded
    }
}
