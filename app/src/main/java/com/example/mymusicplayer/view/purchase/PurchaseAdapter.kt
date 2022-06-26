package com.example.mymusicplayer.view.purchase

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mymusicplayer.R
import com.example.mymusicplayer.domain.purchase.PeriodType
import com.example.mymusicplayer.domain.purchase.ProductEntity

class PurchaseAdapter(
    data: List<ProductEntity>,
    private val onItemClick: (ProductEntity) -> Unit,
    private val applicationContext: Context
) :
    RecyclerView.Adapter<PurchaseViewHolder>() {

    var data: List<ProductEntity> = data
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.purchase_element, parent, false)
        return PurchaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        val cell = data[position]

        holder.purchaseType.apply {
            text = cell.priceLocal
            setOnClickListener {

                onItemClick(cell)
                when (cell.periodType) {
                    PeriodType.MONTH -> Toast.makeText(
                        applicationContext,
                        "Month subscription activated",
                        Toast.LENGTH_SHORT
                    ).show()


                    PeriodType.YEAR -> Toast.makeText(
                        applicationContext,
                        "Year subscription activated",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
