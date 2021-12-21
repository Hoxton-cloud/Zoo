package ru.zoo.presentation.tables.animals.listDirectory.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.include_participant.view.*
import kotlinx.android.synthetic.main.list_item_simple_card.view.*
import kotlinx.android.synthetic.main.list_item_simple_card.view.contractor_representative
import ru.zoo.data.models.Animal

class AnimalsHolder (v: View) : RecyclerView.ViewHolder(v) {
    fun bindItem(
        animal: Animal,
        checkedAnimal: ArrayList<Animal>,
        onClick : ((animal: Animal) -> Unit),
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

        name.text = animal.name
        itemView.setOnClickListener { onClick.invoke(animal) }
    }
}