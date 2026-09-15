package com.example.cursosapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CursoViewModel : ViewModel() {

    private val repository = CursoRepository()

    private val _cursos =
        MutableStateFlow<List<Curso>>(emptyList())

    val cursos: StateFlow<List<Curso>> =
        _cursos.asStateFlow()

    private val _cargando =
        MutableStateFlow(false)

    val cargando: StateFlow<Boolean> =
        _cargando.asStateFlow()

    private val _error =
        MutableStateFlow<String?>(null)

    val error: StateFlow<String?> =
        _error.asStateFlow()

    fun cargarCursos() {

        viewModelScope.launch {

            try {

                _cargando.value = true
                _error.value = null

                val resultado =
                    repository.obtenerCursos()

                _cursos.value = resultado

            } catch (e: Exception) {

                _error.value =
                    "Error al conectar con la API"

            } finally {

                _cargando.value = false
            }
        }
    }
}