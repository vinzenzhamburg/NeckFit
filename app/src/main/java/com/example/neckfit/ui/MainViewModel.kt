package com.example.neckfit.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neckfit.data.Repository
import com.example.neckfit.data.datamodel.Theme
import com.example.neckfit.data.datamodel.Training
import com.example.neckfit.data.datamodel.Uebung
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch

const val TAG = "MainViewModel"

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = Repository()

    private val _themes = MutableLiveData<List<Theme>>()
    val themes: LiveData<List<Theme>>
        get() = _themes

    private val _exercises = MutableLiveData<List<Uebung>>()
    val exercises: LiveData<List<Uebung>>
        get() = _exercises

    private val _allTraining = MutableLiveData<List<Training>>()
    val allTraining: LiveData<List<Training>>
        get() = _allTraining

    private val _currentTraining = MutableLiveData<List<Training>>()
    val currentTraining: LiveData<List<Training>>
        get() = _currentTraining

    // Kommunikationspunkt mit der Firestore Datenbank
    private val db = FirebaseFirestore.getInstance()

    // Kommunikationspunkt mit der FirebaseAuth
    private val firebaseAuth = FirebaseAuth.getInstance()

    // Kommunikationspunkt mit Firebase Storage
    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference

    // currentuser ist null wenn niemand eingeloggt ist
    private val _currentUser = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser

    private val _toast = MutableLiveData<String?>()
    val toast: LiveData<String?>
        get() = _toast

    // hier wird versucht einen User zu erstellen um diesen anschließend auch gleich
    // einzuloggen
    fun signUp(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    login(email, password)
                } else {
                    Log.e(TAG, "SignUp failed: ${it.exception?.message}")
                    _toast.value = it.exception?.message
                    _toast.value = null
                }
            }
    }

    fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _currentUser.value = firebaseAuth.currentUser
                } else {
                    Log.e(TAG, "Login failed: ${it.exception?.message}")
                    _toast.value = it.exception?.message
                    _toast.value = null
                }
            }
    }

    fun logout() {
        firebaseAuth.signOut()
            _currentUser.value = firebaseAuth.currentUser

    }

    fun getThemes(){
        viewModelScope.launch{
            _themes.value = repo.loadThemes()
        }
    }

    fun getExercises(){
        viewModelScope.launch {
            _exercises.value = repo.loadExercises()
        }
    }

    fun getAllTraining(){
        viewModelScope.launch {
            _allTraining.value = repo.loadAllTraining()
        }
    }
// TODO : Bauen eine derzeitige Trainingsliste

    fun loadCurrent(category: String,supCategory: String){
        val currentTraining  = mutableListOf<Training>()

        val type = _exercises.value?.find {
            it.name.equals(category)
        }

    }
}