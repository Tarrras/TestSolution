package com.youarelaunched.challenge.ui.screen.view.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

@Composable
fun SearchField(
    modifier: Modifier,
    query: String?,
    onQueryChanged: (String) -> Unit
) = Surface(
    modifier = modifier,
    elevation = 14.dp,
    shape = RoundedCornerShape(16.dp),
    color = VendorAppTheme.colors.background
) {
    OutlinedTextField(
        value = query ?: "",
        onValueChange = onQueryChanged,
        shape = RoundedCornerShape(16.dp),
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_field_placeholder),
                style = VendorAppTheme.typography.subtitle2,
                color = VendorAppTheme.colors.text
            )
        },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = stringResource(id = R.string.search_field_placeholder)
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            trailingIconColor = Color.Unspecified,
            unfocusedBorderColor = Color.Unspecified,
            focusedBorderColor = Color.Unspecified
        )
    )
}