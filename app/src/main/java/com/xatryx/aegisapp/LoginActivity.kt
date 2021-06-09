package com.xatryx.aegisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.xatryx.aegisapp.databinding.ActivityLoginBinding
import com.xatryx.aegisapp.util.NetworkState
import com.xatryx.aegisapp.viewmodel.CommonDiscordViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance

class LoginActivity : AppCompatActivity(), DIAware {

    override val di: DI by closestDI()

    private val viewModel: CommonDiscordViewModel by instance()

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClickHandler()

        Snackbar.make(binding.root, resources.getString(R.string.Snackbar_Dummy_LoginSuccess), Snackbar.LENGTH_SHORT).show()
        startActivity(Intent(this@LoginActivity, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            finish()
        })
    }

    private fun onClickHandler() {
        binding.apply {
            btnLogin.setOnClickListener {

                if (etGuildId.text.isNullOrEmpty()) {
                    etGuildId.error = resources.getString(R.string.Input_Missing_Required)
                }

                if (etGuildToken.text.isNullOrEmpty()) {
                    etGuildToken.error = resources.getString(R.string.Input_Missing_Required)
                }

                if (etGuildId.text.isNullOrEmpty() || etGuildToken.text.isNullOrEmpty()) {
                    Snackbar.make(binding.root, resources.getString(R.string.Snackbar_Dummy_LoginError), Snackbar.LENGTH_SHORT).show()
                }

                if (!etGuildId.text.isNullOrEmpty() && !etGuildToken.text.isNullOrEmpty()) {
                    viewModel.queryGuildDetails(etGuildId.text.toString(), etGuildToken.text.toString()) {
                        toggleState(it)
                    }
                }
            }
        }
    }

    private fun toggleState(state: NetworkState) {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.getState().observe(this@LoginActivity, {
                when(it) {
                    NetworkState.LOAD -> {
                        Log.i("LocalNetworkState", "is $it")
                    }
                    NetworkState.DONE -> {
                        Log.i("LocalNetworkState", "is $it")

                        viewModel.getGuildDetails().observe(this@LoginActivity, { item ->
                            if (item.guildId == binding.etGuildId.text.toString() && item.guildToken == binding.etGuildToken.text.toString()) {
                                Snackbar.make(binding.root, resources.getString(R.string.Snackbar_Dummy_LoginSuccess), Snackbar.LENGTH_SHORT).show()
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java).apply {
                                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                    finish()
                                })
                            } else {
                                Snackbar.make(binding.root, resources.getString(R.string.Snackbar_Dummy_LoginError), Snackbar.LENGTH_SHORT).show()
                            }
                        })

                    }
                    NetworkState.FAIL -> {
                        Log.i("LocalNetworkState", "is $it")
                    }
                    null -> {
                        Log.i("LocalNetworkState", "is $it")
                    }
                }
            })
        }
    }
}