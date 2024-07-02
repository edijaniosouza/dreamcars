package com.barrosedijanio.dreamcars.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.barrosedijanio.dreamcars.R
import com.barrosedijanio.dreamcars.database.model.FavoriteCar
import com.barrosedijanio.dreamcars.service.model.Car
import com.barrosedijanio.dreamcars.ui.composables.CarItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    data: List<Car>,
    favoriteData: List<FavoriteCar>,
    onExitApp: () -> Unit,
    onFavoriteCarSelected: (car: Car, isFav: Boolean) -> Unit,
) {
    var favFilter by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onExitApp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = stringResource(
                                id = R.string.exit
                            )
                        )
                    }
                },
                title = { Text(text = stringResource(id = R.string.app_name)) },
                actions = {
                    IconButton(
                        onClick = {
                            favFilter = !favFilter
                            Log.i("testFavorite", "HomeScreen: $favFilter")
                        }) {
                        Icon(
                            imageVector = if (favFilter) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = stringResource(
                                id = R.string.favorite
                            )
                        )
                    }
                }
            )
        }
    ) { paddingInsets ->
        Column(
            modifier = Modifier
                .padding(paddingInsets)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (favFilter) Text(modifier = Modifier.padding(7.dp), text = "Filtro aplicado")
            if (favFilter) {
                LazyColumn {
                    items(data) { item ->
                        val fav = favoriteData.find {
                            it.carId == item.id
                        }
                        val isFav = fav != null

                        if (isFav) {
                            CarItem(
                                item = item,
                                isFav = true,
                                favClicked = { onFavoriteCarSelected(item, isFav) })
                        }
                    }
                }
            } else {
                LazyColumn {
                    items(data) { item ->
                        val fav = favoriteData.find {
                            it.carId == item.id
                        }
                        val isFav = fav != null
                        CarItem(
                            item = item,
                            isFav = isFav,
                            favClicked = { onFavoriteCarSelected(item, isFav) })
                    }
                }
            }


        }
    }


}

