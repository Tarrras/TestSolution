package com.youarelaunched.challenge.ui.screen.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VendorsVM @Inject constructor(
    private val repository: VendorsRepository
) : ViewModel() {

    private val _vendors = MutableStateFlow<List<Vendor>?>(null)
    private val _query = MutableStateFlow<String?>(null)
    private val debouncedQuery =
        _query.debounce(500)

    private val filteredVendors = debouncedQuery.flatMapLatest { query ->
        _vendors.map { vendors ->
            vendors?.filter { vendor ->
                query?.let { text -> vendor.companyName.contains(text, ignoreCase = true) } ?: true
            }
        }
    }

    val uiState = combine(filteredVendors, _query) { vendors, query ->
        VendorsScreenUiState(vendors, query)
    }.stateIn(
        viewModelScope,
        initialValue = VendorsScreenUiState(null, null),
        started = SharingStarted.WhileSubscribed(5000)
    )

    init {
        getVendors()
    }

    fun onQueryChanged(text: String) {
        _query.update { text }
    }


    fun getVendors() {
        viewModelScope.launch {
            try {
                _vendors.update {
                    repository.getVendors()
                }
            } catch (exc: Exception) {
                _vendors.value = null
            }

        }
    }

}