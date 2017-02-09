package com.example

import grails.rest.RestfulController

class OrderController extends RestfulController<Order> {
    static responseFormats = ['json']

    OrderController() {
        super(Order)
    }

    def showv1() {
        respond queryForResource(params.id), view: 'show_v1'
    }

    def showv2() {
        respond queryForResource(params.id), view: 'show_v2'
    }

    def showv3() {
        respond queryForResource(params.id), view: 'show_v3'
    }

    def showv4() {
        respond queryForResource(params.id), view: 'show_v4'
    }
}
