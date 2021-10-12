package com.pchpsky.diary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.theme.DiaryTheme
import com.pchpsky.diary.theme.green

@Composable
fun ProgressBar(isDisplayed: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DiaryTheme.colors.background)
            .drawBehind {  },
    ) {
        if (isDisplayed) {
            CircularProgressIndicator(
                color = green,
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.Center),
                strokeWidth = 10.dp,
            )
        }
    }
}
