package com.sdjt.plantsworld.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sdjt.plantsworld.MainActivity
import com.sdjt.plantsworld.PlantRepository
import com.sdjt.plantsworld.PlantRepository.Singleton.plantList
import com.sdjt.plantsworld.R
import com.sdjt.plantsworld.adapter.PlantAdapter
import com.sdjt.plantsworld.adapter.PlantItemdecoration
import java.util.zip.Inflater

class CollectionFragment( private val context: MainActivity ) : Fragment()  {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       // val view = inflater?.inflate(R.layout.fragment_home, container, false)

        val view = inflater?.inflate(R.layout.fragment_collection, container, false)

        // recuperer le recycler view
        val collectionRecyclerView = view.findViewById<RecyclerView>(R.id.collection_recycler_list)

        collectionRecyclerView.adapter = PlantAdapter(context, plantList,R.layout.item_vertical_plant)
        collectionRecyclerView.layoutManager = LinearLayoutManager(context)
        // recuperer le recycler view
        //val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        //dire quel layout a utiliser en envoyant un parametre
        //horizontalRecyclerView.adapter = PlantAdapter(context,
        //    PlantRepository.Singleton.plantList, R.layout.item_horizontal_plant)

        // recuperer le second recyclerview
        //val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        //verticalRecyclerView.adapter = PlantAdapter(context, plantList, R.layout.item_vertical_plant)
       // verticalRecyclerView.addItemDecoration(PlantItemdecoration())

        return view
    }
}