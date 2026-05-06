package com.example.composeapp.component

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.ui.theme.ComposeAppTheme
import org.w3c.dom.Text

@Composable
fun SearchBarHome(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    placeholderText: String = "Cari..."
) {
    Column(modifier) {

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            placeholder = {
                Text(text = placeholderText, fontSize = 14.sp)
            },
            maxLines = 1,
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .height(25.dp)
                        .width(25.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp)),
            shape = RoundedCornerShape(10.dp),
            value = value,
            onValueChange = onValueChange,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                unfocusedBorderColor = Color.Transparent,
            ),
        )
    }

}

@Composable
fun GenreChip(
    text: String,
    isSelected : Boolean,
    onClick : ()-> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.clickable {onClick()},
        shape = RoundedCornerShape(10.dp),
        color = if(isSelected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.surfaceVariant,
        contentColor = if(isSelected) MaterialTheme.colorScheme.onPrimary
                        else MaterialTheme.colorScheme.onSurfaceVariant
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Preview
@Composable
private fun FieldPrev() {
    var searchText by remember { mutableStateOf("") }
    ComposeAppTheme {
        SearchBarHome(value = searchText, onValueChange = {
            searchText = it
        }, modifier = Modifier.padding(bottom = 24.dp))
    }
}