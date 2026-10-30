package com.kodex.dogs.domain.usecase


    import com.kodex.dogs.domain.model.Book
    import javax.inject.Inject

    data class ValidationResult(
        val isValid: Boolean,
        val errors: List<String> = emptyList()
    )

    class ValidatePostUseCase @Inject constructor() {
        operator fun invoke(book: Book): ValidationResult {
            val errors = mutableListOf<String>()

            if (book.imageUrl.isBlank()) errors.add("Загрузите фотографию")
            if (book.title.isBlank()) errors.add("Введите заголовок объявления")
            if (book.description.length < 20) errors.add("Описание должно быть длиннее 10 символов")
            if (book.price <= 0) errors.add("Укажите корректную цену")
            if (book.village.isBlank()) errors.add("Укажите населенный пункт")
            if (book.location && book.street.isBlank()) errors.add("Укажите улицу для показа на карте")
            if (book.location && book.house.isBlank()) errors.add("Укажите дом для показа на карте")

            if (!isValidRussianPhoneNumber(book.telephone)) {
                errors.add("Введите корректный номер телефона: 10 цифр или +7/8 и 10 цифр")
            }

            return ValidationResult(errors.isEmpty(), errors)
        }
    }

    internal fun isValidRussianPhoneNumber(phone: String): Boolean {
        val value = phone.trim()
        if (value.isEmpty() || !value.matches(Regex("""\+?[0-9()\s-]+"""))) return false

        val openingParentheses = value.count { it == '(' }
        val closingParentheses = value.count { it == ')' }
        if (openingParentheses != closingParentheses || openingParentheses > 1) return false
        if (openingParentheses == 1) {
            val openingIndex = value.indexOf('(')
            val closingIndex = value.indexOf(')')
            if (openingIndex >= closingIndex || value.substring(openingIndex + 1, closingIndex).none(Char::isDigit)) {
                return false
            }
        }

        val digits = value.filter { it in '0'..'9' }
        return when {
            digits.length == 10 -> digits.first() in '3'..'9'
            digits.length == 11 && digits.first() in "78" -> digits[1] in '3'..'9'
            else -> false
        }
    }
