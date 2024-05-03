package com.whiska.finance_crypto_dashboard.activities

import android.os.Bundle

import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.whiska.finance_crypto_dashboard.R
import com.whiska.finance_crypto_dashboard.adapter.CryptoAdapter
import com.whiska.finance_crypto_dashboard.adapter.ItemDecoration
import com.whiska.finance_crypto_dashboard.adapter.StockAdapter
import com.whiska.finance_crypto_dashboard.databinding.LayoutMainActivityBinding
import com.whiska.finance_crypto_dashboard.domain.Domain

class MainActivity : FragmentActivity() {

    private lateinit var binding: LayoutMainActivityBinding

    private val data1 = ArrayList<Int>()
    private val data2 = ArrayList<Int>()
    private val data3 = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutMainActivityBinding.inflate(layoutInflater)
        setContentView(binding.main)
        setData()
        recyclerViewCrypto()
        recyclerViewStock()
    }

    private fun recyclerViewCrypto() {
        binding.cryptoListView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val domains = ArrayList<Domain>()
        domains.add(Domain("Bitcoin", "BTC", 59999.12, 2.13, data1, 1245.12, 0.256, R.drawable.bitcoin))
        domains.add(Domain("Etherium", "ETH", 2500.12, -6.15, data2, 2666.33, 0.65, R.drawable.etherium))
        domains.add(Domain("Trox", "ROX", 2585.12, 1.25, data3, 666.12, 0.147, R.drawable.trox))
        binding.cryptoListView.addItemDecoration(ItemDecoration(resources.getDimensionPixelSize(R.dimen.item_margin), LinearLayoutManager.VERTICAL))
        binding.cryptoListView.adapter = CryptoAdapter(domains)
    }

    private fun recyclerViewStock() {
        binding.stockListView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val domains = ArrayList<Domain>()
        domains.add(Domain("NASDAQ100", "NASDAQ", 59999.12, 2.13, data1, 1245.12, 0.256, R.drawable.stock_1))
        domains.add(Domain("S&P 500", "S&P 500", 2500.12, -6.15, data2, 2666.33, 0.65, R.drawable.stock_2))
        domains.add(Domain("Dow Jones", "Dow Jones", 2585.12, 1.25, data3, 666.12, 0.147, R.drawable.stock_3))
        binding.stockListView.addItemDecoration(ItemDecoration(resources.getDimensionPixelSize(R.dimen.item_margin), LinearLayoutManager.HORIZONTAL))
        binding.stockListView.adapter = StockAdapter(domains)
    }

    private fun setData() {
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
    }
}