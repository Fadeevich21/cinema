package com.example.cinema.ui.activies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cinema.App
import com.example.cinema.databinding.ActivityLoginBinding
import com.example.cinema.ui.viewModel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loginUserLive.observe(this) {
            if (viewModel.loginUserLive.value != null) {
                Toast.makeText(applicationContext, "Login success", Toast.LENGTH_SHORT).show()
                App.user = viewModel.loginUserLive.value
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Login not success", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isRegisterLive.observe(this) {
            if (viewModel.isRegisterLive.value == true)
                Toast.makeText(applicationContext, "Register success", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(applicationContext, "Register not success", Toast.LENGTH_SHORT).show()
        }

        binding.loginButton.setOnClickListener {
            val username = binding.usernameForm.text.toString()
            val password = binding.passwordForm.text.toString()
            viewModel.login(username = username, password = password)
        }

        binding.registerButton.setOnClickListener {
            val username = binding.usernameForm.text.toString()
            val password = binding.passwordForm.text.toString()
            viewModel.register(username = username, password = password)
        }
    }
}