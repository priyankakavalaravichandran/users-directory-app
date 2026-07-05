package com.anz.usersdirectoryapp.domain.mapper

import com.anz.usersdirectoryapp.data.model.User
import org.junit.Assert.assertEquals
import org.junit.Test

class UserUiMapperTest {

    @Test
    fun `toUserUiModel maps User to UserUiModel correctly`() {
        val user = User(
            id = 1,
            name = "John Doe Smith",
            company = "Test Co",
            username = "johnd",
            email = "john@example.com",
            address = "123 Street",
            phone = "123456",
            photo = "url"
        )

        val uiModel = user.toUserUiModel()

        assertEquals(1, uiModel.id)
        assertEquals("John Doe", uiModel.name) // Mapper takes first 2 words
        assertEquals("Test Co", uiModel.company)
        assertEquals("johnd", uiModel.userName)
        assertEquals("john@example.com", uiModel.email)
        assertEquals("123 Street", uiModel.address)
        assertEquals("123456", uiModel.phone)
        assertEquals("url", uiModel.photo)
    }

    @Test
    fun `toUserUiModel handles null values`() {
        val user = User(id = null, name = null)
        val uiModel = user.toUserUiModel()

        assertEquals(0, uiModel.id)
        assertEquals("", uiModel.name)
        assertEquals("", uiModel.company)
    }

    @Test
    fun `toUiModelList maps list of users`() {
        val users = listOf(User(id = 1), User(id = 2))
        val uiModels = users.toUiModelList()

        assertEquals(2, uiModels.size)
        assertEquals(1, uiModels[0].id)
        assertEquals(2, uiModels[1].id)
    }
}
