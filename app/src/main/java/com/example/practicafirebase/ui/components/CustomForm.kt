package com.example.practicafirebase.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicafirebase.R
import com.example.practicafirebase.ui.theme.PracticaFirebaseTheme

@Composable
fun CustomForm(
    name: String,
    onNameChange: (String) -> Unit,
    price: String,
    onPriceChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    imageUrl: String,
    onImageUrlChange: (String) -> Unit
) {
    var error by remember { mutableStateOf(false) }

    TextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text(text = stringResource(R.string.label_name)) },
        maxLines = 1,
        modifier = Modifier.fillMaxWidth()
    )
    TextField(
        value = price,
        onValueChange = onPriceChange,
        label = { Text(text = stringResource(R.string.label_price)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        maxLines = 1,
        supportingText = {
            if (price.any { !it.isDigit() && it != '.'}) {
                Text(text = stringResource(R.string.error_price))
                error = true
            } else error = false
        },
        isError = error,
        modifier = Modifier.fillMaxWidth()
    )
    TextField(
        value = description,
        onValueChange = onDescriptionChange,
        label = { Text(text = stringResource(R.string.label_description)) },
        modifier = Modifier.fillMaxWidth().height(100.dp)
    )
    TextField(
        value = imageUrl,
        onValueChange = onImageUrlChange,
        label = { Text(text = stringResource(R.string.label_image_url)) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun CustomFormPreview() {
    PracticaFirebaseTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ){
            CustomForm(
                name = "",
                price = "",
                description = "",
                imageUrl = "",
                onNameChange = {},
                onPriceChange = {},
                onDescriptionChange = {},
                onImageUrlChange = {}
            )
        }
    }
}