package com.laneboy.exchangeratesapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laneboy.exchangeratesapp.R
import com.laneboy.exchangeratesapp.presentation.common.model.ExchangeRate
import com.laneboy.exchangeratesapp.presentation.common.model.exchangeRateFavoriteMock
import com.laneboy.exchangeratesapp.presentation.common.model.exchangeRateMock
import com.laneboy.exchangeratesapp.presentation.theme.Card
import com.laneboy.exchangeratesapp.presentation.theme.Secondary
import com.laneboy.exchangeratesapp.presentation.theme.Yellow
import com.laneboy.exchangeratesapp.presentation.theme.text14Medium
import com.laneboy.exchangeratesapp.presentation.theme.text16Semibold

@Composable
fun ExchangeRateItem(
    modifier: Modifier = Modifier,
    exchangeRate: ExchangeRate,
    showBaseCurrency: Boolean,
    onMarkerFavoriteClick: (ExchangeRate) -> Unit

) {
    Row(
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Card)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        val currencyText = if (showBaseCurrency) {
            "${exchangeRate.baseCurrency}/${exchangeRate.quotedCurrency}"
        } else exchangeRate.quotedCurrency
        Text(
            text = currencyText,
            style = text14Medium
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f),
            text = exchangeRate.cost.toString(),
            style = text16Semibold.copy(textAlign = TextAlign.End)
        )
        Icon(
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onMarkerFavoriteClick(exchangeRate) }
            ),
            painter = painterResource(
                if (exchangeRate.isFavorite) R.drawable.ic_favorites
                else R.drawable.ic_favorites_off
            ),
            tint = if (exchangeRate.isFavorite) Yellow else Secondary,
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun ExchangeRateItemPreview() {
    ExchangeRateItem(exchangeRate = exchangeRateMock, showBaseCurrency = false) {}
}

@Preview
@Composable
private fun ExchangeRateItemFavoritePreview() {
    ExchangeRateItem(exchangeRate = exchangeRateFavoriteMock, showBaseCurrency = true) {}
}
