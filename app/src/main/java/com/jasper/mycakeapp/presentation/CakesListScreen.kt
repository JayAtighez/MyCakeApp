package com.jasper.mycakeapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jasper.mycakeapp.data.Cake

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CakesListScreen(cakeViewModel: CakeViewModel) {

    val cakesList by cakeViewModel.cakeList.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier.shadow(elevation = 12.dp),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Cake List", fontSize = 20.sp, color = Color.Black)
                }
            )
        }
    ) { padding ->

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(cakesList.data.orEmpty()){ cakeItem ->
                CakeItem(cakeItem)
            }

        }
    }
}

@Composable
fun CakeItem(cake: Cake) {

    val image = ImageRequest.Builder(LocalContext.current)
        .data(cake.image)
        .crossfade(true)
        .build()

    Card(
        modifier = Modifier.fillMaxWidth(0.9f),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(all = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            AsyncImage(
                model = image,
                contentDescription = null,
                modifier = Modifier.size(160.dp)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = cake.title, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                Text(text = cake.desc, fontSize = 13.sp)
            }
        }
    }
}