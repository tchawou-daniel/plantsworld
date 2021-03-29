package com.sdjt.plantsworld.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sdjt.plantsworld.MainActivity
import com.sdjt.plantsworld.PlantModel
import com.sdjt.plantsworld.PlantRepository
import com.sdjt.plantsworld.R

//quatrième partie fini  2h01min
                        //class déjà compris dans android donc on a recrée cela
 class PlantAdapter(private val context:MainActivity, private val plantList: List<PlantModel>, private val layoutId: Int) : RecyclerView.Adapter<PlantAdapter.ViewHolder>() {
    //boite pour ranger tout les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //image de la plante
        val plantImage = view.findViewById<ImageView>(R.id.image_item)
        val plantName: TextView? = view.findViewById(R.id.name_item)
        val plantDescription: TextView? = view.findViewById(R.id.description_item)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)

    }

    //permet d'injecter le composant comme pour le fragment
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //recuperer les informations de la plante
        val currentPlant = plantList[position]

        //recuperer le repository
        val repo = PlantRepository()

        // utiliser glide pour recuperer l'image à partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentPlant.imageUrl)).into(holder.plantImage)

        //mettre à jour le nom de la plante
        holder.plantName?.text = currentPlant.name

        //mettre à jour la description de la plante
        holder.plantDescription?.text = currentPlant.description

        //verifier si la plante à été liker ou non
        if (currentPlant.liked) {
            holder.starIcon.setImageResource(R.drawable.ic_star)
        } else {
            holder.starIcon.setImageResource(R.drawable.ic_unstar)
        }

        //rajouter une interaction sur cette etoile
        holder.starIcon.setOnClickListener {
            //inverser si le bouton est liked ou non
            currentPlant.liked = !currentPlant.liked
            //mettre à jour l'objet plante
            repo.updatePlant(currentPlant)
        }
    }

    override fun getItemCount(): Int = plantList.size
}