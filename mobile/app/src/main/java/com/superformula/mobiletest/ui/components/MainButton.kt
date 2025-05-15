package com.superformula.mobiletest.ui.components
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun HomeButton(
    text: String,
    style: MainButtonStyle = MainButtonDefaults.style(),
            onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = style.backgroundColor,
            contentColor = style.textColor
        ),
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

data class MainButtonStyle(
    val backgroundColor: Color,
    val textColor: Color
)

object MainButtonDefaults {
    @Composable
    fun style() = MainButtonStyle(
        backgroundColor = Color(0xFF425D8A),
        textColor = Color.White
    )
}