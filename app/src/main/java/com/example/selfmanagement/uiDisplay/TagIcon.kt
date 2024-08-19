package com.example.selfmanagement.uiDisplay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.selfmanagement.data.Tag

@Composable
fun TagIcon(tag: Tag){
    Row(
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(tag.background)
    ) {
        Text(
            text = "# ${tag.name}",
            fontSize = 14.sp,
            color = tag.color,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
        )
    }
}