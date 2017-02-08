package com.example

import grails.plugin.json.view.test.JsonViewTest
import grails.test.hibernate.HibernateSpec
import org.skyscreamer.jsonassert.JSONAssert

class ProductShowSpec extends HibernateSpec implements JsonViewTest {

    def "test _embedded appear in JSON"() {
        setup:
        def (Category clothing, Category furniture, Category tools) = BootStrap.fixtureCategories()
        def products = BootStrap.fixtureProducts(clothing, furniture, tools)
        def customers = BootStrap.fixtureCustomers()
        def orders  = BootStrap.fixtureOrders(products, customers)

        when:
        def product = products.first()
        def result = render(view: "/product/show", model:[product: product])
        def expectedJsonString = '''
{
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/products/1",
      "hreflang": "en",
      "type": "application/hal+json"
    }
  },
  "_embedded": {
    "category": {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/categories/1",
          "hreflang": "en",
          "type": "application/hal+json"
        }
      },
      "name": "Clothing",
      "version": 0
    }
  },
  "id": "CLOTH001",
  "name": "Cargo Pants",
  "price": 15.00
}
'''
        JSONAssert.assertEquals(expectedJsonString, result.jsonText, true)

        then:
        result.json._embedded

        and:
        notThrown AssertionError
    }
}
