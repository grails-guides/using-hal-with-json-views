package com.example

import grails.rest.*

@Resource(readOnly = false, formats = ['json', 'xml'])
class Product {

    String name
    String inventoryId

    BigDecimal price

    static constraints = {
    }
}
