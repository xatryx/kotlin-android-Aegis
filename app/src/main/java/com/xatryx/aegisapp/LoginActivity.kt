package com.xatryx.aegisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.xatryx.aegisapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.apply {
            BLogin.setOnClickListener {
                if (ETGuildID.text.isNullOrEmpty()) {
                    ETGuildID.error = resources.getString(R.string.Input_Missing_Required)
                }

                if (ETGuildToken.text.isNullOrEmpty()) {
                    ETGuildToken.error = resources.getString(R.string.Input_Missing_Required)
                }

                if (ETGuildID.text.isNullOrEmpty() || ETGuildToken.text.isNullOrEmpty()) {
                    Snackbar.make(binding.root, resources.getString(R.string.Snackbar_Dummy_LoginError), Snackbar.LENGTH_SHORT).show()
                }

                if (!ETGuildID.text.isNullOrEmpty() && !ETGuildToken.text.isNullOrEmpty()) {
                    Snackbar.make(binding.root, resources.getString(R.string.Snackbar_Dummy_LoginSuccess), Snackbar.LENGTH_SHORT).show()
                    startActivity(
                        Intent(this@LoginActivity, MainActivity::class.java)
                    )
                }
            }
        }
    }
}