package com.example

import grails.rest.*

@Resource(readOnly = true, uri='/api/categories')
class Category {

    String name

    static hasMany = [ products : Product ]

    static constraints = {
        products nullable: true
    }
}