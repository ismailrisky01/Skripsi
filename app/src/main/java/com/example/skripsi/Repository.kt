package com.example.skripsi

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class Repository {
    val realtime = FirebaseDatabase.getInstance().reference
    val firestore = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()

    fun regisFirebase(context: Context, email: String, password: String, task: Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "Successful Create Account", Toast.LENGTH_SHORT).show()
                task
            } else {
                Toast.makeText(context, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun loginFirebase(context: Context, email: String, password: String, task: Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                task
            } else {
                Toast.makeText(context, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()

        }
    }

    fun loginWithVeriFirebase(context: Context, email: String, password: String, task: Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                if (auth.currentUser!!.isEmailVerified) {
                    task
                } else {
                    Toast.makeText(context, "Please Verified Your Email", Toast.LENGTH_SHORT).show()
                    auth.signOut()
                }
            } else {
                Toast.makeText(context, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()

        }
    }

    fun setDataRealtime(context: Context, child: String, value: String) {
        realtime.child(child).setValue(value).addOnSuccessListener {
            Toast.makeText(context, "Success set Data", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteDataRealtime(context: Context, child: String) {
        realtime.child(child).removeValue().addOnSuccessListener {
            Toast.makeText(context, "Success Delete Data", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()

        }
    }

    fun setDataFirestore(context: Context, collection: String, document: String, data: Any) {
        if (document == "") {
            firestore.collection(collection).document().set(data).addOnSuccessListener {
                Toast.makeText(context, "Success to set Data Firestore", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
            }

        }else{
            firestore.collection(collection).document(document).set(data).addOnSuccessListener {
                Toast.makeText(context, "Success to set Data Firestore", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}