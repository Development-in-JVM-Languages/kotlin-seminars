package cvbuilder.dsl

import cvbuilder.models.Project

@CVDsl
class ProjectBuilder {
    var name: String = ""
    var description: String = ""
    private val technologies = mutableListOf<String>()
    var url: String = ""
    var year: String = ""

    operator fun String.unaryMinus() {
        technologies.add(this)
    }

    fun build() = Project(name, description, technologies, url, year)
}