package com.app.priteshjetpack.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

inline fun <T> LazyListScope.lazyGrid(
    items: List<T>,
    rowSize: Int = 1,
    padding: Int = 8,
    modifier: Modifier,
    crossinline content: @Composable (item: T, modifier: Modifier) -> Unit,
) {
    items(items.windowed(rowSize, rowSize, true)) {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            it.forEach { item ->
                val itemModifier = Modifier.weight(1f)
                content.invoke(item, itemModifier)
                Spacer(modifier = Modifier.padding((padding / 2).dp))
            }
            repeat(rowSize - it.size) {
                Box(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.padding((padding / 2).dp))
    }
}