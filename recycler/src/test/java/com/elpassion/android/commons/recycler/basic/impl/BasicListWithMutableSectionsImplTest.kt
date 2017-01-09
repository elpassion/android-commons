package com.elpassion.android.commons.recycler.basic.impl

import com.elpassion.android.commons.recycler.basic.BasicListWithMutableSections
import com.elpassion.android.commons.recycler.basic.basicMutableListOf
import org.junit.Assert.assertEquals
import org.junit.Test

class BasicListWithMutableSectionsImplTest {

    @Test
    fun shouldCreateBasicListWithMutableSections() {
        val basicListWithMutableSections = BasicListWithMutableSectionsImpl<String, String>(mutableMapOf())

        assert(basicListWithMutableSections is BasicListWithMutableSections<String, String>)
    }

    @Test
    fun shouldReturnCorrectSizeForOneEmptySection() {
        val basicListWithMutableSections = BasicListWithMutableSectionsImpl<String, String>(
                mutableMapOf("A" to basicMutableListOf())
        )

        assertEquals(basicListWithMutableSections.size, 0)
    }

    @Test
    fun shouldReturnCorrectSizeForOneNotEmptySection() {
        val basicListWithMutableSections = BasicListWithMutableSectionsImpl(
                mutableMapOf("A" to basicMutableListOf("AA", "AB"))
        )

        assertEquals(basicListWithMutableSections.size, 2)
    }

    @Test
    fun shouldReturnCorrectSizeForTwoNotEmptySections() {
        val basicListWithMutableSections = BasicListWithMutableSectionsImpl(
                mutableMapOf(
                        "A" to basicMutableListOf("AA", "AB"),
                        "B" to basicMutableListOf("BA", "BB", "BC")
                )
        )

        assertEquals(basicListWithMutableSections.size, 5)
    }

    @Test
    fun shouldReturnCorrectValuesForGivenPositionsWithOnlyOneSection() {
        val basicListWithMutableSections = BasicListWithMutableSectionsImpl(
                mutableMapOf("A" to basicMutableListOf("AA", "AB", "AC"))
        )

        assertEquals(basicListWithMutableSections[0], "AA")
        assertEquals(basicListWithMutableSections[1], "AB")
        assertEquals(basicListWithMutableSections[2], "AC")
    }

    @Test
    fun shouldReturnCorrectValuesForGivenPositionsWithMoreSections() {
        val basicListWithMutableSections = BasicListWithMutableSectionsImpl(
                mutableMapOf(
                        "A" to basicMutableListOf("AA", "AB", "AC"),
                        "B" to basicMutableListOf("BA", "BB", "BC"),
                        "C" to basicMutableListOf("CA", "CB")
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
        val basicListWithMutableSections = BasicListWithMutableSectionsImpl(
                mutableMapOf("A" to basicMutableListOf("AA", "AB"))
        )

        basicListWithMutableSections[2]
    }

    @Test
    fun shouldReturnValidValuesAfterSourceChange() {
        val source = mutableMapOf(
                "A" to basicMutableListOf("AA", "AB"),
                "B" to basicMutableListOf("BA")
        )
        val basicListWithMutableSections = BasicListWithMutableSectionsImpl(source)

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
                "A" to basicMutableListOf("AA", "AB"),
                "B" to basicMutableListOf("BA")
        )
        val basicListWithMutableSections = BasicListWithMutableSectionsImpl(source)
        assertEquals(basicListWithMutableSections.size, 3)
        source["D"] = basicMutableListOf("DA", "DB")
        assertEquals(basicListWithMutableSections.size, 5)
        source["A"]!!.insert(2, "AC")
        assertEquals(basicListWithMutableSections.size, 6)
    }

    @Test
    fun shouldReturnCorrectValuesViaSectionsProperty() {
        val basicListWithMutableSections = BasicListWithMutableSectionsImpl(
                mutableMapOf(
                        "A" to basicMutableListOf("AA", "AB", "AC"),
                        "B" to basicMutableListOf("BA", "BB", "BC"),
                        "C" to basicMutableListOf("CA", "CB")
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
        val basicListWithMutableSections = BasicListWithMutableSectionsImpl(
                mutableMapOf(
                        "A" to basicMutableListOf("AA", "AB"),
                        "B" to basicMutableListOf("BA")
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
        val basicListWithMutableSections = BasicListWithMutableSectionsImpl(
                mutableMapOf(
                        "A" to basicMutableListOf("AA", "AB"),
                        "B" to basicMutableListOf("BA")
                )
        )

        assertEquals(basicListWithMutableSections.size, 3)

        basicListWithMutableSections.sections["D"] = basicMutableListOf("DA", "DB")

        assertEquals(basicListWithMutableSections.size, 5)

        basicListWithMutableSections.sections["A"]!!.insert(2, "AC")

        assertEquals(basicListWithMutableSections.size, 6)
    }

    @Test
    fun shouldReturnValidValuesAfterInsert() {
        val basicListWithMutableSections = BasicListWithMutableSectionsImpl(
                mutableMapOf(
                        "A" to basicMutableListOf("AA", "AB"),
                        "B" to basicMutableListOf("BA")
                )
        )

        basicListWithMutableSections.sections["A"]!!.insert(2, "AC")
        basicListWithMutableSections.sections["B"]!!.insert(1, "BB")

        assertEquals(basicListWithMutableSections.sections["A"]!![0], "AA")
        assertEquals(basicListWithMutableSections.sections["A"]!![1], "AB")
        assertEquals(basicListWithMutableSections.sections["A"]!![2], "AC")
        assertEquals(basicListWithMutableSections.sections["B"]!![0], "BA")
        assertEquals(basicListWithMutableSections.sections["B"]!![1], "BB")
    }
}