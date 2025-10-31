package com.example.TugasPraktikum




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




