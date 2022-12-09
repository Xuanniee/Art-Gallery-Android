package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    // Initialise Variables
    var artworkResourceIndex by remember { mutableStateOf(1) }
    var titleResourceIndex by remember { mutableStateOf(1) }
    var artistResourceIndex by remember { mutableStateOf(1) }
    var artworkDescriptionIndex by remember { mutableStateOf(1) }

    // Variable IDs
    var artworkResource = when (artworkResourceIndex) {
            1 -> R.drawable.bloodborne_deaths
            2 -> R.drawable.calendar_man
            3 -> R.drawable.courage
            4 -> R.drawable.courage_burger
            5 -> R.drawable.marco_the_phoenix
            6 -> R.drawable.mr_tiger
            7 -> R.drawable.nibbles
            else -> R.drawable.samoyed_
        }

    var titleResource = when (titleResourceIndex) {
            1 -> R.string.bloodborne_deaths
            2 -> R.string.calendar_man
            3 -> R.string.courage_dog
            4 -> R.string.courage_dog_burger
            5 -> R.string.marco_phoenix
            6 -> R.string.tiger_winnie
            7 -> R.string.nibbles_tom_jerry
            else -> R.string.samoyed
        }

    var artistResource = when (artistResourceIndex) {
            1,2,3,4,5,6,7 -> R.string.artist_1
            else -> R.string.artist_1
        }

    var artworkDescription = when (artworkDescriptionIndex) {
            1 -> R.string.bloodborne_deaths_desc
            2 -> R.string.calendar_man_desc
            3 -> R.string.courage_dog_desc
            4 -> R.string.courage_dog_burger_desc
            5 -> R.string.marco_phoenix_desc
            6 -> R.string.tiger_winnie_desc
            7 -> R.string.nibbles_tom_jerry_desc
            else -> R.string.samoyed_desc
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 5.dp)
    ) {
        // 1. Artwork Wall
        Spacer(modifier = Modifier.weight(0.5f))

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(15f, true)
                .fillMaxSize()
        ) {
            ArtworkWall(
                artworkResource = artworkResource,
                artworkDescription = artworkDescription
            )
        }

        Spacer(modifier = Modifier.height(15.dp))
        // 2. Artwork Description
        Row(
            modifier = Modifier
                .weight(8f, false)
        ) {
            ArtistDetails(
                titleResource = titleResource,
                artistResource = artistResource
            )
        }
        Spacer(modifier = Modifier.weight(5f))

        // 3. Display Controller
        Row(
            modifier = Modifier
                .weight(1.5f)
                .fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            DisplayController(
                artworkResourceIndex = artworkResourceIndex,
                artistResourceIndex = artistResourceIndex,
                titleResourceIndex = titleResourceIndex,
                artworkDescriptionIndex = artworkDescriptionIndex,
                onArtworkResourceChange = { artworkResourceIndex = it },
                onArtistResourceChange = { artistResourceIndex = it },
                onArtworkDescriptionChange = {artworkDescriptionIndex = it},
                onTitleResourceChange = {titleResourceIndex = it },
                modifier = Modifier.fillMaxSize()
            )
        }
    }

}

@Composable
fun ArtworkWall(
    @DrawableRes artworkResource: Int,
    @StringRes artworkDescription: Int
) {
    // Artwork Picture
    Image(
        painter = painterResource(id = artworkResource),
        contentDescription = stringResource(id = artworkDescription),
        modifier = Modifier
            .border(width = 5.dp, color = Color.DarkGray)
            .padding(all = 20.dp),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun ArtistDetails(
    titleResource: Int,
    artistResource: Int
) {
    // Details about Artist
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .border(width = 2.dp, color = Color.LightGray)
    ) {
        Surface(
            elevation = 50.dp,
            color = MaterialTheme.colors.background,
            border = BorderStroke(width = 5.dp, Color.LightGray),
            shape = RectangleShape
        ) {
            Column() {
                    Text(
                        text = stringResource(id = titleResource),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = stringResource(id = artistResource),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp),
                        textAlign = TextAlign.Center
                    )

            }
        }

    }

}

@Composable
fun DisplayController(
    artworkResourceIndex: Int,
    artistResourceIndex: Int,
    artworkDescriptionIndex: Int,
    titleResourceIndex: Int,
    onArtworkResourceChange: (Int) -> Unit,
    onArtistResourceChange: (Int) -> Unit,
    onArtworkDescriptionChange: (Int) -> Unit,
    onTitleResourceChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(

    ) {
        // 2 Buttons to traverse the Artworks
        Column(
            modifier = Modifier.weight(1f).fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Previous
            Button(
                // Similarly, replace 7 by the Number of Artworks - 1
                onClick = {
                    if (artworkResourceIndex != 1) {
                        onArtworkResourceChange(artworkResourceIndex - 1)
                        onArtistResourceChange(artistResourceIndex - 1)
                        onArtworkDescriptionChange(artworkDescriptionIndex - 1)
                        onTitleResourceChange(titleResourceIndex - 1)
                    }
                    else {
                        onArtworkResourceChange(artworkResourceIndex + 7)
                        onArtistResourceChange(artistResourceIndex + 7)
                        onArtworkDescriptionChange(artworkDescriptionIndex + 7)
                        onTitleResourceChange(titleResourceIndex + 7)
                    }
                },
                modifier = Modifier
                    .heightIn(min = 60.dp, max = 200.dp)
                    .widthIn(min = 90.dp, max = 180.dp)
            ) {
                Text(
                    text = stringResource(R.string.previous_button)
                )
            }
        }

        Column(
            modifier = Modifier.weight(1f).fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Next
            Button(
                onClick = {
                    // Replace 7 by the (Number of Artworks - 1) to improve Scalability
                    if (artworkResourceIndex <= 7) {
                        onArtworkResourceChange(artworkResourceIndex + 1)
                        onArtistResourceChange(artistResourceIndex + 1)
                        onArtworkDescriptionChange(artworkDescriptionIndex + 1)
                        onTitleResourceChange(titleResourceIndex + 1)
                    }
                    else {
                        onArtworkResourceChange(artworkResourceIndex - 7)
                        onArtistResourceChange(artistResourceIndex - 7)
                        onArtworkDescriptionChange(artworkDescriptionIndex - 7)
                        onTitleResourceChange(titleResourceIndex - 7)
                    }
                },
                modifier = Modifier
                    .heightIn(min = 60.dp, max = 120.dp)
                    .widthIn(min = 90.dp, max = 180.dp)
            ) {
                Text(
                    text = stringResource(R.string.next_button)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}