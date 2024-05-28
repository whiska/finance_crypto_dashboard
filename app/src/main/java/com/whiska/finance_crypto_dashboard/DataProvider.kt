package com.whiska.finance_crypto_dashboard

import com.whiska.finance_crypto_dashboard.domain.Domain

fun provideStockList(): List<Domain> {
    val data1 = ArrayList<Int>()
    val data2 = ArrayList<Int>()
    val data3 = ArrayList<Int>()

    data1.add(15000)
    data1.add(15900)
    data1.add(14500)
    data1.add(14000)

    data2.add(4900)
    data2.add(4950)
    data2.add(4800)
    data2.add(4750)

    data3.add(30000)
    data3.add(30100)
    data3.add(32000)
    data3.add(31500)
    data3.add(32200)

    val domains = ArrayList<Domain>()
    domains.add(Domain("NASDAQ100", "NASDAQ", 59999.12, 2.13, data1, 1245.12, 0.256, R.drawable.stock_1))
    domains.add(Domain("S&P 500", "S&P 500", 2500.12, -6.15, data2, 2666.33, 0.65, R.drawable.stock_2))
    domains.add(Domain("Dow Jones", "Dow Jones", 2585.12, 1.25, data3, 666.12, 0.147, R.drawable.stock_3))
    return domains
}

fun provideCryptoList(): List<Domain> {
    val data1 = ArrayList<Int>()
    val data2 = ArrayList<Int>()
    val data3 = ArrayList<Int>()

    data1.add(1000)
    data1.add(1100)
    data1.add(1200)
    data1.add(1300)

    data2.add(2100)
    data2.add(1900)
    data2.add(1700)
    data2.add(1650)

    data3.add(900)
    data3.add(1100)
    data3.add(1200)
    data3.add(1400)
    data3.add(1600)

    val domains = ArrayList<Domain>()
    domains.add(Domain("Bitcoin", "BTC", 59999.12, 2.13, data1, 1245.12, 0.256, R.drawable.bitcoin))
    domains.add(Domain("Etherium", "ETH", 2500.12, -6.15, data2, 2666.33, 0.65, R.drawable.etherium))
    domains.add(Domain("Trox", "ROX", 2585.12, 1.25, data3, 666.12, 0.147, R.drawable.trox))
    return domains
}