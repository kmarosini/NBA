package hr.algebra.nba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import hr.algebra.nba.framework.startActivity

class SignInActivity : AppCompatActivity() {

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signInEmail : EditText = findViewById(R.id.etSignInEmail)
        val signInPassword : EditText = findViewById(R.id.etSignInPassword)
        val btnSignIn : Button = findViewById(R.id.btn_sign_in)
        val tvDontHaveAccount : TextView = findViewById(R.id.tvDontHaveAccount)

        auth = FirebaseAuth.getInstance()


        tvDontHaveAccount.setOnClickListener {
            startActivity<SignUpActivity>()
        }



        btnSignIn.setOnClickListener {
            val email = signInEmail.text.toString()
            val password = signInPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                if (email.isEmpty()) {
                    signInEmail.error = "Enter your email address"
                }
                if (password.isEmpty()) {
                    signInPassword.error = "Enter password"
                }
                Toast.makeText(this, "Enter valid details", Toast.LENGTH_SHORT).show()
            } else if(!email.matches(emailPattern.toRegex())) {
                signInEmail.error = "Enter valid email address"
                Toast.makeText(this, "Enter valid email address", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        startActivity<HostActivity>()
                        Toast.makeText(this, "Successfully logged in!", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}