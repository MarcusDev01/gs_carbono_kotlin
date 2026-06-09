package com.github.marcusdev01.gscarbono.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.github.marcusdev01.gscarbono.data.MockRepository
import com.github.marcusdev01.gscarbono.navigation.Screen
import com.github.marcusdev01.gscarbono.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val totalCo2 = MockRepository.getTotalCo2()
    val totalEmissoes = MockRepository.getEmissoes().size
    val totalSatelites = MockRepository.getSatelites().size

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "🛰️",
                            fontSize = 22.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "GS Carbono",
                            color = SpaceAccent,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SpaceDarkBlue
                )
            )
        },
        containerColor = SpaceBlack
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Hero Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(SpaceBlue, SpaceDarkBlue)
                        )
                    )
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "🌍",
                        fontSize = 56.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Monitoramento de Carbono\nvia Satélite",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 30.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Rastreando emissões de CO₂ com tecnologia espacial\nalinhado aos ODS 9, 11 e 13 da ONU",
                        color = SpaceGray,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Stats Row
            Text(
                text = "RESUMO GLOBAL",
                color = SpaceGray,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    label = "Total CO₂",
                    value = "${"%.1f".format(totalCo2)} t",
                    icon = "☁️",
                    color = SpaceOrange
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    label = "Registros",
                    value = "$totalEmissoes",
                    icon = "📊",
                    color = SpaceAccent
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    label = "Satélites",
                    value = "$totalSatelites",
                    icon = "🛰️",
                    color = SpaceGreen
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Navigation Buttons
            Text(
                text = "ACESSO RÁPIDO",
                color = SpaceGray,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            NavButton(
                icon = Icons.Default.List,
                title = "Emissões Registradas",
                subtitle = "$totalEmissoes eventos monitorados por satélite",
                onClick = { navController.navigate(Screen.Emissoes.route) }
            )
            Spacer(modifier = Modifier.height(10.dp))
            NavButton(
                icon = Icons.Default.Star,
                title = "Satélites Ativos",
                subtitle = "$totalSatelites satélites de monitoramento ambiental",
                onClick = { navController.navigate(Screen.Satelites.route) }
            )
            Spacer(modifier = Modifier.height(10.dp))
            NavButton(
                icon = Icons.Default.Info,
                title = "Sobre o Projeto",
                subtitle = "Global Solution FIAP 2025 — Indústria Espacial",
                onClick = { navController.navigate(Screen.Sobre.route) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Footer tag
            Text(
                text = "🚀 GS Carbono · FIAP 2025 · Indústria Espacial",
                color = SpaceGray,
                fontSize = 11.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun StatCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    icon: String,
    color: Color
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = icon, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                color = color,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = label,
                color = SpaceGray,
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun NavButton(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(SpaceBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = SpaceAccent,
                    modifier = Modifier.size(22.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
                Text(
                    text = subtitle,
                    color = SpaceGray,
                    fontSize = 12.sp
                )
            }
            Text(text = "›", color = SpaceGray, fontSize = 20.sp)
        }
    }
}
