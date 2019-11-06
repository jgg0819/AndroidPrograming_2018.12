package com.example.coronastagram_f16

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var auth: FirebaseAuth?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth=FirebaseAuth.getInstance();
    }
    fun signinAndSignup(){
        auth?.createUserWithEmailAndPassword(email_edittext.text.toString(), password_edittext.text.toString())?.addOnCompleteListener {
            task->
                if(task.isSuccessful){
                    //Creating a user account
                }else if(task.exception?.message.isNullOrEmpty()){
                    //Show the error message
                }else{
                    //Login if you have account
                }
        }
    }

    fun signinEmail(){
        auth?.createUserWithEmailAndPassword(email_edittext.text.toString(), password_edittext.text.toString())?.addOnCompleteListener {
                task->
            if(task.isSuccessful){
                //Login
            }else{
                //Show the error message
            }
        }
    }
}
