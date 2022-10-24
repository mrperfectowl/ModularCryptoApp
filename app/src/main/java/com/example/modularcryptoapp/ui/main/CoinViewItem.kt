package com.example.modularcryptoapp.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.AttrRes
import androidx.cardview.widget.CardView
import com.example.domain.model.Coin
import com.example.modularcryptoapp.databinding.ItemCoinBinding
import com.example.modularcryptoapp.ui.adapter.ViewItem

class CoinViewItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
) : CardView(context, attrs, defStyleAttr) {

    private val layout = ItemCoinBinding.inflate(
        LayoutInflater.from(context),
        this
    )

    init {
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(8, 8, 8, 8)
        }
        radius = 8f
        cardElevation = 2f
    }

    fun bind(
        data: Coin?,
    ) {
        data?.let { item ->
            layout.tvCoinName.text = item.name
            layout.tvCoinRank.text = item.rank.toString()
        }
    }

    companion object {
        fun toViewItem(
            data: Coin?,
        ): ViewItem<CoinViewItem> {
            return ViewItem(
                builder = {
                    CoinViewItem(it)
                },
                binder = {
                    it.bind(data)
                },
                type = CoinViewItem::class.hashCode()
            )
        }
    }
}