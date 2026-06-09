package com.github.marcusdev01.gscarbono.model

data class Emissao(
    val id: Int,
    val fonteEmissao: String,
    val quantidadeCo2Toneladas: Double,
    val dataEmissao: String,
    val tipoGas: String,
    val descricao: String,
    val latitude: Double,
    val longitude: Double,
    val setor: String
)
