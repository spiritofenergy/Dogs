package com.kodex.dogs.ui.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kodex.dogs.domain.model.BookCategories


class BookConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromRatingsList(value: List<Int>?): String? {
        if (value == null) return null
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toRatingsList(value: String?): List<Int>? {
        if (value == null) return null
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(value, type) ?: emptyList()
    }

    // --- Конвертер для Enum BookCategories ---

    @TypeConverter
    fun fromBookCategories(value: BookCategories?): Int? {
        return value?.id
    }

    @TypeConverter
    fun toBookCategories(value: Int?): BookCategories? {
        return value?.let { BookCategories.fromId(it) }
    }
}
/*

    @TypeConverter
    fun fromRatingsList(ratings: List<Int>): String {
        return Json.encodeToString(ratings) // Безопасная сериализация в "[1,2,3]"
    }

    @TypeConverter
    fun toRatingsList(data: String): List<Int> {
        return try {
            if (data.isBlank()) emptyList() else Json.decodeFromString(data)
        } catch (e: Exception) {
            emptyList() // Защита от краша при битых данных
        }
    }
    @TypeConverter
    fun fromBookCategories(value: BookCategories): Int {
        return value.id // предполагаем, что у enum есть свойство id типа Int
    }

    @TypeConverter
    fun toBookCategories(value: Int): BookCategories {
        return BookCategories.fromId(value) // ваш метод получения enum из Int
    }

    @TypeConverter
    fun fromRatingsList(value: List<Int>?): String? {
        if (value == null) return null
        val gson = Gson()
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toRatingsList(value: String?): List<Int>? {
        if (value == null) return null
        val gson = Gson()
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(value, type)
    }
*/

   /* @TypeConverter
    fun fromRatingsList(ratings: List<Int>): String {
        return ratings.joinToString(",")
    }

    @TypeConverter
    fun toRatingsList(data: String): List<Int> {
        if (data.isEmpty()) return emptyList()
        return data.split(",").mapNotNull { it.toIntOrNull() }
    }*/
