package cvbuilder.render

import cvbuilder.models.CV
import kotlinx.html.*
import kotlinx.html.stream.createHTML

class HtmlRenderer(private val curriculum: CV) : CVRenderer {
    override fun render(): String = createHTML().html {
        head {
            title(curriculum.contact.name)
            style { unsafe { +CvStyles.STYLES } }
        }
        body {
            div(CvStyles.ClassNames.CONTAINER) {
                renderSections()
            }
        }
    }

    private fun DIV.renderSections() {
        renderSection(CvStyles.ClassNames.CONTACT) { renderContact() }
        renderSection(CvStyles.ClassNames.EDUCATION) { renderEducation() }
        renderSection(CvStyles.ClassNames.PROJECTS) { renderProjects() }
        renderSection(CvStyles.ClassNames.SKILLS) { renderSkills() }
    }

    private fun DIV.renderSection(className: String, content: DIV.() -> Unit) {
        div("${CvStyles.ClassNames.SECTION} $className") {
            content()
        }
    }

    private fun DIV.renderContact() {
        h1 { +curriculum.contact.name }
        p {
            +"Email: "
            a(href = "mailto:${curriculum.contact.email}") { +curriculum.contact.email }
        }
        p { +"Phone: ${curriculum.contact.phoneNumber}" }
    }

    private fun DIV.renderEducation() {
        h2 { +"Education" }
        curriculum.education.institutions.forEach { institution ->
            div(CvStyles.ClassNames.INSTITUTION) {
                h3 { +institution.degree }
                p {
                    +institution.name
                    +" (${institution.from} - ${institution.to})"
                }
                institution.location.takeIf { it.isNotEmpty() }?.let {
                    p { +it }
                }
            }
        }
    }

    private fun DIV.renderProjects() {
        h2 { +"Projects" }
        curriculum.projects.forEach { project ->
            div(CvStyles.ClassNames.PROJECT) {
                h3 { +project.name }
                p { +project.description }
                project.url.takeIf { it.isNotEmpty() }?.let {
                    p { a(href = it) { +"Project Link" } }
                }
                project.technologies.takeIf { it.isNotEmpty() }?.let {
                    renderTechnologies(it)
                }
                p { +"Year: ${project.year}" }
            }
        }
    }

    private fun DIV.renderTechnologies(technologies: List<String>) {
        p { +"Technologies:" }
        ul {
            technologies.forEach { tech ->
                li { +tech }
            }
        }
    }

    private fun DIV.renderSkills() {
        h2 { +"Skills" }
        div(CvStyles.ClassNames.SKILLS_LIST) {
            curriculum.skillSet.skills.forEach { skill ->
                span(CvStyles.ClassNames.SKILL_ITEM) { +skill.name }
            }
        }
    }
    @Suppress("unused")
    companion object {
        fun CV.toHtml(): String = HtmlRenderer(this).render()
        fun CV.saveToHtml(path: String) {
            java.io.File(path).writeText(this.toHtml())
        }
    }
}

