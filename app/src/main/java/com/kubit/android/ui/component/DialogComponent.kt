package com.kubit.android.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.kubit.android.ui.theme.DialogBlue
import com.kubit.android.ui.theme.KubitTheme
import com.kubit.android.ui.theme.Shapes
import com.kubit.android.ui.theme.TextLight
import com.kubit.android.ui.util.ConvertUtil

@Composable
fun KubitMessagDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    title: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable (() -> Unit)? = null,
    shape: Shape = MaterialTheme.shapes.medium,
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
) {
    KubitTheme {
        Dialog(
            onDismissRequest = { onDismissRequest() },
            properties = DialogProperties()
        ) {
            Surface(
                modifier = modifier,
                shape = shape,
                color = backgroundColor,
                contentColor = contentColor
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp, top = 20.dp, end = 18.dp, bottom = 20.dp)
                ) {
                    title?.let {
                        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                            val textStyle = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold,
                                fontSize = ConvertUtil.dp2sp(dp = 20.dp),
                                color = TextLight
                            )
                            ProvideTextStyle(textStyle, it)
                        }
                    }
                    Spacer(modifier = Modifier.height(if (title == null) 10.dp else 30.dp))
                    text?.let {
                        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                            val textStyle = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Normal,
                                fontSize = ConvertUtil.dp2sp(dp = 16.dp),
                                color = TextLight
                            )
                            ProvideTextStyle(textStyle, it)
                        }
                    }
                    Spacer(modifier = Modifier.height(if (title == null) 20.dp else 30.dp))
                    Box(
                        Modifier.fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            if (dismissButton != null) {
                                dismissButton()
                                Spacer(Modifier.width(36.dp))
                                confirmButton()
                            } else {
                                confirmButton()
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * 사용자에게 메시지를 전달하기 위해 사용하는 Dialog
 *
 * @param title                 다이얼로그 타이틀
 * @param message               다이얼로그 메시지
 * @param onDismissRequest      확인 버튼 콜백 리스너
 * @param isDarkTheme           다크 모드 여부
 */
@Composable
fun MessageDialog(
    title: String,
    message: String,
    onDismissRequest: () -> Unit,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    KubitTheme {
        KubitMessagDialog(
            onDismissRequest = { onDismissRequest() },
            title = {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    color = TextLight,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            text = {
                Text(
                    text = message,
                    textAlign = TextAlign.Center,
                    color = TextLight,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                TextButton(
                    onClick = { onDismissRequest() },
                ) {
                    Text(
                        text = "확인",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(42.dp)
                            .background(color = DialogBlue, shape = Shapes.small)
                    )
                }
            },
            modifier = Modifier
                .width(334.dp)
                .background(color = Color.White, shape = Shapes.large)
        )
    }
}


/**
 * 사용자에게 메시지를 전달하기 위해 사용하는 Dialog
 *
 * @param message               다이얼로그 메시지
 * @param onDismissRequest      확인 버튼 콜백 리스너
 * @param isDarkTheme           다크 모드 여부
 */
@Composable
fun MessageDialog(
    message: String,
    onDismissRequest: () -> Unit,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    KubitTheme {
        KubitMessagDialog(
            onDismissRequest = { onDismissRequest() },
            text = {
                Text(
                    text = message,
                    textAlign = TextAlign.Center,
                    color = TextLight,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                TextButton(
                    onClick = { onDismissRequest() },
                ) {
                    Text(
                        text = "확인",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(42.dp)
                            .background(color = DialogBlue, shape = Shapes.small)
                    )
                }
            },
            modifier = Modifier
                .width(334.dp)
                .background(color = Color.White, shape = Shapes.large)
        )
    }
}

@Composable
@Preview
fun UiComponentPreview() {
    MessageDialog(title = "타이틀", message = "와이파이에 연결해 주삼~~", onDismissRequest = { })
//    MessageDialog(message = "와이파이에 연결해 주삼~~", onDismissRequest = { })
}