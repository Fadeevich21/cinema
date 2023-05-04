package com.example.cinema.ui.activies

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
                finish()
            } else {
                Toast.makeText(applicationContext, "Login not success", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isRegisterLive.observe(this) {
            if (viewModel.isRegisterLive.value == true)
                Toast.makeText(applicationContext, "Register success", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(applicationContext, "Register not success", Toast.LENGTH_SHORT)
                    .show()
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

        setupFloatingLabelError()
    }

    private fun setupFloatingLabelError() {
        val usernameLabel = binding.usernameTextInputLayout
        usernameLabel.editText!!.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(text: CharSequence, start: Int, count: Int, after: Int) {
                if (text.isEmpty()) {
                    binding.loginButton.isEnabled = false
                    binding.registerButton.isEnabled = false
                    return
                }

                if (text.length in 1..4) {
                    usernameLabel.error = "username is incorrect"
                    usernameLabel.isErrorEnabled = true
                } else
                    usernameLabel.isErrorEnabled = false

                binding.loginButton.isEnabled = !usernameLabel.isErrorEnabled
                binding.registerButton.isEnabled = !usernameLabel.isErrorEnabled
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }
}