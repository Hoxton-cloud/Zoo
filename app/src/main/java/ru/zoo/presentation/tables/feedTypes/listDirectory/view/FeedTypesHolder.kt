package ru.zoo.presentation.tables.feedTypes.listDirectory.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.include_participant.view.*
import kotlinx.android.synthetic.main.list_item_simple_card.view.*
import kotlinx.android.synthetic.main.list_item_simple_card.view.contractor_representative
import ru.zoo.data.models.FeedType

class FeedTypesHolder (v: View) : RecyclerView.ViewHolder(v) {
    fun bindItem(
        feedType: FeedType,
        checkedFeedType: ArrayList<FeedType>,
        onClick : ((feedType: FeedType) -> Unit),
        lastItem:Boolean
    ) {
        val name = itemView.name
        val subName = itemView.sub_name
        val subTV = itemView.sub_tv
        val rectangle = itemView.rectangle

        val contractorRepresentative = itemView.contractor_representative
        val participantCompany = itemView.participant_company
        val participantName = itemView.participant_name
        val participantPhone = itemView.participant_phone

        name.text = feedType.title
        itemView.setOnClickListener { onClick.invoke(feedType) }
    }
}