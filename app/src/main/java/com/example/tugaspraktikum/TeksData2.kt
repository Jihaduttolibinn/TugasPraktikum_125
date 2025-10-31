package com.example.TugasPraktikum

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay



@Composable
fun FormDataDiriStyled(modifier: Modifier = Modifier) {
    // states
    var namaInput by remember { mutableStateOf("") }
    var alamatInput by remember { mutableStateOf("") }
    var genderInput by remember { mutableStateOf("") }
    var statusInput by remember { mutableStateOf("") }

    var submittedNama by remember { mutableStateOf("") }
    var submittedGender by remember { mutableStateOf("") }
    var submittedStatus by remember { mutableStateOf("") }
    var submittedAlamat by remember { mutableStateOf("") }

    var isSubmitted by remember { mutableStateOf(false) }

    val genders = listOf("Laki-laki", "Perempuan")
    val statuses = listOf("Janda", "Lajang", "Duda")

    // visuals
    val headerGradient = Brush.verticalGradient(colors = listOf(Color(0xFFCE93D8), Color(0xFFE1BEE7)))
    val primaryPurple = Color(0xFF8E24AA)
    val labelColorOnWhite = Color(0xFF555555)
    val cardResultBg = Color(0xFF222222)
    val cardResultText = Color.White

    // scroll state untuk form + result (di dalam card)
    val scrollState = rememberScrollState()

    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8EAF6))
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp)
        ) {
            // Parent Card yang berisi header + form + hasil
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(18.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(scrollState) // semua konten di-card bisa discroll
                ) {
                    // Header (bagian atas card)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp))
                            .background(headerGradient),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Formulir Pendaftaran",
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    // Isi form (langsung di bawah header)
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // Nama
                        Text(text = "NAMA LENGKAP", color = labelColorOnWhite)
                        OutlinedTextField(
                            value = namaInput,
                            onValueChange = { namaInput = it },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("Isi nama lengkapnya") }
                        )
                        // Jenis Kelamin (vertikal)
                        Text(text = "JENIS KELAMIN", color = labelColorOnWhite)
                        Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                            genders.forEach { gender ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .selectable(
                                            selected = (genderInput == gender),
                                            onClick = { genderInput = gender }
                                        )
                                        .padding(vertical = 1.dp)
                                ) {
                                    RadioButton(
                                        selected = (genderInput == gender),
                                        onClick = { genderInput = gender }
                                    )
                                    Text(text = gender, modifier = Modifier.padding(start = 8.dp), color = Color.Black)
                                }
                            }
                        }
                        // Status Perkawinan
                        Text(text = "STATUS PERKAWINAN", color = labelColorOnWhite)
                        Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                            statuses.forEach { status ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .selectable(
                                            selected = (statusInput == status),
                                            onClick = { statusInput = status }
                                        )
                                        .padding(vertical = 1.dp)
                                ) {
                                    RadioButton(
                                        selected = (statusInput == status),
                                        onClick = { statusInput = status }
                                    )
                                    Text(text = status, modifier = Modifier.padding(start = 8.dp), color = Color.Black)
                                }
                            }
                        }
                        // Alamat
                        Text(text = "ALAMAT", color = labelColorOnWhite)
                        OutlinedTextField(
                            value = alamatInput,
                            onValueChange = { alamatInput = it },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("Alamat") }
                        )
                        Spacer(modifier = Modifier.height(6.dp))

                        // Submit
                        Button(
                            onClick = {
                                // simpan data ke state submitted dan tandai sudah submit
                                submittedNama = namaInput.trim()
                                submittedGender = genderInput
                                submittedStatus = statusInput
                                submittedAlamat = alamatInput.trim()
                                isSubmitted = true
                            },
                            enabled = namaInput.isNotBlank() && alamatInput.isNotBlank(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .clip(RoundedCornerShape(24.dp)),
                            colors = ButtonDefaults.buttonColors(containerColor = primaryPurple)
                        ) {
                            Text(text = "Submit", color = Color.White)
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        // Hasil — sekarang berada di dalam card, tepat di bawah form
                        if (isSubmitted) {
                            ElevatedCard(
                                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                                colors = CardDefaults.cardColors(containerColor = cardResultBg),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 12.dp),
                                    verticalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Text(text = "Nama   : ${submittedNama.ifBlank { "-" }}", color = cardResultText)
                                    Text(text = "Gender : ${submittedGender.ifBlank { "-" }}", color = cardResultText)
                                    Text(text = "Status : ${submittedStatus.ifBlank { "-" }}", color = cardResultText)
                                    Text(text = "Alamat : ${submittedAlamat.ifBlank { "-" }}", color = cardResultText)
                                }
                            }
                        }

                    }
                }
            }







