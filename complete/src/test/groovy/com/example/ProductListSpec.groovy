package com.example

import grails.plugin.json.view.test.JsonViewTest
import grails.test.hibernate.HibernateSpec
import org.skyscreamer.jsonassert.JSONAssert

class ProductListSpec extends HibernateSpec implements JsonViewTest {

    def "test pagination links appear in JSON"() {
        setup:
        def (Category clothing, Category furniture, Category tools) = BootStrap.fixtureCategories()
        def products = BootStrap.fixtureProducts(clothing, furniture, tools)
        def customers = BootStrap.fixtureCustomers()
        BootStrap.fixtureOrders(products, customers)

        when:
        int max = 10
        def result = render(view: "/product/index", model:[productList: products[0..<max],
                                                           productCount: products.size(),
                                                           max: max,
                                                           offset: 0,
                                                           sort: null,
                                                           order: null])
        def expectedJsonString = '''
{
  "_links": {
    "self": {
      "href": "http://localhost:8080/products?offset=0&max=10",
      "hreflang": "en",
      "type": "application/hal+json"
    },
    "first": {
      "href": "http://localhost:8080/products?offset=0&max=10",
      "hreflang": "en"
    },
    "next": {
      "href": "http://localhost:8080/products?offset=10&max=10",
      "hreflang": "en"
    },
    "last": {
      "href": "http://localhost:8080/products?offset=10&max=10",
      "hreflang": "en"
    }
  },
  "products": [
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/products/1",
          "hreflang": "en",
          "type": "application/hal+json"
        }
      },
      "name": "Cargo Pants",
      "id": "CLOTH001",
      "price": 15.00,
      "category": "Clothing"
    },
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/products/2",
          "hreflang": "en",
          "type": "application/hal+json"
        }
      },
      "name": "Sweater",
      "id": "CLOTH002",
      "price": 12.00,
      "category": "Clothing"
    },
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/products/3",
          "hreflang": "en",
          "type": "application/hal+json"
        }
      },
      "name": "Jeans",
      "id": "CLOTH003",
      "price": 15.00,
      "category": "Clothing"
    },
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/products/4",
          "hreflang": "en",
          "type": "application/hal+json"
        }
      },
      "name": "Blouse",
      "id": "CLOTH004",
      "price": 18.00,
      "category": "Clothing"
    },
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/products/5",
          "hreflang": "en",
          "type": "application/hal+json"
        }
      },
      "name": "T-Shirt",
      "id": "CLOTH005",
      "price": 10.00,
      "category": "Clothing"
    },
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/products/6",
          "hreflang": "en",
          "type": "application/hal+json"
        }
      },
      "name": "Jacket",
      "id": "CLOTH006",
      "price": 20.00,
      "category": "Clothing"
    },
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/products/7",
          "hreflang": "en",
          "type": "application/hal+json"
        }
      },
      "name": "Bookcase",
      "id": "FURN001",
      "price": 40.00,
      "category": "Furniture"
    },
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/products/8",
          "hreflang": "en",
          "type": "application/hal+json"
        }
      },
      "name": "Coffee Table",
      "id": "FURN002",
      "price": 50.00,
      "category": "Furniture"
    },
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/products/9",
          "hreflang": "en",
          "type": "application/hal+json"
        }
      },
      "name": "Vanity",
      "id": "FURN003",
      "price": 90.00,
      "category": "Furniture"
    },
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/products/10",
          "hreflang": "en",
          "type": "application/hal+json"
        }
      },
      "name": "Table Saw",
      "id": "TOOL001",
      "price": 120.00,
      "category": "Tools"
    }
  ],
  "count": 13,
  "max": 10,
  "offset": 0,
  "sort": null,
  "order": null
}
'''
        JSONAssert.assertEquals(expectedJsonString, result.jsonText, true)

        then:
        notThrown AssertionError
    }
}
