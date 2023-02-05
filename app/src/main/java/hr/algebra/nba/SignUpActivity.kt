package hr.algebra.nba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import hr.algebra.nba.framework.startActivity
import hr.algebra.nba.model.Users

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var database : FirebaseDatabase
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val signUpEmail : EditText = findViewById(R.id.etEmail)
        val signUpPassword : EditText = findViewById(R.id.etPassword)
        val signUpPasswordRepeat : EditText = findViewById(R.id.etPassword_Repeat)
        val signUpButton : Button = findViewById(R.id.btn_sign_up)
        val alreadyHaveAccount : TextView = findViewById(R.id.tvAlreadyHaveAccount)



        /*if (auth.currentUser == null) {
            startActivity<SignUpActivity>()
        }*/

        alreadyHaveAccount.setOnClickListener {
            startActivity<SignInActivity>()
        }

        signUpButton.setOnClickListener {
            val email = signUpEmail.text.toString()
            val password = signUpPassword.text.toString()
            val password_repeat = signUpPasswordRepeat.text.toString()

            if (email.isEmpty() || password.isEmpty() || password_repeat.isEmpty()) {
                if (email.isEmpty()) {
                    signUpEmail.error = "Enter your email"
                }
                if (password.isEmpty()) {
                    signUpPassword.error = "Enter your password"
                }
                if (password_repeat.isEmpty()) {
                    signUpPasswordRepeat.error = "Repeat your password"
                }
                Toast.makeText(this, "Enter valid details!", Toast.LENGTH_SHORT).show()
            } else if (!email.matches(emailPattern.toRegex())) {
                signUpEmail.error = "Enter valid email address"
                Toast.makeText(this, "Enter valid email address!", Toast.LENGTH_SHORT).show()
            } else if (password.length < 6) {
                signUpPassword.error = "Enter password with min. 6 characters"
                Toast.makeText(this, "Enter valid password!", Toast.LENGTH_SHORT).show()
            } else if (password_repeat != password) {
                signUpPasswordRepeat.error = "Passwords doesn't match"
                Toast.makeText(this, "Repeat password!", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful){
                        val databaseRef = database.reference.child("users").child(auth.currentUser!!.uid)
                        val users : Users = Users(email, password, auth.currentUser!!.uid)

                        databaseRef.setValue(users).addOnCompleteListener {
                            if (it.isSuccessful) {
                                startActivity<SignInActivity>()
                                Toast.makeText(this, "Successfully registered!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this, "Something went wrong, try again", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(this, "Something went wrong, try again", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


    }
}