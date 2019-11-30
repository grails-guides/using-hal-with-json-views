package com.example

import java.text.SimpleDateFormat

class BootStrap {

    def init = { servletContext ->
        log.info "Loading database..."

        def (Category clothing, Category furniture, Category tools) = fixtureCategories()

        def products = fixtureProducts(clothing, furniture, tools)

        def customers = fixtureCustomers()

        fixtureOrders(products, customers)

    }

    static List<Order> fixtureOrders(List<Product> products, List<Customer> customers) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss')
        def orders = [
                [orderId: "0A12321", shippingCost: 13.54, products: [products[5], products[0], products[10]], customer: customers[0], shippingAddress: customers[0].address, orderPlaced: simpleDateFormat.parse('2017-02-08 10:10:36')],
                [orderId: "0A16546", shippingCost: 9.19, products: [products[1], products[6], products[3]], customer: customers[0], shippingAddress: customers[0].address],
                [orderId: "0A27345", shippingCost: 14.60, products: [products[7], products[8]], customer: customers[1], shippingAddress: customers[1].address],
                [orderId: "0A78129", shippingCost: 3.82, products: [products[4]], customer: customers[2], shippingAddress: customers[2].address],
                [orderId: "0F35439", shippingCost: 13.40, products: [products[11], products[12]], customer: customers[3], shippingAddress: customers[3].address],
                [orderId: "0F35523", shippingCost: 6.00, products: [products[11], products[0]], customer: customers[3], shippingAddress: new Address(street: '93 Harvey Blvd', state: State.AZ, city: "Phoenix", zip: 892342)]
        ].collect { new Order(it) }
        orders.collect { order ->
            Order.withTransaction {
                order.save()
            }
        }
    }

    static List<Customer> fixtureCustomers() {
        def customers = [
                [firstName: "Peter", lastName: "River", address: new Address(street: '321 Arrow Ln',
                        state: State.IL,
                        city: "Chicago",
                        zip: 646465)],
                [firstName: "Ann", lastName: "Hughes", address: new Address(street: '76 Sage Ave',
                        state: State.MA,
                        city: "Boston",
                        zip: 989896)],
                [firstName: "Rogelio", lastName: "Alvarado", address: new Address(street: '93 Jeffers Dr',
                        state: State.TN,
                        city: "Memphis",
                        zip: 754543)],
                [firstName: "Leona", lastName: "Pittman", address: new Address(street: '2 Fort Meyers Circle',
                        state: State.AZ,
                        city: "Tucson",
                        zip: 865427)]
        ].collect { new Customer(it)}
        customers.collect { customer ->
            Customer.withTransaction {
                customer.save()
            }
        }
    }

    static List<Product> fixtureProducts(Category clothing,
                                         Category furniture,
                                         Category tools) {
        def products = [
                [name: 'Cargo Pants', inventoryId: 'CLOTH001', price: 15.00, category: clothing],
                [name: 'Sweater', inventoryId: 'CLOTH002', price: 12.00, category: clothing],
                [name: 'Jeans', inventoryId: 'CLOTH003', price: 15.00, category: clothing],
                [name: 'Blouse', inventoryId: 'CLOTH004', price: 18.00, category: clothing],
                [name: 'T-Shirt', inventoryId: 'CLOTH005', price: 10.00, category: clothing],
                [name: 'Jacket', inventoryId: 'CLOTH006', price: 20.00, category: clothing],
                [name: 'Bookcase', inventoryId: 'FURN001', price: 40.00, category: furniture],
                [name: 'Coffee Table', inventoryId: 'FURN002', price: 50.00, category: furniture],
                [name: 'Vanity', inventoryId: 'FURN003', price: 90.00, category: furniture],
                [name: 'Table Saw', inventoryId: 'TOOL001', price: 120.00, category: tools],
                [name: 'Router', inventoryId: 'TOOL002', price: 90.00, category: tools],
                [name: 'Block Plane', inventoryId: 'TOOL003', price: 50.00, category: tools],
                [name: 'Chisel', inventoryId: 'TOOL004', price: 22.00, category: tools]
        ].collect { new Product(it) }
        products.collect { product ->
            Product.withTransaction {
                product.save()
            }
        }
    }

    static List<Category> fixtureCategories() {
        List<Category> categories = ['Clothing', 'Furniture', 'Tools'].collect {
            new Category(name: it)
        }
        categories.collect { category ->
            Category.withTransaction {
                category.save()
            }
        }
    }

    def destroy = {
    }
}
