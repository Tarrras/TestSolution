package com.youarelaunched.challenge.ui.screen.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

@Composable
fun EmptyDataMessage(modifier: Modifier = Modifier) =
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.msg_empty_data_title),
            color = VendorAppTheme.colors.buttonSelected,
            style = VendorAppTheme.typography.h2
        )
        Text(
            text = stringResource(id = R.string.msg_empty_data_title),
            color = VendorAppTheme.colors.textDark,
            style = VendorAppTheme.typography.subtitle2,
            textAlign = TextAlign.Center
        )
    }

@Preview
@Composable
fun EmptyDataMessagePreview() = VendorAppTheme {
    Surface {
        EmptyDataMessage(Modifier.padding(16.dp))
    }
}