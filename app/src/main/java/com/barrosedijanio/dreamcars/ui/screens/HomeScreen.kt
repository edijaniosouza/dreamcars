package com.barrosedijanio.dreamcars.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.barrosedijanio.dreamcars.R
import com.barrosedijanio.dreamcars.database.model.FavoriteCar
import com.barrosedijanio.dreamcars.service.model.Car

@Composable
fun HomeScreen(
    data: List<Car>,
    favoriteData: List<FavoriteCar>,
    onFavoriteCarSelected: (car: Car, isFav: Boolean) -> Unit
) {

    Scaffold { paddingInsets ->
        Column(
            modifier = Modifier
                .padding(paddingInsets)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("OLA MUNDO", fontWeight = FontWeight.Bold);
            LazyColumn {
                items(data) { item ->
                    Row(
                        Modifier.fillMaxWidth()
                    ) {
                        Text(item.nome_modelo)

                        val fav = favoriteData.find {
                            it.carId == item.id
                        }
                        val isFav = fav != null

                        IconButton(onClick = { onFavoriteCarSelected(item, isFav) }) {
                            Icon(
                                imageVector = if(isFav) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = stringResource(R.string.favorite)
                            )
                        }
                    }
                    HorizontalDivider()
                }
            }
        }
    }


}