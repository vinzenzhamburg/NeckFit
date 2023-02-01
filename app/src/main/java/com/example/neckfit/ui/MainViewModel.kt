package com.example.neckfit.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neckfit.data.Repository
import com.example.neckfit.data.datamodel.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch

const val TAG = "MainViewModel"

enum class ApiStatus { LOADING, ERROR, DONE }

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = Repository()
//TODO : LOADING SYMBOL
    private val _loading = MutableLiveData<ApiStatus>()
    val loading: LiveData<ApiStatus>
        get() = _loading

    private val _themes = MutableLiveData<List<Theme>>()
    val themes: LiveData<List<Theme>>
        get() = _themes

    private val _exercises = MutableLiveData<List<Uebung>>()

    private val _allTraining = MutableLiveData<List<Training>>()
    val allTraining: LiveData<List<Training>>
        get() = _allTraining

    private val _category = MutableLiveData<List<Category>>()
    val category: LiveData<List<Category>>
        get() = _category

    private val _types = MutableLiveData<List<Category>>()
    val types: LiveData<List<Category>>
       get() = _types

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

    fun getThemes() {
        viewModelScope.launch {
            _loading.value = ApiStatus.LOADING
            _themes.value = repo.loadThemes()
            _loading.value = ApiStatus.DONE
        }
    }

    fun getAllTraining() {
        viewModelScope.launch {
            _allTraining.value = repo.loadAllTraining()
        }
    }

    fun setTypes(types: List<Category>) {
        _loading.value = ApiStatus.LOADING
        _types.value = types
        _loading.value = ApiStatus.DONE
    }
}
