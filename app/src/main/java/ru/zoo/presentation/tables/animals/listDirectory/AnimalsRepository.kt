package ru.zoo.presentation.tables.animals.listDirectory

import ru.zoo.data.models.Animal

class AnimalsRepository {
    companion object{
        var animalsTemp = ArrayList<Animal>()
        fun addAnimal(animal: Animal) {
            if (checkedAnimal.any { it.id == animal.id }) {
                checkedAnimal.removeAll { it.id == animal.id }
            } else {
                checkedAnimal.add(animal)
            }
        }
        var animals = ArrayList<Animal>()
        var redAnimals = ArrayList<Animal>()
        var checkedAnimal = ArrayList<Animal>()

        var requestCode = 0
        var isMultiple = false

        fun clear() {
            animals.clear()
            redAnimals.clear()
            animalsTemp.clear()
        }
    }
}