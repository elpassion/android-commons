package com.elpassion.android.commons.recycler.basic.impl

import org.junit.Assert.assertEquals
import org.junit.Test

class BasicListWithMutableSectionsImplTest {

    @Test
    fun shouldCreateBasicListWithMutableSections() {
        val basicListWithMutableSections = ListWithMutableSectionsImpl<String, String>(mutableMapOf())

        assert(basicListWithMutableSections is ListWithMutableSections<String, String>)
    }

    @Test
    fun shouldReturnCorrectSizeForOneEmptySection() {
        val basicListWithMutableSections = ListWithMutableSectionsImpl<String, String>(
                mutableMapOf("A" to mutableListOf())
        )

        assertEquals(basicListWithMutableSections.size, 0)
    }

    @Test
    fun shouldReturnCorrectSizeForOneNotEmptySection() {
        val basicListWithMutableSections = ListWithMutableSectionsImpl(
                mutableMapOf("A" to mutableListOf("AA", "AB"))
        )

        assertEquals(basicListWithMutableSections.size, 2)
    }

    @Test
    fun shouldReturnCorrectSizeForTwoNotEmptySections() {
        val basicListWithMutableSections = ListWithMutableSectionsImpl(
                mutableMapOf(
                        "A" to mutableListOf("AA", "AB"),
                        "B" to mutableListOf("BA", "BB", "BC")
                )
        )

        assertEquals(basicListWithMutableSections.size, 5)
    }

    @Test
    fun shouldReturnCorrectValuesForGivenPositionsWithOnlyOneSection() {
        val basicListWithMutableSections = ListWithMutableSectionsImpl(
                mutableMapOf("A" to mutableListOf("AA", "AB", "AC"))
        )

        assertEquals(basicListWithMutableSections[0], "AA")
        assertEquals(basicListWithMutableSections[1], "AB")
        assertEquals(basicListWithMutableSections[2], "AC")
    }

