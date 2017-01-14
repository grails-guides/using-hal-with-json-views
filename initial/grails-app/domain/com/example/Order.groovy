package com.example

import grails.rest.*

@Resource(readOnly = false, formats = ['json', 'xml'])
class Order {

    String orderId

    BigDecimal shippingCost

    static hasMany = [products: Product]

    static belongsTo = [ customer: Customer ]

    static constraints = {
    }


    BigDecimal getTotalPrice() {
        return shippingCost + products*.price.sum()
    }
}
