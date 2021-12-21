package ru.zoo.presentation.tables.rations.listDirectory

import ru.zoo.data.models.Ration

class RationsRepository {
    companion object{
        var rationsTemp = ArrayList<Ration>()
        fun addRation(ration: Ration) {
            if (checkedRation.any { it.id == ration.id }) {
                checkedRation.removeAll { it.id == ration.id }
            } else {
                checkedRation.add(ration)
            }
        }
        var rations = ArrayList<Ration>()
        var redRations = ArrayList<Ration>()
        var checkedRation = ArrayList<Ration>()

        var requestCode = 0
        var isMultiple = false

        fun clear() {
            rations.clear()
            redRations.clear()
            rationsTemp.clear()
        }
    }
}