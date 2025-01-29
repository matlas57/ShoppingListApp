package com.example.shoppinglistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListApp() {
    var sItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(64.dp),
        verticalArrangement = Arrangement.Center
    ) {
        //Creating state to track a list of ShoppingItems
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Add Item")
        }
        //A lazy column only renders information that is visible
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
        ){
            //items() defines the LazyColumn content to be a list
            items(sItems) {

            }
        }
    }

    if (showDialog) {
        BasicAlertDialog(
            onDismissRequest = { showDialog = false },
        ) {
            Column {
                Text("Add Item")

                OutlinedTextField(
                    value = itemName,
                    onValueChange = { itemName = it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                )

                OutlinedTextField(
                    value = itemQuantity,
                    onValueChange = { itemQuantity = it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                )

                Row (
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (itemName.isNotBlank()) {
                                val newItem = ShoppingItem(
                                    id = sItems.size + 1,
                                    name = itemName,
                                    quantity = itemQuantity.toInt(),
                                )
                                sItems = sItems + newItem
                                showDialog = false
                                itemName = ""
                                itemQuantity = ""
                            }
                        }
                    ) {
                        Text("Add")
                    }

                    Button(
                        onClick = {
                            showDialog = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}

data class ShoppingItem(
    val id: Int,
    var name: String,
    var quantity: Int,
    var isEditing: Boolean = false
)