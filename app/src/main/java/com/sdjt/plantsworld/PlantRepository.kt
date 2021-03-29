package com.sdjt.plantsworld

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.security.auth.callback.Callback



class PlantRepository {
    object Singleton{
        //se connecter à la référence "plants"
        var databaseRef = FirebaseDatabase.getInstance("https://plantsworld-f63ff-default-rtdb.firebaseio.com/").getReference("plants")

        //créer une liste qui va contenir les plantes
        val plantList = arrayListOf<PlantModel>()
    }
    
    fun updateData(callback: () -> Unit){
        //absorber les données depuis la databaseRef -> liste de plantes
        Singleton.databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                // retirer les anciennes
                Singleton.plantList.clear()
                //recolter la liste
                for (ds in snapshot.children){
                    //construire un objet plante
                    val plant = ds.getValue(PlantModel::class.java)

                    //vérifier que la plante à bien été chargée
                    if(plant != null) {
                        // ajouter à notre liste la plante
                        Singleton.plantList.add(plant)
                    }
                }
                // actionner le callback
                callback()
            }

            override fun onCancelled(p0: DatabaseError) {
            }

        })
    }
// mettre à jour un objet plante en bdd
    fun updatePlant(plant: PlantModel){
        Singleton.databaseRef.child(plant.id).setValue(plant)
    }

    //supprimer une plante de la base
    fun deletePlant(plant: PlantModel) = Singleton.databaseRef.child(plant.id).removeValue()
}