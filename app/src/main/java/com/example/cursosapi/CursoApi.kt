package com.example.cursosapi

import retrofit2.http.GET

interface CursoApi {

    @GET("posts")
    suspend fun obtenerCursos(): List<Curso>
}