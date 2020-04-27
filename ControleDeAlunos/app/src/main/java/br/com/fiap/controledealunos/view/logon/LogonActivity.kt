package br.com.fiap.controledealunos.view.logon

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.controledealunos.R
import br.com.fiap.controledealunos.view.main.MainActivity
import com.tomer.fadingtextview.FadingTextView

import kotlinx.android.synthetic.main.activity_logon.*
import java.util.regex.Pattern

class LogonActivity : AppCompatActivity() {

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

        if (et_senha.text.toString() == "123") {
            val intent = Intent(this@LogonActivity, MainActivity::class.java)
            startActivity(intent)
            this@LogonActivity.finish()
            return
        }

        showToolTip("Senha ou email invalidos")
        et_senha.setTextColor(Color.RED)
        et_usuario_email.setTextColor(Color.RED)
    }

    private fun fieldListeners() {

        et_usuario_email.setOnFocusChangeListener { view, hasFocus -> validarEmail() }

        abstract class LocalTextWatcher() : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int){ }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                hideTP()
            }
            abstract fun hideTP()
        }

        et_senha.addTextChangedListener(object : LocalTextWatcher() {
            override fun hideTP() {
                hideToolTip()
            }
        })

        et_usuario_email.addTextChangedListener(object : LocalTextWatcher() {
            override fun hideTP() {
                hideToolTip()
            }
        })
    }

    private fun validarEmail(): Boolean {
        if (!isEmailValid(et_usuario_email.text.toString())) {
            et_usuario_email.setTextColor(Color.RED);
            showToolTip("e-mail invalido!")
            bt_logon.isEnabled = false
            bt_logon.isClickable = false
            return false
        }

        bt_logon.isEnabled = true
        bt_logon.isClickable = true
        return true
    }

    private fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    private fun showToolTip(msg: String) {
        val fadingTextView = findViewById(R.id.tv_fadingTV) as FadingTextView
        fadingTextView.text = msg
    }

    private fun hideToolTip() {
        val fadingTextView = findViewById(R.id.tv_fadingTV) as FadingTextView
        et_usuario_email.setTextColor(Color.BLACK);
        et_senha.setTextColor(Color.BLACK);
        fadingTextView.text = ""
    }
}
