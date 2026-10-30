package com.kodex.dogs

import com.kodex.dogs.domain.usecase.isValidRussianPhoneNumber
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class RussianPhoneNumberValidationTest {

    @Test
    fun `accepts common Russian phone formats`() {
        assertTrue(isValidRussianPhoneNumber("4951234567"))
        assertTrue(isValidRussianPhoneNumber("+7 (495) 123-45-67"))
        assertTrue(isValidRussianPhoneNumber("8 800 555 35 35"))
    }

    @Test
    fun `rejects incomplete malformed and non-Russian numbers`() {
        assertFalse(isValidRussianPhoneNumber(""))
        assertFalse(isValidRussianPhoneNumber("12345"))
        assertFalse(isValidRussianPhoneNumber("+1 212 555 0100"))
        assertFalse(isValidRussianPhoneNumber("+7 (495 123-45-67"))
        assertFalse(isValidRussianPhoneNumber("495CALL567"))
    }
}
