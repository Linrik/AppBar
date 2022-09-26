package henrik.usn.appbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import henrik.usn.appbar.ui.theme.AppBarTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppBarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppBar()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var drawerList = listOf<String>("Hjem",
        "Artikkel",
        "Event",
        "Om oss")
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            //Title
            Text(text = "Root", modifier = Modifier.padding(16.dp))

            //content
            for(item in drawerList){
                Text(text = item, modifier = Modifier.padding(16.dp))
            }
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Root",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
        }
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun drawer() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var drawerList = listOf<String>("Hjem",
        "Artikkel",
        "Event",
        "Om oss")
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            //Title
            Text(text = "Root", modifier = Modifier.padding(16.dp))

            //content
            for(item in drawerList){
                Text(text = item, modifier = Modifier.padding(16.dp))
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Open or close drawer") },
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }
            )
        }
    ){
        // Screen content
    }
}

/*
@Preview(showBackground = true)
@Composable
fun DrawerPreview() {
    AppBarTheme {
        drawer()
    }
}*/


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppBarTheme {
        AppBar()
    }
}