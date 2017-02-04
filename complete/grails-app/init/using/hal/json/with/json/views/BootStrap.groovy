package using.hal.json.with.json.views

import com.example.Address
import com.example.Category
import com.example.Customer
import com.example.Order
import com.example.Product
import com.example.State

class BootStrap {

    def init = { servletContext ->
        log.info "Loading database..."

        def clothing = new Category(name: "Clothing")
        def furniture = new Category(name: "Furniture")
        def tools = new Category(name: "Tools")

        [clothing, furniture, tools]*.save()

        def product1 = new Product(name: 'Cargo Pants', inventoryId: 'CLOTH001', price: 15.00, category: clothing)
        def product2 = new Product(name: 'Sweater', inventoryId: 'CLOTH002', price: 12.00, category: clothing)
        def product3 = new Product(name: 'Jeans', inventoryId: 'CLOTH003', price: 15.00, category: clothing)
        def product4 = new Product(name: 'Blouse', inventoryId: 'CLOTH004', price: 18.00, category: clothing)
        def product5 = new Product(name: 'T-Shirt', inventoryId: 'CLOTH005', price: 10.00, category: clothing)
        def product6 = new Product(name: 'Jacket', inventoryId: 'CLOTH006', price: 20.00, category: clothing)

        def product7 = new Product(name: 'Bookcase', inventoryId: 'FURN001', price: 40.00, category: furniture)
        def product8 = new Product(name: 'Coffee Table', inventoryId: 'FURN002', price: 50.00, category: furniture)
        def product9 = new Product(name: 'Vanity', inventoryId: 'FURN003', price: 90.00, category: furniture)

        def product10 = new Product(name: 'Table Saw', inventoryId: 'TOOL001', price: 120.00, category: tools)
        def product11 = new Product(name: 'Router', inventoryId: 'TOOL002', price: 90.00, category: tools)
        def product12 = new Product(name: 'Block Plane', inventoryId: 'TOOL003', price: 50.00, category: tools)
        def product13 = new Product(name: 'Chisel', inventoryId: 'TOOL004', price: 22.00, category: tools)

        [product1, product2, product3, product4, product5, product6, product7,
         product8, product9, product10, product11, product12, product13]*.save()


        def customer1 = new Customer(firstName: "Peter", lastName: "River", address: new Address(street: '321 Arrow Ln', state: State.IL, city: "Chicago", zip: 646465))
        def customer2 = new Customer(firstName: "Ann", lastName: "Hughes", address: new Address(street: '76 Sage Ave', state: State.MA, city: "Boston", zip: 989896))
        def customer3 = new Customer(firstName: "Rogelio", lastName: "Alvarado", address: new Address(street: '93 Jeffers Dr', state: State.TN, city: "Memphis", zip: 754543))
        def customer4 = new Customer(firstName: "Leona", lastName: "Pittman", address: new Address(street: '2 Fort Meyers Circle', state: State.AZ, city: "Tucson", zip: 865427))

        [customer1, customer2, customer3, customer4]*.save()

        def order1 = new Order(orderId: "0A12321", shippingCost: 13.54, products: [product6, product1, product11], customer: customer1, shippingAddress: customer1.address)
        def order2 = new Order(orderId: "0A16546", shippingCost: 9.19, products: [product2, product7, product4], customer: customer1, shippingAddress: customer1.address)
        def order3 = new Order(orderId: "0A27345", shippingCost: 14.60, products: [product8, product9], customer: customer2, shippingAddress: customer2.address)
        def order4 = new Order(orderId: "0A78129", shippingCost: 3.82, products: [product5], customer: customer3, shippingAddress: customer3.address)
        def order5 = new Order(orderId: "0F35439", shippingCost: 13.40, products: [product12, product13], customer: customer4, shippingAddress: customer4.address)
        def order6 = new Order(orderId: "0F35523", shippingCost: 6.00, products: [product12, product1], customer: customer4, shippingAddress: new Address(street: '93 Harvey Blvd', state: State.AZ, city: "Phoenix", zip: 892342))

        [order1, order2, order3, order4, order5, order6]*.save()

    }
    def destroy = {
    }
}
