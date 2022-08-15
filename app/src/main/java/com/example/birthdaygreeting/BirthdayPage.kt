package com.example.birthdaygreeting

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.birthdaygreeting.databinding.ActivityBirthdayPageBinding

class BirthdayPage : AppCompatActivity() {

    private lateinit var birthdayPageBinding: ActivityBirthdayPageBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        birthdayPageBinding = ActivityBirthdayPageBinding.inflate(layoutInflater)
        val view = birthdayPageBinding.root
        setContentView(view)

        val i = intent
        val txtData = i.extras!!.getString("txtData", "")
        val txtInput2 = birthdayPageBinding.birthdayPersonName
        txtInput2.text = "Happy Birthday $txtData"
    }
}