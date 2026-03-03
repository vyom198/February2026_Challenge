package com.vs.february2026_challenge.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vs.february2026_challenge.R
val figtree = FontFamily(
    fonts = listOf(
        androidx.compose.ui.text.font.Font(
            resId = R.font.figtree_variablefont_wght,
        )
    )
)
val SansHeadline = FontFamily(
    fonts = listOf(
       androidx.compose.ui.text.font.Font(
           resId = R.font.stacksansheadline_variablefont_wght,
       )
    )
)
val inter = FontFamily(
    fonts = listOf(
        androidx.compose.ui.text.font.Font(
            resId = R.font.inter_variablefont_opsz_wght
        )
    )
)
val Typography.figtitle : TextStyle
    get() = TextStyle(
        fontFamily = figtree,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp,

        )
val Typography.figregular : TextStyle
    get() = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,

        )
val Typography.title : TextStyle
    get() = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 32.sp,

    )

val Typography.regular : TextStyle
    get() = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp

        )
val Typography.subtitle : TextStyle
    get() = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 32.sp,

        )
val Typography.lastactivetitle : TextStyle
    get() = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 20.sp,

        )

val Typography.buttonTitle : TextStyle
    get() = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        lineHeight = 24.sp,

        )
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = SansHeadline,
        fontWeight = FontWeight.Medium,
        fontSize = 25.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)