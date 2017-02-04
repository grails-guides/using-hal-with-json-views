package com.example

import grails.rest.*

@Resource(readOnly = true, formats = ['json', 'xml'])
class Product {

    String name
    String inventoryId

    BigDecimal price

    static belongsTo = [ category : Category ]

}
