package com.example

import grails.rest.*

@Resource(readOnly = true, formats = ['json', 'xml'], uri='/api/products')
class Product {

    String name
    String inventoryId

    BigDecimal price

    static belongsTo = [ category : Category ]

}
