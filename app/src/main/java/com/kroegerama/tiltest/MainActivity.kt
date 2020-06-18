package com.kroegerama.tiltest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialFadeThrough
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnFragA.setOnClickListener(::btnClick)
        btnFragB.setOnClickListener(::btnClick)

        //load fragA on start
        btnClick(btnFragA)
    }

    private fun btnClick(btn: View) {
        val next = if (btn.id == R.id.btnFragA) FragA() else FragB()
        next.apply {
            enterTransition = MaterialFadeThrough().apply {
                duration = 3000
            }
            exitTransition = MaterialFadeThrough().apply {
                duration = 3000
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, next)
            .setReorderingAllowed(true)
            .commit()
    }
}

class FragA : Fragment(R.layout.frag_a)
class FragB : Fragment(R.layout.frag_b)