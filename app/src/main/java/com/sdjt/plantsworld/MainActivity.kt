package com.sdjt.plantsworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sdjt.plantsworld.fragments.Homefragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //injecter le fragment dans notre boite (fragment_container)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, Homefragment(this))
        transaction.addToBackStack(null)
        transaction.commit()
    }
}