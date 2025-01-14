package com.looker.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheets(
    modifier: Modifier = Modifier,
    state: ModalBottomSheetState,
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        modifier = modifier,
        sheetElevation = 0.dp,
        sheetState = state,
        sheetContent = sheetContent,
        content = content,
        sheetBackgroundColor = MaterialTheme.colors.background,
        scrimColor = MaterialTheme.colors.background.copy(0.3f),
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    )
}

/**
 * [angle] = 2f is ArrowUp
 *
 * [angle] = 1f is Bar
 *
 * [angle] = 0f is ArrowDown
 */
@Composable
fun HandleIcon(
    angle: Float,
    backgroundColor: Color = MaterialTheme.colors.background,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        CanvasHandleIcon(
            modifier = Modifier
                .size(30.dp)
                .padding(6.dp),
            angle = angle
        )
    }
}

@Composable
fun CanvasHandleIcon(
    modifier: Modifier = Modifier,
    angle: Float,
    color: Color = MaterialTheme.colors.onBackground
) {
    val animateIcon by animateFloatAsState(angle)

    Canvas(modifier = modifier) {
        drawLine(
            color = color,
            strokeWidth = 5f,
            start = Offset(0f, animateIcon * center.y),
            end = Offset(center.x, center.y)
        )
        drawLine(
            color = color,
            strokeWidth = 5f,
            start = Offset(center.x, center.y),
            end = Offset(size.width, animateIcon * center.y)
        )
    }
}

sealed class SheetsState {
    object VISIBLE : SheetsState()
    object ToVISIBLE : SheetsState()
    object ToHIDDEN : SheetsState()
    object HIDDEN : SheetsState()
}