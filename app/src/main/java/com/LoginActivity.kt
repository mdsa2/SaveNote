package com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mohammed.savenote.MainActivity
import com.mohammed.savenote.R
import com.mohammed.savenote.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
 private lateinit var binding :  ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
       binding.buttonl.setOnClickListener {
            if ( binding.idpassw.text.toString().equals("mohammed")) {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)


            }else {
                Toast.makeText(this,"The Password is not Correct ", Toast.LENGTH_LONG).show()
            }


        }
    }
}