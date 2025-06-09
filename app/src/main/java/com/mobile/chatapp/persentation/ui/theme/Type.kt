package com.mobile.chatapp.persentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mobile.chatapp.R


val zohoBlack = FontFamily(
    Font(R.font.zohoblack)
)

val zohoBold = FontFamily(
    Font(R.font.zohobold)
)

val zohoBoldItalic = FontFamily(
    Font(R.font.zohobolditalic)
)

val zohoExtraBlack = FontFamily(
    Font(R.font.zohoextrablack)
)

val zohoExtraBold = FontFamily(
    Font(R.font.zohoextrabold)
)

val zohoExtraLight = FontFamily(
    Font(R.font.zohoextralight)
)

val zohoLight = FontFamily(
    Font(R.font.zoholight)
)

val zohoMedium = FontFamily(
    Font(R.font.zohomedium)
)

val zohoRegular = FontFamily(
    Font(R.font.zohoregular)
)

val zohoRegularItalic = FontFamily(
    Font(R.font.zohoregularitalic)
)

val zohoSemiBold = FontFamily(
    Font(R.font.zohosemibold)
)

val zohoSemiBoldItalic = FontFamily(
    Font(R.font.zohosemibolditalic)
)

val zohoThin = FontFamily(
    Font(R.font.zohothin)
)


// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 17.sp,
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