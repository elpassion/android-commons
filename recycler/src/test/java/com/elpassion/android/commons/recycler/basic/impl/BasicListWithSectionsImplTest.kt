package com.elpassion.android.commons.recycler.basic.impl

import com.elpassion.android.commons.recycler.basic.ListWithSections
import com.elpassion.android.commons.recycler.basic.asBasicListWithSections
import org.junit.Assert.assertEquals
import org.junit.Test

class BasicListWithSectionsImplTest {

    @Test
    fun shouldCreateBasicListWithSections() {
        val basicListWithSections = ListWithSectionsImpl<String, String>(mapOf())

        assert(basicListWithSections is ListWithSections<String, String>)
    }

    @Test
    fun shouldReturnCorrectSizeForOneEmptySection() {
        val basicListWithSections = ListWithSectionsImpl(mapOf("A" to listOf<String>()))

        assertEquals(basicListWithSections.size, 0)
    }

    @Test
    fun shouldReturnCorrectSizeForOneNotEmptySection() {
        val basicListWithSections = ListWithSectionsImpl(mapOf("A" to listOf("AA", "AB")))

        assertEquals(basicListWithSections.size, 2)
    }

    @Test
    fun shouldReturnCorrectSizeForTwoNotEmptySections() {
        val basicListWithSections = ListWithSectionsImpl(mapOf("A" to listOf("AA", "AB"), "B" to listOf("BA", "BB", "BC")))

        assertEquals(basicListWithSections.size, 5)
    }

    @Test
    fun shouldReturnCorrectValuesForGivenPositionsWithOnlyOneSection() {
        val basicListWithSections = ListWithSectionsImpl(mapOf("A" to listOf("AA", "AB", "AC")))

        assertEquals(basicListWithSections[0], "AA")
        assertEquals(basicListWithSections[1], "AB")
        assertEquals(basicListWithSections[2], "AC")
    }

    @Test
    fun shouldReturnCorrectValuesForGivenPositionsWithMoreSections() {
        val basicListWithSections = ListWithSectionsImpl(mapOf(
                "A" to listOf("AA", "AB", "AC"),
                "B" to listOf("BA", "BB", "BC"),
                "C" to listOf("CA", "CB")
        ))

        assertEquals(basicListWithSections[0], "AA")
        assertEquals(basicListWithSections[1], "AB")
        assertEquals(basicListWithSections[2], "AC")
        assertEquals(basicListWithSections[3], "BA")
        assertEquals(basicListWithSections[4], "BB")
        assertEquals(basicListWithSections[5], "BC")
        assertEquals(basicListWithSections[6], "CA")
        assertEquals(basicListWithSections[7], "CB")
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun shouldThrowOutOfBoundsExceptionForNonExistingValue() {
        val basicListWithSections = ListWithSectionsImpl(mapOf("A" to listOf("AA", "AB")))

        basicListWithSections[2]
    }

    @Test
    fun shouldReturnValidValuesAfterSourceChange() {
        val source = mapOf(
                "A" to mutableListOf("AA", "AB"),
                "B" to mutableListOf("BA")
        )
        val basicListWithSections = source.asBasicListWithSections()

        assertEquals(basicListWithSections[0], "AA")
        assertEquals(basicListWithSections[1], "AB")
        assertEquals(basicListWithSections[2], "BA")

        source["A"]!![1] = "ab"
        source["B"]!![0] = "ba"

        assertEquals(basicListWithSections[0], "AA")
        assertEquals(basicListWithSections[1], "ab")
        assertEquals(basicListWithSections[2], "ba")
    }

    @Test
    fun shouldReturnCorrectSizeWhenSourceChanges() {
        val source = mutableMapOf(
                "A" to mutableListOf("AA", "AB"),
                "B" to mutableListOf("BA")
        )
        val basicListWithSections = ListWithSectionsImpl(source)
        assertEquals(basicListWithSections.size, 3)
        source["D"] = mutableListOf("DA", "DB")
        assertEquals(basicListWithSections.size, 5)
        source["A"]!!.add("AC")
        assertEquals(basicListWithSections.size, 6)
    }

    @Test
    fun shouldReturnCorrectValuesViaSectionsProperty() {
        val basicListWithSections = mapOf(
                "A" to listOf("AA", "AB", "AC"),
                "B" to listOf("BA", "BB", "BC"),
                "C" to listOf("CA", "CB")
        ).asBasicListWithSections()

        assertEquals(basicListWithSections.sections["A"]!![0], "AA")
        assertEquals(basicListWithSections.sections["A"]!![1], "AB")
        assertEquals(basicListWithSections.sections["A"]!![2], "AC")
        assertEquals(basicListWithSections.sections["B"]!![0], "BA")
        assertEquals(basicListWithSections.sections["B"]!![1], "BB")
        assertEquals(basicListWithSections.sections["B"]!![2], "BC")
        assertEquals(basicListWithSections.sections["C"]!![0], "CA")
        assertEquals(basicListWithSections.sections["C"]!![1], "CB")
    }
}