    @Test
    fun shouldReturnCorrectValuesForGivenPositionsWithMoreSections() {
        val basicListWithMutableSections = ListWithMutableSectionsImpl(
                mutableMapOf(
                        "A" to mutableListOf("AA", "AB", "AC"),
                        "B" to mutableListOf("BA", "BB", "BC"),
                        "C" to mutableListOf("CA", "CB")
                )
        )

        assertEquals(basicListWithMutableSections[0], "AA")
        assertEquals(basicListWithMutableSections[1], "AB")
        assertEquals(basicListWithMutableSections[2], "AC")
        assertEquals(basicListWithMutableSections[3], "BA")
        assertEquals(basicListWithMutableSections[4], "BB")
        assertEquals(basicListWithMutableSections[5], "BC")
        assertEquals(basicListWithMutableSections[6], "CA")
        assertEquals(basicListWithMutableSections[7], "CB")
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun shouldThrowOutOfBoundsExceptionForNonExistingValue() {
        val basicListWithMutableSections = ListWithMutableSectionsImpl(
                mutableMapOf("A" to mutableListOf("AA", "AB"))
        )

        basicListWithMutableSections[2]
    }

    @Test
    fun shouldReturnValidValuesAfterSourceChange() {
        val source = mutableMapOf(
                "A" to mutableListOf("AA", "AB"),
                "B" to mutableListOf("BA")
        )
        val basicListWithMutableSections = ListWithMutableSectionsImpl(source)

        assertEquals(basicListWithMutableSections[0], "AA")
        assertEquals(basicListWithMutableSections[1], "AB")
        assertEquals(basicListWithMutableSections[2], "BA")

        source["A"]!![1] = "ab"
        source["B"]!![0] = "ba"

        assertEquals(basicListWithMutableSections[0], "AA")
        assertEquals(basicListWithMutableSections[1], "ab")
        assertEquals(basicListWithMutableSections[2], "ba")
    }

    @Test
    fun shouldReturnCorrectSizeWhenSourceChanges() {
        val source = mutableMapOf(
                "A" to mutableListOf("AA", "AB"),
                "B" to mutableListOf("BA")
        )
        val basicListWithMutableSections = ListWithMutableSectionsImpl(source)
        assertEquals(basicListWithMutableSections.size, 3)
        source["D"] = mutableListOf("DA", "DB")
        assertEquals(basicListWithMutableSections.size, 5)
        source["A"]!!.add(2, "AC")
        assertEquals(basicListWithMutableSections.size, 6)
    }

    @Test
    fun shouldReturnCorrectValuesViaSectionsProperty() {
        val basicListWithMutableSections = ListWithMutableSectionsImpl(
                mutableMapOf(
                        "A" to mutableListOf("AA", "AB", "AC"),
                        "B" to mutableListOf("BA", "BB", "BC"),
                        "C" to mutableListOf("CA", "CB")
                )
        )

        assertEquals(basicListWithMutableSections.sections["A"]!![0], "AA")
        assertEquals(basicListWithMutableSections.sections["A"]!![1], "AB")
        assertEquals(basicListWithMutableSections.sections["A"]!![2], "AC")
        assertEquals(basicListWithMutableSections.sections["B"]!![0], "BA")
        assertEquals(basicListWithMutableSections.sections["B"]!![1], "BB")
        assertEquals(basicListWithMutableSections.sections["B"]!![2], "BC")
        assertEquals(basicListWithMutableSections.sections["C"]!![0], "CA")
        assertEquals(basicListWithMutableSections.sections["C"]!![1], "CB")
    }

    @Test
    fun shouldReturnValidValuesAfterSectionsChanges() {
        val basicListWithMutableSections = ListWithMutableSectionsImpl(
                mutableMapOf(
                        "A" to mutableListOf("AA", "AB"),
                        "B" to mutableListOf("BA")
                )
        )

        assertEquals(basicListWithMutableSections[0], "AA")
        assertEquals(basicListWithMutableSections[1], "AB")
        assertEquals(basicListWithMutableSections[2], "BA")

        basicListWithMutableSections.sections["A"]!![1] = "ab"
        basicListWithMutableSections.sections["B"]!![0] = "ba"

        assertEquals(basicListWithMutableSections[0], "AA")
        assertEquals(basicListWithMutableSections[1], "ab")
        assertEquals(basicListWithMutableSections[2], "ba")
    }

    @Test
    fun shouldReturnCorrectSizeAfterSectionsChanges() {
        val basicListWithMutableSections = ListWithMutableSectionsImpl(
                mutableMapOf(
                        "A" to mutableListOf("AA", "AB"),
                        "B" to mutableListOf("BA")
                )
        )

        assertEquals(basicListWithMutableSections.size, 3)

        basicListWithMutableSections.sections["D"] = mutableListOf("DA", "DB")

        assertEquals(basicListWithMutableSections.size, 5)

        basicListWithMutableSections.sections["A"]!!.add(2, "AC")

        assertEquals(basicListWithMutableSections.size, 6)
    }

    @Test
    fun shouldReturnValidValuesAfterInsert() {
        val basicListWithMutableSections = ListWithMutableSectionsImpl(
                mutableMapOf(
                        "A" to mutableListOf("AA", "AB"),
                        "B" to mutableListOf("BA")
                )
        )

        basicListWithMutableSections.sections["A"]!!.add(2, "AC")
        basicListWithMutableSections.sections["B"]!!.add(1, "BB")

        assertEquals(basicListWithMutableSections.sections["A"]!![0], "AA")
        assertEquals(basicListWithMutableSections.sections["A"]!![1], "AB")
        assertEquals(basicListWithMutableSections.sections["A"]!![2], "AC")
        assertEquals(basicListWithMutableSections.sections["B"]!![0], "BA")
        assertEquals(basicListWithMutableSections.sections["B"]!![1], "BB")
    }
}