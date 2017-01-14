package com.example

import grails.rest.*

@Resource(readOnly = true, formats = ['json', 'xml'])
class Address {

    String street
    String street2

    String city
    State state
    Integer zip

    static constraints = {
    }
}