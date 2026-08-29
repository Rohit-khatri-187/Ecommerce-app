package com.rohit.ecom.presentation.screens.profilescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rohit.ecom.presentation.screens.components.ErrorItem
import com.rohit.ecom.presentation.screens.profilescreen.components.EditProfileDialog
import com.rohit.ecom.presentation.screens.profilescreen.components.ProfileHeader
import com.rohit.ecom.presentation.screens.profilescreen.components.ProfileItem
import com.rohit.ecom.utils.ApiResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onBackClick: () -> Unit,
    onLogoutSuccess: () -> Unit,
    viewModel: ProfileScreenViewModel = hiltViewModel()
) {
    val profileState by viewModel.getProfileUiState.collectAsState()
    val updateState by viewModel.updateProfileState.collectAsState()
    
    var showEditDialog by remember { mutableStateOf(false) }

    LaunchedEffect(updateState) {
        if (updateState is ApiResult.Success) {
            showEditDialog = false
            viewModel.resetUpdateState()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (val state = profileState) {
                is ApiResult.Loading -> {
                    CircularProgressIndicator()
                }

                is ApiResult.Success -> {
                    val profile = state.data.content
                    
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ProfileHeader(name = profile.name)

                        Spacer(modifier = Modifier.height(32.dp))

                        ProfileItem(
                            icon = Icons.Default.Email,
                            label = "Email Address",
                            value = profile.email
                        )
                        
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                        )

                        ProfileItem(
                            icon = Icons.Default.Phone,
                            label = "Mobile Number",
                            value = profile.mobileNumber
                        )

                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                        )

                        ProfileItem(
                            icon = Icons.Default.LocationOn,
                            label = "Delivery Address",
                            value = profile.address
                        )

                        Spacer(modifier = Modifier.height(48.dp))

                        Button(
                            onClick = { showEditDialog = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(50)
                        ) {
                            Text("Edit Profile", fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedButton(
                            onClick = { viewModel.logout(onLogoutSuccess) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(50),
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                MaterialTheme.colorScheme.error.copy(alpha = 0.5f)
                            ),
                            colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
                                contentColor = MaterialTheme.colorScheme.error
                            )
                        ) {
                            Icon(imageVector = Icons.Default.Logout, contentDescription = null)
                            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                            Text("Logout", fontWeight = FontWeight.Bold)
                        }
                    }

                    if (showEditDialog) {
                        EditProfileDialog(
                            currentAddress = profile.address,
                            currentMobile = profile.mobileNumber,
                            isLoading = updateState is ApiResult.Loading,
                            onDismiss = { showEditDialog = false },
                            onConfirm = { updateDto ->
                                viewModel.updateProfile(updateDto)
                            }
                        )
                    }
                }

                is ApiResult.Error -> {
                    ErrorItem(
                        message = state.message,
                        onRetry = { viewModel.getProfile() }
                    )
                }
            }
        }
    }
}
