package com.sdjt.plantsworld

class PlantModel (
        val id: String = "plant2",
        val name: String = "Tulipe",
        val description: String = "Petite description",
        //plante par defaut s'il y'a un pb
        val imageUrl: String = "https://pixabay.com/fr/photos/pissenlit-plantes-t%C3%AAte-de-semences-335662/",
        val grow: String = "Faible",
        val water: String = "Moyenne",
        var liked: Boolean = false
)