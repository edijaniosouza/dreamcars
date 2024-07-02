package com.barrosedijanio.dreamcars.core.generic

sealed class GenericResult {
    data object Success: GenericResult()
    data object Loading : GenericResult()
    data class Error(val errorMessage: Int) : GenericResult()
    data object Empty : GenericResult()
}