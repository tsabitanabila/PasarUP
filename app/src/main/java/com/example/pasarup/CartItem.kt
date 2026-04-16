package com.example.pasarup

data class CartItem(
    val id: String,
    val nama: String,
    val harga: Int,
    val gambarResId: Int,
    var jumlah: Int = 1,
    val tokoId: String
)

data class Order(
    val id: String,
    val items: List<CartItem>,
    val totalHarga: Int,
    val status: String,
    val tanggal: String
)

object CartManager {
    val items = mutableListOf<CartItem>()
    val orders = mutableListOf<Order>()

    fun addItem(item: CartItem) {
        val existingItem = items.find { it.id == item.id }
        if (existingItem != null) {
            existingItem.jumlah += 1
        } else {
            items.add(item.copy())
        }
    }

    fun getTotalHarga(): Int {
        return items.sumOf { it.harga * it.jumlah }
    }

    fun checkout() {
        if (items.isNotEmpty()) {
            val newOrder = Order(
                id = "ORD-${System.currentTimeMillis()}",
                items = ArrayList(items),
                totalHarga = getTotalHarga() + 12000, // + ongkir
                status = "Diproses",
                tanggal = "Hari ini"
            )
            orders.add(newOrder)
            items.clear()
        }
    }
}