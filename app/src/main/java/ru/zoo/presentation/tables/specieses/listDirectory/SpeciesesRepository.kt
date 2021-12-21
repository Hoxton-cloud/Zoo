package ru.zoo.presentation.tables.specieses.listDirectory

import ru.zoo.data.models.Species

class SpeciesesRepository {
    companion object{
        var speciesesTemp = ArrayList<Species>()
        fun addSpecies(species: Species) {
            if (checkedSpecies.any { it.id == species.id }) {
                checkedSpecies.removeAll { it.id == species.id }
            } else {
                checkedSpecies.add(species)
            }
        }
        var specieses = ArrayList<Species>()
        var redSpecieses = ArrayList<Species>()
        var checkedSpecies = ArrayList<Species>()

        var requestCode = 0
        var isMultiple = false

        fun clear() {
            specieses.clear()
            redSpecieses.clear()
            speciesesTemp.clear()
        }
    }
}