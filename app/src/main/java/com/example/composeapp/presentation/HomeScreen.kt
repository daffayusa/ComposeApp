package com.example.composeapp.presentation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.R
import com.example.composeapp.component.CinemaCard
import com.example.composeapp.component.GenreChip
import com.example.composeapp.component.MainCard
import com.example.composeapp.component.MainCard2
import com.example.composeapp.component.MainCardContent
import com.example.composeapp.component.MovieBannerCard
import com.example.composeapp.component.SearchBarHome
import com.example.composeapp.data.CinemaData
import com.example.composeapp.data.MovieData
import com.example.composeapp.navigation.Screen
import com.example.composeapp.ui.theme.ComposeAppTheme
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(navController: NavHostController, modifier: Modifier) {
//    val navController = rememberNavController()
    var searchText by remember { mutableStateOf("") }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    val genres = listOf("All Movies", "Action", "Drama", "Horror", "Sci-Fi", "Comedy")
    var selectedGenre by remember { mutableStateOf("All Movies") }
    var searchQuery by remember { mutableStateOf("") }
    val movies = MovieData.dummyMovies
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.background
        ),
        startY = 0f,
        endY = 1000f
    )

    val filteredMovies = remember(selectedGenre){
        if ( selectedGenre == "All Movies"){
            MovieData.dummyMovies
        }else{
            MovieData.dummyMovies.filter { it.genre == selectedGenre }
        }
    }
    val moviePagerState = rememberPagerState(
        initialPage = 1,
        pageCount = { filteredMovies.size }
    )
    val filteredCinemas = remember(searchQuery) {
        if (searchQuery.isEmpty()) {
            CinemaData.dummyCinemas
        } else {
            CinemaData.dummyCinemas.filter { cinema ->
                val cityName = CinemaData.dummyCities.find { it.id == cinema.cityId }?.name ?: ""
                cityName.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 12.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            SearchBarHome(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholderText = "Cari Film",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            MovieBannerCard(
                modifier = Modifier.fillMaxWidth(),
                navController = navController
            )

            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.clapperboard),
                    contentDescription = null,

                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(28.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Now Showing",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
//            LazyColumn(
//                verticalArrangement = Arrangement.spacedBy(12.dp),
//                contentPadding = PaddingValues(bottom = 32.dp)
//            ) {
//                items(filteredCinemas) { cinema ->
//                    CinemaCard(cinema)
//                }
//            }

            // Hapus background color pada LazyVerticalGrid agar gradasi Box terlihat
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 0.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(genres){genre ->
                    GenreChip(
                        text = genre,
                        isSelected = genre == selectedGenre,
                        onClick = {selectedGenre = genre}
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
//            LazyVerticalGrid(
//                columns = GridCells.Adaptive(minSize = 120.dp),
//                modifier = Modifier.fillMaxSize(),
//                contentPadding = PaddingValues(top = 0.dp, bottom = 82.dp),
//                verticalArrangement = Arrangement.spacedBy(16.dp),
//                horizontalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                items(filteredMovies) { movie ->
//                    MainCard2(
//                        imageUrl = movie.imageUrl,
//                        title = movie.title,
//                        rating = movie.rating,
//                        desc = movie.desc,
//                        modifier = Modifier.fillMaxWidth() .clickable(){
//                            navController.navigate(Screen.Detail.route + "/${movie.id}")
//                        }
//                    )
//                }
//            }
            HorizontalPager(
                state = moviePagerState,
                modifier = Modifier.fillMaxWidth() ,
                contentPadding = PaddingValues(horizontal = 88.dp),
                pageSpacing = 4.dp,
                verticalAlignment = Alignment.CenterVertically
            ) { page ->
                val movie = filteredMovies[page]


                val pageOffset = (
                        (moviePagerState.currentPage - page) + moviePagerState.currentPageOffsetFraction
                        ).absoluteValue

                Card(
                    modifier = Modifier
                        .widthIn(min = 120.dp, max = 180.dp)
                        .aspectRatio(0.7f)
                        .padding(bottom = 12.dp)
                        .graphicsLayer {

                            val scale = lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                            scaleX = scale
                            scaleY = scale

                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }
                        .clickable {
                            navController.navigate(Screen.Detail.route + "/${movie.id}")
                        },
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    MainCardContent(movie = movie)
                }
            }
        }
    }


}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun HomeScreenPrev() {
    val navController = rememberNavController()
    ComposeAppTheme {
        HomeScreen(navController, modifier = Modifier)

    }
}