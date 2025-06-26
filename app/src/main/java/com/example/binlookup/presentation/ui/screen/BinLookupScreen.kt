package com.example.binlookup.presentation.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.binlookup.R
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BinLookupScreen(
    viewModel: BinViewModel = koinViewModel(),
    onNavigateToHistory: () -> Unit
) {
    val context = LocalContext.current
    val binInput = remember { mutableStateOf("") }
    val binInfo = viewModel.binInfo.collectAsState().value
    val error = viewModel.error.collectAsState().value
    val loading = viewModel.isLoading.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.bin_lookup)) })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            TextField(
                value = binInput.value,
                onValueChange = { binInput.value = it },
                label = { Text(stringResource(R.string.enter_bin)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { viewModel.lookupBin(binInput.value.trim()) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.lookup))
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (loading) {
                CircularProgressIndicator(modifier = Modifier.padding(8.dp))
            }

            error?.let {
                Text(stringResource(R.string.error, it), color = Color.Red)
            }

            binInfo?.let { bin ->
                Column(modifier = Modifier.padding(vertical = 16.dp)) {
                    Text(stringResource(R.string.card_details), fontWeight = FontWeight.Bold)
                    InfoRow(stringResource(R.string.scheme), bin.scheme)
                    InfoRow(stringResource(R.string.type), bin.type)
                    InfoRow(stringResource(R.string.brand), bin.brand)
                    InfoRow(stringResource(R.string.prepaid), bin.prepaid?.toString())

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(stringResource(R.string.bank_info), fontWeight = FontWeight.Bold)
                    InfoRow(stringResource(R.string.name), bin.bank?.name)
                    InfoRow(stringResource(R.string.city), bin.bank?.city)
                    bin.bank?.url?.let { url ->
                        ClickableText(
                            text = AnnotatedString(stringResource(R.string.visit_bank_site)),
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                startActivity(context, intent, null)
                            },
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    } ?: InfoRow(
                        stringResource(R.string.bank_url),
                        stringResource(R.string.unavailable)
                    )
                    bin.bank?.phone?.let { phone ->
                        ClickableText(
                            text = AnnotatedString(stringResource(R.string.call_bank)),
                            onClick = {
                                val intent = Intent(
                                    Intent.ACTION_DIAL, Uri.parse(
                                        context.getString(
                                            R.string.tel, phone
                                        )
                                    )
                                )
                                startActivity(context, intent, null)
                            },
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    } ?: InfoRow(
                        stringResource(R.string.phone),
                        stringResource(R.string.unavailable)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(stringResource(R.string.country_info), fontWeight = FontWeight.Bold)
                    InfoRow(
                        stringResource(R.string.name),
                        "${bin.country?.name ?: stringResource(R.string.n_a)} ${bin.country?.emoji.orEmpty()}"
                    )
                    InfoRow(stringResource(R.string.currency), bin.country?.currency)
                    bin.country?.let { country ->
                        val lat = country.latitude
                        val lon = country.longitude
                        if (lat != null && lon != null) {
                            ClickableText(
                                text = AnnotatedString(stringResource(R.string.view_location_on_map)),
                                onClick = {
                                    val geoUri = "geo:$lat,$lon"
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
                                    startActivity(context, intent, null)
                                },
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        } else {
                            InfoRow(
                                stringResource(R.string.location),
                                stringResource(R.string.unavailable)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onNavigateToHistory() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.view_history))
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String?) {
    Text(
        "$label: ${value ?: stringResource(R.string.n_a)}",
        fontSize = 14.sp,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
