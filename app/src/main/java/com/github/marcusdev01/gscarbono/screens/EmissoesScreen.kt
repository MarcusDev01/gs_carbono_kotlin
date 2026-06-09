package com.github.marcusdev01.gscarbono.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.github.marcusdev01.gscarbono.data.MockRepository
import com.github.marcusdev01.gscarbono.model.Emissao
import com.github.marcusdev01.gscarbono.navigation.Screen
import com.github.marcusdev01.gscarbono.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmissoesScreen(navController: NavHostController) {
    var setorSelecionado by remember { mutableStateOf("Todos") }
    val setores = MockRepository.getSetores()
    val emissoes = MockRepository.getEmissoesBySetor(setorSelecionado)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Emissões Registradas",
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
        ) {
            // Filtros por setor
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                Text(
                    text = "FILTRAR POR SETOR",
                    color = SpaceGray,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    setores.forEach { setor ->
                        val selecionado = setor == setorSelecionado
                        FilterChip(
                            selected = selecionado,
                            onClick = { setorSelecionado = setor },
                            label = {
                                Text(
                                    text = setor,
                                    fontSize = 12.sp,
                                    color = if (selecionado) SpaceBlack else SpaceGray
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = SpaceAccent,
                                containerColor = SpaceBlue
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${emissoes.size} resultado(s) encontrado(s)",
                    color = SpaceGray,
                    fontSize = 12.sp
                )
            }

            // Lista de emissões
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(emissoes) { emissao ->
                    EmissaoCard(
                        emissao = emissao,
                        onClick = {
                            navController.navigate(Screen.EmissaoDetalhe.createRoute(emissao.id))
                        }
                    )
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}

@Composable
fun EmissaoCard(emissao: Emissao, onClick: () -> Unit) {
    val co2Color = when {
        emissao.quantidadeCo2Toneladas > 1000 -> SpaceOrange
        emissao.quantidadeCo2Toneladas > 300 -> SpaceYellow
        else -> SpaceGreen
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = emissao.fonteEmissao,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(co2Color.copy(alpha = 0.15f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "${"%.0f".format(emissao.quantidadeCo2Toneladas)} t",
                        color = co2Color,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = emissao.descricao,
                color = SpaceGray,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoTag(emoji = "🏭", label = emissao.setor)
                InfoTag(emoji = "📅", label = emissao.dataEmissao)
                InfoTag(emoji = "☁️", label = emissao.tipoGas)
            }
        }
    }
}

@Composable
private fun InfoTag(emoji: String, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = emoji, fontSize = 11.sp)
        Spacer(modifier = Modifier.width(3.dp))
        Text(text = label, color = SpaceGray, fontSize = 11.sp)
    }
}
