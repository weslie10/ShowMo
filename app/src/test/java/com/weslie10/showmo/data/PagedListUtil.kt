package com.weslie10.showmo.data

import androidx.paging.PagedList
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

object PagedListUtil {

    fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList = mock(PagedList::class.java)
        `when`(pagedList[anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        `when`(pagedList.size).thenReturn(list.size)

        @Suppress("UNCHECKED_CAST")
        return pagedList as PagedList<T>
    }
}