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
import com.whiska.finance_crypto_dashboard.domain.Domain
import java.text.DecimalFormat

class CryptoAdapter(val dataList: ArrayList<Domain>) : RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {

//    private val dataList = ArrayList<Domain>()


//    fun setData(data: List<Domain>) {
//        dataList.clear()
//        dataList.addAll(data)
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewBinding = ViewholderCryptoBinding.inflate(inflater)
        viewBinding.root.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: CryptoAdapter.ViewHolder, position: Int) {
        val item = dataList[position]
        holder.viewBinding.apply {
            cryptoNameLbl.text = item.name
            cryptoPriceLbl.text = root.context.resources.getString(R.string.dollar_sign, item.price)
            cryptoChangePercentLbl.text = root.context.resources.getString(R.string.change_percent, item.changePercent)
            propertySizeLbl.text = root.context.resources.getString(R.string.property_size, item.propertySize, item.symbol)
            propertyValueLbl.text = root.context.resources.getString(R.string.dollar_sign, item.propertyAmount)
            lineChart.setData(item.lineData)

            if(item.changePercent >= 0) {
                cryptoChangePercentLbl.setTextColor(Color.parseColor("#12c737"))
                lineChart.sparkLineColor = Color.parseColor("#12c737")
            } else if(item.changePercent < 0){
                cryptoChangePercentLbl.setTextColor(Color.parseColor("#fc0000"))
                lineChart.sparkLineColor = Color.parseColor("#fc0000")
            } else {
                cryptoChangePercentLbl.setTextColor(Color.parseColor("#ffffff"))
                lineChart.sparkLineColor = Color.parseColor("#ffffff")
            }
            val drawable = ResourcesCompat.getDrawable(root.context.resources, item.resourceId, root.context.theme)
            Glide.with(root.context).load(drawable).into(cryptoImg)
        }


    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(val viewBinding: ViewholderCryptoBinding): RecyclerView.ViewHolder(viewBinding.root) {

    }
}