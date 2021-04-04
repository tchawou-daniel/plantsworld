package com.sdjt.plantsworld

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sdjt.plantsworld.fragments.AddPlantFragment
import com.sdjt.plantsworld.fragments.CollectionFragment
import com.sdjt.plantsworld.fragments.Homefragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        // cacher la barre de titre
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        setContentView(R.layout.activity_main)

        //charger notre PlantRepository
        val repo = PlantRepository()


        //mettre à jour la liste de plantes
        repo.updateData{
            //injecter le fragment dans notre boite (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, Homefragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
        }



    }
}