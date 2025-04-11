package cvbuilder.dsl

import cvbuilder.models.Project

@CVDsl
class ProjectListBuilder {
    private val projects = mutableListOf<Project>()

    fun project(setup: ProjectBuilder.() -> Unit) {
        val builder = ProjectBuilder()
        builder.setup()
        projects.add(builder.build())
    }

    fun build(): List<Project> = projects
}