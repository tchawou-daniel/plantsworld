package com.sdjt.plantsworld.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sdjt.plantsworld.MainActivity
import com.sdjt.plantsworld.PlantModel
import com.sdjt.plantsworld.R
import com.sdjt.plantsworld.adapter.PlantAdapter
import com.sdjt.plantsworld.adapter.PlantItemdecoration

class Homefragment(private val context: MainActivity) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)

        // créer une liste qui va stocker ces plantes
        val plantList = arrayListOf<PlantModel>()
        // enregistrer une premiere plate dans notre liste (pissenlit)
        plantList.add(PlantModel(
                "Pissenlit",
                "Jaune soleil",
                "https://cdn.pixabay.com/photo/2014/05/01/17/51/dandelion-335662_960_720.jpg",
                false
        ))

        plantList.add(PlantModel(
                "Rose",
                "ça pique un peu",
                "https://cdn.pixabay.com/photo/2016/09/03/23/18/rose-1642970_960_720.jpg",
                false
        ))

        plantList.add(PlantModel(
                "Cactus",
                "ça pique beaucoup",
                "https://cdn.pixabay.com/photo/2016/06/13/07/32/cacti-1453793_960_720.jpg",
                true
        ))

        plantList.add(PlantModel(
                "Tulipe",
                "C'est beau",
                "https://cdn.pixabay.com/photo/2017/04/23/20/36/pink-2254970_960_720.jpg",
                false
        ))
        // recuperer le recycler view
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        //dire quel layout a utiliser en envoyant un parametre
        horizontalRecyclerView.adapter = PlantAdapter(context,plantList, R.layout.item_horizontal_plant)

        // recuperer le second recyclerview
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = PlantAdapter(context,plantList, R.layout.item_vertical_plant)
        verticalRecyclerView.addItemDecoration(PlantItemdecoration())

        return view
    }
}