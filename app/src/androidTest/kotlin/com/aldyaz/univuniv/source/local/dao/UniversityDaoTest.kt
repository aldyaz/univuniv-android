package com.aldyaz.univuniv.source.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.aldyaz.univuniv.source.local.UnivUnivDatabase
import com.aldyaz.univuniv.source.local.model.UniversityDbModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UniversityDaoTest {

    private lateinit var database: UnivUnivDatabase
    private lateinit var dao: UniversityDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            context = ApplicationProvider.getApplicationContext(),
            klass = UnivUnivDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = database.universityDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertUniversities_returnCertainList() = runBlocking {
        val expected = dummyDbList()
        dao.saveUniversities(expected)
        dao.getUniversities().test {
            val actual = awaitItem()
            Assert.assertTrue(actual.isNotEmpty())
            Assert.assertTrue(actual.size == 3)
            Assert.assertEquals(expected.first().name, actual.first().name)
        }
    }

    @Test
    fun insertUniversity_returnListContainOneItem() = runBlocking {
        val expected = dummyDbList()[0]
        dao.saveUniversities(listOf(expected))
        dao.getUniversities().test {
            val actual = awaitItem()
            Assert.assertTrue(actual.isNotEmpty())
            Assert.assertTrue(actual.size == 1)
            Assert.assertEquals(expected.name, actual.first().name)
        }
    }

    @Test
    fun insertEmptyUniversities_isTableEmptyReturnTrue() = runBlocking {
        Assert.assertTrue(dao.isTableEmpty())
        dao.saveUniversities(items = emptyList())
        Assert.assertTrue(dao.isTableEmpty())
    }

    @Test
    fun insertCertainUniversities_isTableEmptyReturnFalse() = runBlocking {
        Assert.assertTrue(dao.isTableEmpty())
        dao.saveUniversities(dummyDbList())
        Assert.assertFalse(dao.isTableEmpty())
    }

    @Test
    fun insertCertainList_getUniversityByNameReturnEmptyList() = runBlocking {
        val dummyList = dummyDbList()
        dao.saveUniversities(dummyList)
        dao.getUniversities("STIE").test {
            val actual = awaitItem()
            Assert.assertTrue(actual.isEmpty())
        }
    }

    @Test
    fun insertCertainList_getUniversityByNameReturnNonEmptyList() = runBlocking {
        val dummyList = dummyDbList()
        dao.saveUniversities(dummyList)
        dao.getUniversities("Universitas").test {
            val actual = awaitItem()
            Assert.assertTrue(actual.isNotEmpty())
            Assert.assertTrue(actual.size == 2)
        }
    }

    @Test
    fun insertCertainList_emptyQuery_getUniversityByNameReturnCompleteList() = runBlocking {
        val dummyList = dummyDbList()
        dao.saveUniversities(dummyList)
        dao.getUniversities("").test {
            val actual = awaitItem()
            Assert.assertTrue(actual.isNotEmpty())
            Assert.assertTrue(actual.size == 3)
        }
    }

    private fun dummyDbList() = listOf(
        UniversityDbModel(
            name = "Universitas Negeri Malang",
            country = "Indonesia",
            domains = listOf("unm.ac.id"),
            webPages = listOf("https://unm.ac.id")
        ),
        UniversityDbModel(
            name = "Binus University",
            country = "Indonesia",
            domains = listOf("binus.ac.id"),
            webPages = listOf("https://binus.ac.id")
        ),
        UniversityDbModel(
            name = "Universitas Indonesia",
            country = "Indonesia",
            domains = listOf("ui.ac.id"),
            webPages = listOf("https://ui.ac.id")
        ),
    )
}