package br.com.fiap.controledealunos.view.usuario

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.fiap.controledealunos.R
import br.com.fiap.controledealunos.model.User
import br.com.fiap.controledealunos.view.logon.LogonActivity
import br.com.fiap.controledealunos.view.main.MainActivity
import br.com.fiap.controledealunos.viewModel.UsuariosViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AdicionarUsuarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_usuario)

        onClickLogout()
        listeners()
    }

    val usuViewModel: UsuariosViewModel by viewModel()

    private fun listeners() {
        var etUsuario = findViewById<EditText>(R.id.et_email_adm)
        var etSenha = findViewById<EditText>(R.id.et_senha_adm)

        val btAddAdm = findViewById<Button>(R.id.bt_adiciona_usu_adm)

        btAddAdm.setOnClickListener {
            val usuario = User(etUsuario.text.toString(), etSenha.text.toString())

            usuViewModel.adicionarADM(usuario)
            usuViewModel.messageError.observe(this, Observer {
                if (it != "") {
                    etUsuario.setTextColor(Color.RED)
                    etSenha.setTextColor(Color.RED)
                    Toast.makeText(this, "Erro ao inserir o usuario", Toast.LENGTH_LONG).show()
                }
            })

            usuViewModel.statusUsuarioADM.observe(this, Observer {
                val intent = Intent(this@AdicionarUsuarioActivity, MainActivity::class.java)
                startActivity(intent)
                this@AdicionarUsuarioActivity.finish()
                Toast.makeText(this, "Usuario Adicionado ", Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun onClickLogout(){
        val btLogout = findViewById<Button>(R.id.bt_logout_add_usu)
        btLogout.setOnClickListener {
            val intent = Intent(this@AdicionarUsuarioActivity, LogonActivity::class.java)
            startActivity(intent)
            this@AdicionarUsuarioActivity.finish()
        }
    }
}

