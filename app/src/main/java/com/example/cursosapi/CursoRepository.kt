package com.example.cursosapi

class CursoRepository {

    private val api = RetrofitClient.api

    suspend fun obtenerCursos(): List<Curso> {
        return api.obtenerCursos()
    }
}