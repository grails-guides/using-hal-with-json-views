package com.example

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
