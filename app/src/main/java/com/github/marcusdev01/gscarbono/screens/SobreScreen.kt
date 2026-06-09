package com.github.marcusdev01.gscarbono.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.github.marcusdev01.gscarbono.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SobreScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Sobre o Projeto",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar",
                            tint = SpaceAccent
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SpaceDarkBlue)
            )
        },
        containerColor = SpaceBlack
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo / Hero
            Text(text = "🛰️", fontSize = 64.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "GS Carbono",
                color = SpaceAccent,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
            Text(
                text = "Monitoramento de Emissões via Satélite",
                color = SpaceGray,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            SobreSection(
                emoji = "🎯",
                titulo = "Objetivo da Solução",
                conteudo = "A plataforma GS Carbono conecta dados de emissão de CO₂ de diferentes setores industriais com tecnologias espaciais (dados de satélite, monitoramento remoto) para permitir rastreamento, análise e relatórios de impacto ambiental em escala global."
            )

            Spacer(modifier = Modifier.height(12.dp))

            SobreSection(
                emoji = "🌍",
                titulo = "ODS Alinhados",
                conteudo = "• ODS 9 — Indústria, Inovação e Infraestrutura\n• ODS 11 — Cidades e Comunidades Sustentáveis\n• ODS 13 — Ação Climática"
            )

            Spacer(modifier = Modifier.height(12.dp))

            SobreSection(
                emoji = "🛰️",
                titulo = "Tecnologia Espacial",
                conteudo = "Dados coletados via rede de satélites de observação da Terra, incluindo Sentinel-5P (ESA), DETER/PRODES (INPE), Landsat-9 (NASA) e OCO-3 (NASA/ISS), permitindo monitoramento em escala global com precisão orbital."
            )

            Spacer(modifier = Modifier.height(12.dp))

            SobreSection(
                emoji = "📱",
                titulo = "Stack Mobile",
                conteudo = "• Kotlin + Jetpack Compose\n• Navigation Compose\n• Material Design 3\n• Scaffold + LazyColumn\n• Dados mockados baseados em fontes reais"
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Autor Card


            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SpaceAccent)
            ) {
                Text(text = "← Voltar para o início", color = SpaceBlack, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun SobreSection(emoji: String, titulo: String, conteudo: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = emoji, fontSize = 20.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = titulo,
                    color = SpaceAccent,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = conteudo,
                color = SpaceGray,
                fontSize = 13.sp,
                lineHeight = 20.sp
            )
        }
    }
}
