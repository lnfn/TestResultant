package com.eugenetereshkov.testresultant.ui.currencylist

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.eugenetereshkov.testresultant.R
import com.eugenetereshkov.testresultant.entity.Currency
import com.eugenetereshkov.testresultant.extension.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_currency.*


class CurrencyListAdapter : ListAdapter<Currency, CurrencyListAdapter.ViewHolder>(diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(R.layout.item_currency))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
            override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: Currency) {
            textViewName.text = item.name
            textViewVolume.text = item.volume.toString()
            textViewAmount.text = item.price.amount.toString()
        }
    }
}

val diff = object : DiffUtil.ItemCallback<Currency>() {
    override fun areItemsTheSame(oldItem: Currency, newItem: Currency) = oldItem === newItem
    override fun areContentsTheSame(oldItem: Currency, newItem: Currency) = oldItem == newItem
}
