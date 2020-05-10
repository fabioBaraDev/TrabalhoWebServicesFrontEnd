package br.com.fiap.controledealunos.view.logon

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.fiap.controledealunos.R
import br.com.fiap.controledealunos.Session
import br.com.fiap.controledealunos.model.User
import br.com.fiap.controledealunos.util.Validadores
import br.com.fiap.controledealunos.view.main.MainActivity
import br.com.fiap.controledealunos.viewModel.AuthViewModel
import com.tomer.fadingtextview.FadingTextView
import kotlinx.android.synthetic.main.activity_logon.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.regex.Pattern


class LogonActivity : AppCompatActivity() {

    val auth: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logon)

        buttonControl()
        fieldListeners()
    }

    private fun buttonControl() {

        val btLogon = findViewById<Button>(R.id.bt_logon)
        btLogon.setOnClickListener {
            if (validarEmail()) {
                validaCredenciais()
            }
        }
    }

    private fun validaCredenciais() {
        val nome = findViewById(R.id.et_email_logon) as TextView
        val senha = findViewById(R.id.et_senha_logon) as TextView

        var user = User(nome.text.toString(), senha.text.toString())

        auth.logon(user)

        auth.messageError.observe(this, Observer {
            if (it != "") {
                trataErro()
            }
        })

        auth.tokenUser.observe(this, Observer {
            if (it.token.isNullOrEmpty()) {
                trataErro()
            } else {

                Session.data["token"] = it.token

                val intent = Intent(this@LogonActivity, MainActivity::class.java)
                startActivity(intent)
                this@LogonActivity.finish()

                Toast.makeText(this, "Bem Vindo", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun trataErro() {
        showToolTip("Senha ou email invalidos")
        et_senha_logon.setTextColor(Color.RED)
        et_email_logon.setTextColor(Color.RED)
    }

    private fun fieldListeners() {

        et_email_logon.setOnFocusChangeListener { view, hasFocus -> validarEmail() }

        abstract class LocalTextWatcher() : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                hideTP()
            }

            abstract fun hideTP()
        }

        et_senha_logon.addTextChangedListener(object : LocalTextWatcher() {
            override fun hideTP() {
                hideToolTip()
            }
        })

        et_email_logon.addTextChangedListener(object : LocalTextWatcher() {
            override fun hideTP() {
                hideToolTip()
            }
        })
    }

    private fun validarEmail(): Boolean {
        if (!Validadores.isEmailValid(et_email_logon.text.toString())) {
            et_email_logon.setTextColor(Color.RED);
            showToolTip("e-mail invalido!")
            bt_logon.isEnabled = false
            bt_logon.isClickable = false
            return false
        }

        bt_logon.isEnabled = true
        bt_logon.isClickable = true
        return true
    }

    private fun showToolTip(msg: String) {
        val fadingTextView = findViewById(R.id.tv_fadingTV) as FadingTextView
        fadingTextView.text = msg
    }

    private fun hideToolTip() {
        val fadingTextView = findViewById(R.id.tv_fadingTV) as FadingTextView
        et_email_logon.setTextColor(Color.BLACK);
        et_senha_logon.setTextColor(Color.BLACK);
        fadingTextView.text = ""
    }
}
