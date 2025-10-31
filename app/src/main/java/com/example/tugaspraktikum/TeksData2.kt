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




