package com.github.marcusdev01.gscarbono.data

import com.github.marcusdev01.gscarbono.model.Emissao
import com.github.marcusdev01.gscarbono.model.Satelite

object MockRepository {

    fun getEmissoes(): List<Emissao> = listOf(
        Emissao(
            id = 1,
            fonteEmissao = "Lançamento Rocket Lab - Electron",
            quantidadeCo2Toneladas = 120.5,
            dataEmissao = "2025-06-01",
            tipoGas = "CO₂",
            descricao = "Emissão registrada via sensor orbital durante lançamento comercial",
            latitude = -23.5505,
            longitude = -46.6333,
            setor = "Indústria Espacial"
        ),
        Emissao(
            id = 2,
            fonteEmissao = "SpaceX Falcon 9 - Starlink",
            quantidadeCo2Toneladas = 340.0,
            dataEmissao = "2025-05-28",
            tipoGas = "CO₂",
            descricao = "Missão de reposição da constelação Starlink com 60 satélites",
            latitude = 28.5721,
            longitude = -80.6480,
            setor = "Telecomunicações Espaciais"
        ),
        Emissao(
            id = 3,
            fonteEmissao = "Usina Termelétrica Cubatão",
            quantidadeCo2Toneladas = 1240.0,
            dataEmissao = "2025-05-25",
            tipoGas = "CO₂",
            descricao = "Dados coletados via satélite Sentinel-5P (ESA) sobre emissões industriais",
            latitude = -23.8908,
            longitude = -46.4246,
            setor = "Energia"
        ),
        Emissao(
            id = 4,
            fonteEmissao = "Desmatamento Amazônia - PA",
            quantidadeCo2Toneladas = 8500.0,
            dataEmissao = "2025-05-20",
            tipoGas = "CO₂ + CH₄",
            descricao = "Monitoramento via satélite DETER/INPE — área desmatada: 3.200 ha",
            latitude = -4.4784,
            longitude = -54.5502,
            setor = "Desmatamento"
        ),
        Emissao(
            id = 5,
            fonteEmissao = "Porto de Santos - Navegação",
            quantidadeCo2Toneladas = 567.3,
            dataEmissao = "2025-05-15",
            tipoGas = "CO₂ + NOₓ",
            descricao = "Emissões de navios no maior porto da América Latina, monitoradas via AIS e satélite",
            latitude = -23.9618,
            longitude = -46.3322,
            setor = "Transporte Marítimo"
        ),
        Emissao(
            id = 6,
            fonteEmissao = "Arianegroup Ariane 6",
            quantidadeCo2Toneladas = 210.7,
            dataEmissao = "2025-05-10",
            tipoGas = "CO₂ + H₂O",
            descricao = "Primeiro lançamento comercial europeu do ano, carga útil de 7 toneladas",
            latitude = 5.2367,
            longitude = -52.7697,
            setor = "Indústria Espacial"
        ),
    )

    fun getSatelites(): List<Satelite> = listOf(
        Satelite(
            id = 1,
            nome = "Sentinel-5P",
            operador = "ESA (Europa)",
            altitude = "824 km",
            status = "🟢 Ativo",
            tipoOrbita = "LEO - Polar",
            descricao = "Monitora poluição atmosférica, CO₂, metano e ozônio em escala global"
        ),
        Satelite(
            id = 2,
            nome = "DETER / PRODES",
            operador = "INPE (Brasil)",
            altitude = "776 km",
            status = "🟢 Ativo",
            tipoOrbita = "LEO - Heliossíncrona",
            descricao = "Sistema brasileiro de monitoramento do desmatamento na Amazônia"
        ),
        Satelite(
            id = 3,
            nome = "Landsat-9",
            operador = "NASA / USGS (EUA)",
            altitude = "705 km",
            status = "🟢 Ativo",
            tipoOrbita = "LEO - Polar",
            descricao = "Imagens multiespectrais para monitoramento de mudanças na superfície terrestre"
        ),
        Satelite(
            id = 4,
            nome = "OCO-3",
            operador = "NASA (EUA)",
            altitude = "408 km",
            status = "🟢 Ativo",
            tipoOrbita = "LEO - ISS",
            descricao = "Observatório de carbono atmosférico acoplado à Estação Espacial Internacional"
        ),
        Satelite(
            id = 5,
            nome = "GOES-16",
            operador = "NOAA (EUA)",
            altitude = "35.786 km",
            status = "🟢 Ativo",
            tipoOrbita = "GEO",
            descricao = "Monitoramento climático em tempo quase real para as Américas"
        ),
        Satelite(
            id = 6,
            nome = "Amazonia-1",
            operador = "INPE (Brasil)",
            altitude = "752 km",
            status = "🟡 Parcial",
            tipoOrbita = "LEO - Polar",
            descricao = "Primeiro satélite de observação da Terra totalmente desenvolvido pelo Brasil"
        ),
    )

    fun getSetores(): List<String> = listOf(
        "Todos",
        "Indústria Espacial",
        "Telecomunicações Espaciais",
        "Energia",
        "Desmatamento",
        "Transporte Marítimo"
    )

    fun getEmissoesBySetor(setor: String): List<Emissao> {
        if (setor == "Todos") return getEmissoes()
        return getEmissoes().filter { it.setor == setor }
    }

    fun getTotalCo2(): Double = getEmissoes().sumOf { it.quantidadeCo2Toneladas }

    fun getEmissaoById(id: Int): Emissao? = getEmissoes().find { it.id == id }
}
