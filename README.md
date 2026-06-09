# 🛰️ GS Carbono — Android

> **Global Solution FIAP 2025 — Tema: Indústria Espacial**

[![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?logo=android&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Navigation Compose](https://img.shields.io/badge/Navigation%20Compose-2.8.4-green)](https://developer.android.com/jetpack/compose/navigation)
[![Material Design 3](https://img.shields.io/badge/Material%20Design-3-blue)](https://m3.material.io/)

---

## 🎯 Objetivo da Solução

A plataforma **GS Carbono** conecta dados de emissão de CO₂ de diferentes setores industriais com **tecnologias espaciais** (dados de satélite, monitoramento remoto) para permitir rastreamento, análise e relatórios de impacto ambiental em escala global.

Alinhada aos **ODS 9, 11 e 13** da ONU — construída como protótipo funcional Android com Kotlin e Jetpack Compose.

---

## 🛰️ Contexto Espacial

Os dados apresentados no app são baseados em informações reais coletadas via rede de satélites de observação da Terra:

| Satélite | Operador | Função |
|---|---|---|
| Sentinel-5P | ESA (Europa) | Monitoramento de CO₂, metano e ozônio |
| DETER / PRODES | INPE (Brasil) | Detecção de desmatamento na Amazônia |
| Landsat-9 | NASA / USGS | Imagens multiespectrais da superfície |
| OCO-3 | NASA (ISS) | Observatório de carbono na ISS |
| GOES-16 | NOAA (EUA) | Clima em tempo real para as Américas |
| Amazonia-1 | INPE (Brasil) | Primeiro satélite de observação 100% brasileiro |

---



## 📱 Telas e Fluxo de Navegação

<p align="center">
  <img src="<img width="182" height="381" alt="Image" src="https://github.com/user-attachments/assets/5672a390-161f-4fe2-b287-a2cc7fa93699" />" width="18%" />
  <img src="URL_HOME" width="18%" />
  <img src="URL_EMISSOES" width="18%" />
  <img src="URL_SATELITES" width="18%" />
  <img src="URL_SOBRE" width="18%" />
</p>

<p align="center">
  Splash &nbsp;→&nbsp; Home &nbsp;→&nbsp; Emissões &nbsp;→&nbsp; Satélites &nbsp;→&nbsp; Sobre
</p>

A navegação é gerenciada pelo **Navigation Compose** com 5 telas e passagem de parâmetro via rota:

```
┌─────────┐     ┌──────────┐     ┌────────────────┐
│ Splash  │ ──▶ │   Home   │ ──▶ │    Emissões    │
└─────────┘     └──────────┘     └───────┬────────┘
                     │                   │
                     ▼                   ▼
               ┌──────────┐     ┌────────────────┐
               │ Satélites│     │ Detalhe Emissão│
               └──────────┘     └────────────────┘
                     │
                     ▼
               ┌──────────┐
               │  Sobre   │
               └──────────┘
```

## 🗂️ Estrutura do Projeto

```
app/
 └── src/
      └── main/
           ├── AndroidManifest.xml
           └── java/
                └── com/github/marcusdev01/gscarbono/
                     ├── MainActivity.kt
                     ├── data/
                     │    └── MockRepository.kt          # Dados mockados
                     ├── model/
                     │    ├── Emissao.kt                 # Data class emissão
                     │    └── Satelite.kt                # Data class satélite
                     ├── navigation/
                     │    ├── Screen.kt                  # Rotas sealed class
                     │    └── NavGraph.kt                # NavHost + composables
                     ├── screens/
                     │    ├── HomeScreen.kt              # Tela inicial
                     │    ├── EmissoesScreen.kt          # Lista com filtro
                     │    ├── EmissaoDetalheScreen.kt    # Detalhe (parâmetro)
                     │    ├── SatelitesScreen.kt         # Satélites ativos
                     │    └── SobreScreen.kt             # Sobre o projeto
                     └── ui/theme/
                          ├── Color.kt                   # Paleta espacial
                          ├── Theme.kt                   # MaterialTheme dark
                          └── Type.kt                    # Tipografia
```

---

## ✅ Requisitos Atendidos

| Requisito | Como foi implementado |
|---|---|
| **Tela inicial** | `HomeScreen` com nome da solução, descrição, identidade visual espacial |
| **Navigation Compose** | `NavGraph.kt` com 5 telas e parâmetro via rota (`emissao_detalhe/{emissaoId}`) |
| **Componentes Compose** | `Column`, `Row`, `Card`, `LazyColumn`, `Scaffold`, `FilterChip` |
| **Dados do projeto** | `MockRepository` com 6 emissões e 6 satélites baseados em fontes reais |
| **Interação do usuário** | Filtro por setor (FilterChip), clique nos cards, navegação entre telas, botão voltar |
| **Boas práticas** | Separação em `data/`, `model/`, `navigation/`, `screens/`, `ui/theme/` |

---

## 🚀 Como rodar

1. Clone o repositório ou descompacte o ZIP
2. Abra no **Android Studio Ladybug** (ou superior)
3. Aguarde o Gradle sync
4. Execute em um emulador com **API 24+** ou dispositivo físico

---

## 🧭 Navegação — Resumo das rotas

```kotlin
// Screen.kt
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Emissoes : Screen("emissoes")
    object EmissaoDetalhe : Screen("emissao_detalhe/{emissaoId}") {
        fun createRoute(emissaoId: Int) = "emissao_detalhe/$emissaoId"
    }
    object Satelites : Screen("satelites")
    object Sobre : Screen("sobre")
}
```

---

## 🛠️ Stack Técnica

- **Kotlin** — linguagem principal
- **Jetpack Compose** — UI declarativa
- **Navigation Compose 2.8.4** — navegação entre telas
- **Material Design 3** — componentes e tema
- **Scaffold + innerPadding** — estrutura de tela padrão
- **LazyColumn** — listas performáticas
- **FilterChip** — filtros interativos
- **Dados mockados** — baseados em fontes reais (INPE, ESA, NASA)

---

## 👤 Autor

**Marcus** — Android Developer  
Global Solution FIAP 2025 — Indústria Espacial  
Professor orientador: **Ewerton Luiz de Lima Carreira**
