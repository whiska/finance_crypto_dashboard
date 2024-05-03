package com.whiska.finance_crypto_dashboard.domain

data class Domain(val name: String = "", val symbol: String = "", val price: Double = 0.0, val changePercent: Double = 0.0,
                  val lineData: ArrayList<Int> = ArrayList<Int>(), val propertyAmount: Double = 0.0, val propertySize: Double = 0.0, val resourceId: Int = 0) {

}