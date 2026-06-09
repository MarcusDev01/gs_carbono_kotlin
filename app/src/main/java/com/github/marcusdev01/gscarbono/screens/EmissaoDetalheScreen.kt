package com.github.marcusdev01.gscarbono.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.github.marcusdev01.gscarbono.data.MockRepository
import com.github.marcusdev01.gscarbono.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmissaoDetalheScreen(navController: NavHostController, emissaoId: Int) {
    val emissao = MockRepository.getEmissaoById(emissaoId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detalhes da Emissão",
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
        if (emissao == null) {
            Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("Emissão não encontrada", color = SpaceGray)
            }
            return@Scaffold
        }

        val co2Color = when {
            emissao.quantidadeCo2Toneladas > 1000 -> SpaceOrange
            emissao.quantidadeCo2Toneladas > 300 -> SpaceYellow
            else -> SpaceGreen
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Header Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = CardBackground)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = emissao.fonteEmissao,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        lineHeight = 26.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = "Emissão total", color = SpaceGray, fontSize = 12.sp)
                            Text(
                                text = "${"%.1f".format(emissao.quantidadeCo2Toneladas)} toneladas",
                                color = co2Color,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(co2Color.copy(alpha = 0.15f))
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Text(text = emissao.tipoGas, color = co2Color, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Info grid
            Text(
                text = "INFORMAÇÕES",
                color = SpaceGray,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                InfoDetailCard(modifier = Modifier.weight(1f), label = "Setor", value = emissao.setor, emoji = "🏭")
                InfoDetailCard(modifier = Modifier.weight(1f), label = "Data", value = emissao.dataEmissao, emoji = "📅")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                InfoDetailCard(modifier = Modifier.weight(1f), label = "Latitude", value = "${"%.4f".format(emissao.latitude)}°", emoji = "📍")
                InfoDetailCard(modifier = Modifier.weight(1f), label = "Longitude", value = "${"%.4f".format(emissao.longitude)}°", emoji = "🗺️")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Descrição
            Text(
                text = "DESCRIÇÃO",
                color = SpaceGray,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = CardBackground)
            ) {
                Text(
                    text = emissao.descricao,
                    color = Color.White,
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Contexto espacial
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SpaceBlue)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "🛰️", fontSize = 28.sp)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Monitorado via satélite",
                            color = SpaceAccent,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Dados coletados pela rede de satélites de observação da Terra em parceria com INPE e ESA.",
                            color = SpaceGray,
                            fontSize = 12.sp,
                            lineHeight = 17.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botão voltar
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SpaceAccent)
            ) {
                Text(text = "← Voltar para a lista", color = SpaceBlack, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun InfoDetailCard(modifier: Modifier = Modifier, label: String, value: String, emoji: String) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = emoji, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = label, color = SpaceGray, fontSize = 11.sp)
            Text(text = value, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
        }
    }
}
