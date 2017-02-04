package com.example

import grails.rest.*

@Resource(readOnly = true, uri='/api/order')
class Order {

    String orderId

    BigDecimal shippingCost

    Date orderPlaced = new Date()
    Address shippingAddress

    static hasMany = [products: Product]

    static belongsTo = [ customer: Customer ]

    static constraints = {
    }

    static mapping = {
        table 'CUS_ORDER'
    }


    BigDecimal getTotalPrice() {
        return shippingCost + products*.price.sum()
    }
}
