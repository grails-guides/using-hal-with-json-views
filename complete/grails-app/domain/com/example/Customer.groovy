package com.example

class Customer {

    String firstName
    String lastName

    Address address

    static hasMany = [ orders: Order ]

    static constraints = {
        orders nullable: true
    }


    String getFullName() {
        return "${firstName} ${lastName}"
    }
}
