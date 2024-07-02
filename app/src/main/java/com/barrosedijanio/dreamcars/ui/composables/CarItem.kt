package com.barrosedijanio.dreamcars.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barrosedijanio.dreamcars.R
import com.barrosedijanio.dreamcars.service.model.Car
import com.barrosedijanio.dreamcars.ui.theme.poppinsFontFamily
import com.barrosedijanio.dreamcars.util.formatToReal


@Composable
fun CarItem(modifier: Modifier = Modifier, item: Car, isFav: Boolean, favClicked: (Car) -> Unit) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .background(
                color = colorScheme.primary.copy(alpha = 0.8f),
                shape = ShapeDefaults.Medium
            )
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            CarItemDetails(
                icon = painterResource(id = R.drawable.car_door),
                text = "${item.num_portas} Portas"
            )
            CarItemDetails(icon = painterResource(id = R.drawable.fuel), text = item.combustivel)
            CarItemDetails(
                icon = painterResource(id = R.drawable.baseline_color_lens_24),
                text = item.cor
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = item.nome_modelo,
                color = colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                fontFamily = poppinsFontFamily
            )
            Text(
                text = item.valor.toString().formatToReal(),
                color = colorScheme.onPrimary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                fontFamily = poppinsFontFamily
            )
        }

        Button(
            onClick = { favClicked(item) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
        ) {
            if (isFav) Icon(
                imageVector = Icons.Filled.Done,
                contentDescription = null,
                tint = Color.Green
            )
            else Text(
                stringResource(R.string.i_want_it),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = poppinsFontFamily
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CarItemPreview() {
    CarItem(
        item = Car(
            id = 1,
            cor = "BEGE",
            ano = 2015,
            combustivel = "FLEX",
            modelo_id = 0,
            nome_modelo = "ONIX PLUS",
            num_portas = 4,
            timestamp_cadastro = 0L,
            valor = 50.0,
        ), isFav = true
    ) {}
}