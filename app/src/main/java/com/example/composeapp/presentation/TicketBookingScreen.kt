package com.example.composeapp.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketBookingScreen(
    movieId : Int,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val movieTitle = "Judul Film"

    var selectedDate by remember { mutableStateOf("18 Jun") }
    var selectedTime by remember { mutableStateOf("14:30") }
    val selectedSeats = remember { mutableStateListOf<String>() }

    val ticketPrice = 50000
    val totalPrice = selectedSeats.size * ticketPrice

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pilih Kursi", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                // Mencegah nabrak status bar atas
                modifier = Modifier.statusBarsPadding()
            )
        },
        bottomBar = {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 16.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .navigationBarsPadding() // Mencegah nabrak nav bar bawah
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Total Price", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                        Text(
                            text = "Rp $totalPrice",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Button(
                        onClick = { /* Lanjut ke Pembayaran */ },
                        enabled = selectedSeats.isNotEmpty(),
                        modifier = Modifier.height(48.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Beli Tiket")
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(movieTitle, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold)

            Spacer(modifier = Modifier.height(16.dp))

            // --- Tanggal Tayang ---
            val dates = listOf("17 Jun", "18 Jun", "19 Jun", "20 Jun", "21 Jun")
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(dates.size) { index ->
                    DateOrTimeChip(
                        label = dates[index],
                        isSelected = selectedDate == dates[index],
                        onClick = { selectedDate = dates[index] }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- Jam Tayang ---
            val times = listOf("10:00", "12:15", "14:30", "16:45", "19:00")
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(times.size) { index ->
                    DateOrTimeChip(
                        label = times[index],
                        isSelected = selectedTime == times[index],
                        onClick = { selectedTime = times[index] }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- Layar Bioskop ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(100))
            )
            Text(
                text = "Layar Bioskop",
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // --- Denah Kursi ---
            val rows = listOf("A", "B", "C", "D", "E")
            val columns = 6

            LazyVerticalGrid(
                columns = GridCells.Fixed(columns),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(rows.size * columns) { index ->
                    val rowIndex = index / columns
                    val colIndex = index % columns + 1
                    val seatId = "${rows[rowIndex]}$colIndex"

                    // Simulasi kursi yang sudah dipesan (Hardcode)
                    val isBooked = seatId in listOf("C3", "C4", "D5")
                    val isSelected = selectedSeats.contains(seatId)

                    SeatItem(
                        seatId = seatId,
                        isBooked = isBooked,
                        isSelected = isSelected,
                        onClick = {
                            if (!isBooked) {
                                if (isSelected) selectedSeats.remove(seatId)
                                else selectedSeats.add(seatId)
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // --- Legend (Keterangan Warna) ---
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                LegendItem("Tersedia", MaterialTheme.colorScheme.surfaceVariant)
                LegendItem("Dipilih", MaterialTheme.colorScheme.primary)
                LegendItem("Terisi", Color.DarkGray)
            }
        }
    }
}

// --- Komponen Bantuan ---

@Composable
fun DateOrTimeChip(label: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun SeatItem(seatId: String, isBooked: Boolean, isSelected: Boolean, onClick: () -> Unit) {
    val bgColor = when {
        isBooked -> Color.DarkGray
        isSelected -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.surfaceVariant
    }

    Box(
        modifier = Modifier
            .aspectRatio(1f) // Buat kotak presisi
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .clickable(enabled = !isBooked) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = seatId,
            color = if (isBooked || isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun LegendItem(label: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(16.dp).background(color, CircleShape))
        Spacer(modifier = Modifier.width(8.dp))
        Text(label, style = MaterialTheme.typography.labelMedium)
    }
}