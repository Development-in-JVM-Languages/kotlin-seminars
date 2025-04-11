package cvbuilder.dsl

import cvbuilder.models.Education
import cvbuilder.models.Institution

@CVDsl
class EducationBuilder {
    val institutions = mutableListOf<Institution>()
    private var currentDegree = ""
    private var currentName = ""
    private var currentLocation = ""
    private var currentFrom = ""

    fun build(): Education = Education(institutions)

    infix fun String.at(that: String): FromBuilder {
        currentDegree = this
        currentName = that
        return FromBuilder()
    }

    inner class FromBuilder {
        infix fun from(year: String): ToBuilder {
            this@EducationBuilder.currentFrom = year
            return ToBuilder()
        }
    }

    inner class ToBuilder {
        infix fun to(year: String) {
            val institution = Institution(currentDegree, currentName, currentLocation, currentFrom, year)
            institutions.add(institution)
        }
    }
}
