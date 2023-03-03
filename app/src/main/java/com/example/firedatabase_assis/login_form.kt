package com.example.firedatabase_assis

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.firedatabase_assis.databinding.ActivityLoginFormBinding

class ogin_form : AppCompatActivity() {
    private lateinit var bind: ActivityLoginFormBinding

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityLoginFormBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val dbhelp = DB_class(applicationContext)
        val db = dbhelp.readableDatabase
        bind.btnlogin.setOnClickListener {
            val username = bind.logtxt.text.toString()
            val password = bind.ed3.text.toString()
            val query = "SELECT * FROM user WHERE username='$username' AND pswd='$password'"
            val rs = db.rawQuery(query, null)
            if (rs.moveToFirst()) {
                val name = rs.getString(rs.getColumnIndex("name"))
                rs.close()
                startActivity(Intent(this, welcome_window::class.java).putExtra("name", name))
            } else {
                val ad = AlertDialog.Builder(this)
                ad.setTitle("Message")
                ad.setMessage("Username or password is incorrect!")
                ad.setPositiveButton("Ok", null)
                ad.show()
            }
        }
        bind.regisLink.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}