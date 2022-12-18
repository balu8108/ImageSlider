package com.codingstuff.imageslider

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codingstuff.imageslider.ui.theme.ImageSliderTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageSliderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    //Greeting("Android")
                    BottomSheetScaffoldScreen()
                }
            }
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Details", fontSize = 18.sp) },
        backgroundColor = colorResource(id = R.color.colorPrimary),
        contentColor = Color.White
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetScaffoldScreen() {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        sheetContent = {
            BottomSheetContent()
        },
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetBackgroundColor = colorResource(id = R.color.github_black),
        // sheetPeekHeight = 0.dp,
        // scrimColor = Color.Red,  // Color for the fade background when you open/close the bottom sheet
    ) {
        Scaffold(
            topBar = { TopBar() },
            backgroundColor = colorResource(id = R.color.colorPrimaryDark)
        ) { padding ->  // We need to pass scaffold's inner padding to content. That's why we use Box.
            Box(modifier = Modifier.padding(padding)) {
                BottomSheetScaffoldMainScreen(scope = scope, state = bottomSheetScaffoldState)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BottomSheetScaffoldScreenPreview() {
    BottomSheetScaffoldScreen()
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetScaffoldMainScreen(scope: CoroutineScope, state: BottomSheetScaffoldState) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "    When Thor's evil brother, Loki (Tom Hiddleston), gains access to the unlimited power of the energy cube called the Tesseract, Nick Fury (Samuel L. Jackson), director of S.H.I.E.L.D., initiates a superhero recruitment effort to defeat the unprecedented threat to Earth. Joining Fury's \"dream team\" are Iron Man (Robert Downey Jr.), Captain America (Chris Evans), the Hulk (Mark Ruffalo), Thor (Chris Hemsworth), the Black Widow (Scarlett Johansson) and Hawkeye (Jeremy Renner).",
            color = Color.White
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.colorPrimary),
                contentColor = Color.White
            ),
            onClick = {
                scope.launch {
                    if (state.bottomSheetState.isCollapsed) {
                        state.bottomSheetState.expand()
                    } else {
                        state.bottomSheetState.collapse()
                    }
                }
            }) {
            if (state.bottomSheetState.isCollapsed) {
                Text(text = "Open Bottom Sheet Scaffold")
            } else {
                Text(text = "Close Bottom Sheet Scaffold")
            }
        }
    }
}

@Composable
fun BottomSheetContent() {
    val context = LocalContext.current
    Column {
        BottomSheetListItem(
            icon = R.drawable.ic_share,
            title = "Share",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
            })
        BottomSheetListItem(
            icon = R.drawable.ic_link,
            title = "Get link",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
            })
        BottomSheetListItem(
            icon = R.drawable.ic_edit,
            title = "Edit name",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
            })
        BottomSheetListItem(
            icon = R.drawable.ic_delete,
            title = "Delete collection",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
            })
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetContentPreview() {
    BottomSheetContent()
}

@Composable
fun BottomSheetListItem(icon: Int, title: String, onItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(title) })
            .height(55.dp)
            .background(color = colorResource(id = R.color.colorPrimary))
            .padding(start = 15.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = icon), contentDescription = "Share", tint = Color.White)
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = title, color = Color.White)
    }
}