package com.mobdeve.s11.ruelos.miguel.mc02

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var username: EditText? = null
    var password: EditText? = null
    var repassword: EditText? = null
    var signup: Button? = null
    var signin: Button? = null
    var DB: DBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        username = findViewById<View>(R.id.et_username) as EditText
        password = findViewById<View>(R.id.et_password) as EditText
        repassword = findViewById<View>(R.id.et_confirmpassword) as EditText
        signup = findViewById<View>(R.id.btnsignup) as Button
        signin = findViewById<View>(R.id.btnsignin) as Button
        DB = DBHelper(this)
        signup!!.setOnClickListener {
            val user = username!!.text.toString()
            val pass = password!!.text.toString()
            val repass = repassword!!.text.toString()
            if (user == "" || pass == "" || repass == "") Toast.makeText(
                this@MainActivity,
                "Please enter all the fields",
                Toast.LENGTH_SHORT
            ).show() else {
                if (pass == repass) {
                    val checkuser = DB!!.checkusername(user)
                    if (checkuser == false) {
                        val insert = DB!!.insertData(user, pass)
                        if (insert == true) {
                            Toast.makeText(
                                this@MainActivity,
                                "Registered successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(applicationContext, HomePage::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Registration failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "User already exists! please sign in",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Passwords not matching", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        signin!!.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}