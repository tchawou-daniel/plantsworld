package com.sdjt.plantsworld

import android.app.Dialog
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.sdjt.plantsworld.adapter.PlantAdapter

class PlantPopup(
    private val adapter: PlantAdapter,
    private val currentPlant: PlantModel
    ) : Dialog(adapter.context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_plants_details)
        setupComponents()
        setupCloseButton()
        setupDeleteButton()
        setUpStarButton()
    }

    private fun updateStar(button: ImageView){
        if(currentPlant.liked){
            button.setImageResource(R.drawable.ic_star)
        }else{
            button.setImageResource(R.drawable.ic_unstar)
        }
    }

    private fun setUpStarButton(){
        val starButton=findViewById<ImageView>(R.id.star_button)
        updateStar(starButton)
        //interactoin
        starButton.setOnClickListener{
            currentPlant.liked = !currentPlant.liked
            val repo = PlantRepository()
            repo.updatePlant(currentPlant)
            updateStar(starButton)
        }
    }
    private fun setupCloseButton(){
        findViewById<ImageView>(R.id.close_button).setOnClickListener{
            // fermer la fenetre
            dismiss()
        }
    }

    private fun setupDeleteButton(){
        findViewById<ImageView>(R.id.delete_button).setOnClickListener{
            //supprimer la plante de la base
            val repo = PlantRepository()
            repo.deletePlant(currentPlant)
            dismiss()
        }
    }

    private fun setupComponents(){
        //actualiser l'image de la plante
        val plantImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentPlant.imageUrl)).into(plantImage)

        //actualiser le nom de la plante
        findViewById<TextView>(R.id.popup_plant_name).text = currentPlant.name

        //actualiser la description de la plante
        findViewById<TextView>(R.id.popup_plant_description_subtitle).text = currentPlant.description

        //actualiser la croissance de la plante
        findViewById<TextView>(R.id.popup_plant_grow_subtitle).text = currentPlant.grow

        //actualiser la consommation d'eau de la plante
        findViewById<TextView>(R.id.popup_plant_water_subtitle).text = currentPlant.water

    }
}