package com.kodex.dogs

import com.kodex.dogs.domain.model.Book
import com.kodex.dogs.domain.usecase.ValidatePostUseCase
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PostLocationValidationTest {
    private val validatePost = ValidatePostUseCase()

    @Test
    fun `street and house are required when location is shown on map`() {
        val result = validatePost(validBook(location = true))

        assertFalse(result.isValid)
        assertTrue(result.errors.contains("Укажите улицу для показа на карте"))
        assertTrue(result.errors.contains("Укажите дом для показа на карте"))
    }

    @Test
    fun `street and house remain optional when location is hidden`() {
        val result = validatePost(validBook(location = false))

        assertTrue(result.isValid)
    }

    private fun validBook(location: Boolean) = Book(
        imageUrl = "photo",
        title = "Пост",
        description = "Описание объявления достаточно длинное",
        price = 100,
        telephone = "4951234567",
        village = "Тула",
        location = location
    )
}
