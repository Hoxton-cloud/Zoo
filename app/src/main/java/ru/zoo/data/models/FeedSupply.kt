package ru.zoo.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class FeedSupply : Serializable {
    @SerializedName("id")
    var id = 0
    @SerializedName("quantity")
    var quantity = ""
    @SerializedName("price")
    var price = ""
    @SerializedName("feedID")
    var feedID = 0
    @SerializedName("supplierID")
    var supplierID = 0

    constructor()
    constructor(id: Int, quantity: String, price: String, feedID: Int, supplierID: Int) {
        this.id = id
        this.quantity = quantity
        this.price = price
        this.feedID = feedID
        this.supplierID = supplierID
    }

}