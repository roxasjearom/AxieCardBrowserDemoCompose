package com.roxasjearom.axiecardbrowserdemo.compose.presentation.card

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.roxasjearom.axiecardbrowserdemo.compose.R
import com.roxasjearom.axiecardbrowserdemo.compose.databinding.ItemCardBinding
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.OriginCard
import javax.inject.Inject

class CardAdapter @Inject constructor() :
    RecyclerView.Adapter<CardAdapter.OriginCardViewHolder>() {

    private var axieCards = emptyList<OriginCard>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OriginCardViewHolder {
        val binding = ItemCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return OriginCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OriginCardViewHolder, position: Int) {
        val currentItem = axieCards[position]

        with(holder.binding) {
            val imageUrl = "https://cdn.axieinfinity.com/game/origin-cards/base/version-20220422/${currentItem.cardImage}"
            val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
            mainCard.load(imageUri) {
                placeholder(R.drawable.card_frame)
                error(R.drawable.card_frame_failed_to_load)
                crossfade(250)
                memoryCachePolicy(policy = CachePolicy.ENABLED)
            }
            cardName.text = currentItem.cardName

            cardDescription.text = currentItem.cardText
        }
    }

    override fun getItemCount(): Int {
        return axieCards.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCards(originCards: List<OriginCard>) {
        axieCards = originCards
        notifyDataSetChanged()
    }

    inner class OriginCardViewHolder(val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root)
}
