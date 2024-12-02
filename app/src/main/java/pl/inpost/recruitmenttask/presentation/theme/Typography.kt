package pl.inpost.recruitmenttask.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import pl.inpost.recruitmenttask.R

val inPostRecruitmentTaskFonts = FontFamily(
    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_black, FontWeight.Black),
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_extrabold, FontWeight.ExtraBold),

)

// Set of Material typography styles to start with
val InPostRecruitmentTaskTypography = Typography(
    defaultFontFamily = inPostRecruitmentTaskFonts,
    h6 = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.4.sp,
        lineHeight = 24.sp
    )
)