package com.whiska.finance_crypto_dashboard.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.whiska.finance_crypto_dashboard.R
import com.whiska.finance_crypto_dashboard.databinding.ViewholderCryptoBinding
import com.whiska.finance_crypto_dashboard.databinding.ViewholderStockBinding
import com.whiska.finance_crypto_dashboard.domain.Domain
import java.text.DecimalFormat

class StockAdapter(val dataList: ArrayList<Domain>) : RecyclerView.Adapter<StockAdapter.ViewHolder>() {

//    private val dataList = ArrayList<Domain>()


//    fun setData(data: List<Domain>) {
//        dataList.clear()
//        dataList.addAll(data)
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewBinding = ViewholderStockBinding.inflate(inflater)
        viewBinding.root.layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: StockAdapter.ViewHolder, position: Int) {
        val item = dataList[position]
        holder.viewBinding.apply {
            stockNameLbl.text = item.name
            stockPriceLbl.text = root.context.resources.getString(R.string.dollar_sign, item.price)
            stockChangePercentLbl.text = root.context.resources.getString(R.string.change_percent, item.changePercent)
            lineChart.setData(item.lineData)
            
            if(item.changePercent >= 0) {
                stockChangePercentLbl.setTextColor(Color.parseColor("#12c737"))
                lineChart.sparkLineColor = Color.parseColor("#12c737")
            } else if(item.changePercent < 0){
                stockChangePercentLbl.setTextColor(Color.parseColor("#fc0000"))
                lineChart.sparkLineColor = Color.parseColor("#fc0000")
            } else {
                stockChangePercentLbl.setTextColor(Color.parseColor("#ffffff"))
                lineChart.sparkLineColor = Color.parseColor("#ffffff")
            }
            val drawable = ResourcesCompat.getDrawable(root.context.resources, item.resourceId, root.context.theme)
            Glide.with(root.context).load(drawable).into(stockImg)

            if(position == 0) {
                root.setBackgroundResource(R.drawable.purple_bg)
                stockNameLbl.setTextColor(Color.parseColor("#ffffff"))
                stockPriceLbl.setTextColor(Color.parseColor("#ffffff"))
                stockChangePercentLbl.setTextColor(Color.parseColor("#ffffff"))
            }
        }


    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(val viewBinding: ViewholderStockBinding): RecyclerView.ViewHolder(viewBinding.root) {

    }
}