package com.sdjt.plantsworld

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.sdjt.plantsworld.PlantRepository.Singleton.databaseRef
import com.sdjt.plantsworld.PlantRepository.Singleton.downloadUri
import com.sdjt.plantsworld.PlantRepository.Singleton.plantList
import com.sdjt.plantsworld.PlantRepository.Singleton.storageReference

import java.util.*

class PlantRepository {
    object Singleton{

        //donner le lien our acceder au bucket
        private val BUCKET_URL: String = "gs://plantsworld-f63ff.appspot.com"

        //se connecter à notre espace de stockage
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)
        //se connecter à la référence "plants"
        var databaseRef = FirebaseDatabase.getInstance("https://plantsworld-f63ff-default-rtdb.firebaseio.com/").getReference("plants")

        //créer une liste qui va contenir les plantes
        val plantList = arrayListOf<PlantModel>()

        //contenir l'image courante
        var downloadUri:Uri?=null

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


    // créer une fonction pour envoyer des fichiers sur le storage
    fun uploadImage(file: Uri, callback: () -> Unit){
        // verifier que ce fichier n'est pas null
        if(file!=null){
            val fileName= UUID.randomUUID().toString()+".jpg"
            val ref = storageReference.child(fileName)
            val uploadTask = ref.putFile(file)

            //demarrer la tache d'envoi
            uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot,Task<Uri>>{ task->
                // on verifie si il y a eu un pb lors de l'envoi su fichier
                if(!task.isSuccessful){
                    task.exception?.let { throw it }
                }
                return@Continuation ref.downloadUrl

            }).addOnCompleteListener { task->
                if(task.isSuccessful){
                    // recuperer l'image qui est en ligne
                    downloadUri = task.result
                    callback()
                }
            }

        }
    }


    // mettre à jour un objet plante en bdd
    fun updatePlant(plant: PlantModel) = Singleton.databaseRef.child(plant.id).setValue(plant)


    // inserer une nouvelle plante en bdd
    fun insertPlant(plant: PlantModel) = Singleton.databaseRef.child(plant.id).setValue(plant)


    //supprimer une plante de la base
    fun deletePlant(plant: PlantModel) = Singleton.databaseRef.child(plant.id).removeValue()
}