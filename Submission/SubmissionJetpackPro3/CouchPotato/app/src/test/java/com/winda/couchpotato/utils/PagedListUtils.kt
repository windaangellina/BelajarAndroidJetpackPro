package com.winda.couchpotato.utils

import androidx.paging.PagedList
import org.mockito.Mockito.mock

object PagedListUtils {
    fun <T> mockPagedList(list: List<T>): PagedList<T> {
        return mock(PagedList::class.java) as PagedList<T>
    }
}