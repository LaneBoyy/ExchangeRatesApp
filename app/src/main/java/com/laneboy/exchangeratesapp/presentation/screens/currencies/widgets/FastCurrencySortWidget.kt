package com.laneboy.exchangeratesapp.presentation.screens.currencies.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.laneboy.exchangeratesapp.R
import com.laneboy.exchangeratesapp.domain.model.BaseCurrency
import com.laneboy.exchangeratesapp.presentation.theme.LightPrimary
import com.laneboy.exchangeratesapp.presentation.theme.Primary
import com.laneboy.exchangeratesapp.presentation.theme.Secondary
import com.laneboy.exchangeratesapp.presentation.theme.White
import com.laneboy.exchangeratesapp.presentation.theme.text14Medium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FastCurrencySortWidget(
    modifier: Modifier = Modifier,
    currencies: List<BaseCurrency>,
    selectedCurrency: BaseCurrency,
    onCurrencySelect: (BaseCurrency) -> Unit,
) {
    val expandedMenu = remember { mutableStateOf(false) }
    val selectedCurrencyBoxShape = if (expandedMenu.value) {
        RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
    } else {
        RoundedCornerShape(8.dp)
    }

    ExposedDropdownMenuBox(
        modifier = modifier.height(48.dp),
        expanded = expandedMenu.value,
        onExpandedChange = { expandedMenu.value = it },
    ) {
        SelectedCurrencyBox(
            modifier = Modifier.menuAnchor(type = MenuAnchorType.PrimaryNotEditable),
            selectedCurrency = selectedCurrency,
            shape = selectedCurrencyBoxShape,
            expanded = expandedMenu.value
        )
        ExposedDropdownMenu(
            modifier = modifier,
            containerColor = White,
            shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
            border = BorderStroke(
                width = 1.dp,
                color = Secondary
            ),
            expanded = expandedMenu.value,
            onDismissRequest = { expandedMenu.value = false }
        ) {
            currencies.forEach { currency ->
                SortCurrencyItem(
                    currency = currency,
                    isSelected = currency == selectedCurrency,
                    onClick = {
                        expandedMenu.value = false
                        onCurrencySelect(currency)
                    }
                )
            }
        }
    }
}

@Composable
private fun SelectedCurrencyBox(
    modifier: Modifier = Modifier,
    shape: Shape,
    selectedCurrency: BaseCurrency,
    expanded: Boolean
) {
    BasicTextField(
        modifier = modifier
            .fillMaxSize()
            .clip(shape)
            .background(White)
            .border(
                width = 1.dp,
                color = Secondary,
                shape = shape
            )
            .padding(horizontal = 16.dp),
        value = selectedCurrency.name,
        onValueChange = {},
        readOnly = true,
        textStyle = text14Medium,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    innerTextField()
                }
                Icon(
                    painter = painterResource(
                        if (expanded) R.drawable.ic_arrow_up
                        else R.drawable.ic_arrow_down
                    ),
                    tint = Primary,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
private fun SortCurrencyItem(
    modifier: Modifier = Modifier,
    currency: BaseCurrency,
    isSelected: Boolean,
    onClick: (BaseCurrency) -> Unit
) {
    val backgroundColor = if (isSelected) LightPrimary else White
    Box(
        modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(backgroundColor)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onClick(currency) }
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = currency.name,
            style = text14Medium
        )
    }
}

fun Modifier.crop(
    horizontal: Dp = 0.dp,
    vertical: Dp = 0.dp,
): Modifier = this.layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    fun Dp.toPxInt(): Int = this.toPx().toInt()

    layout(
        placeable.width - (horizontal * 2).toPxInt(),
        placeable.height - (vertical * 2).toPxInt()
    ) {
        placeable.placeRelative(-horizontal.toPx().toInt(), -vertical.toPx().toInt())
    }
}

@Preview
@Composable
private fun FastCurrencyFilterWidgetPreview() {
    FastCurrencySortWidget(
        selectedCurrency = BaseCurrency.EUR,
        currencies = listOf(),
        onCurrencySelect = {}
    )
}