package cvbuilder.dsl

import cvbuilder.models.CV

@CVDsl
class CVBuilder {
    val cv = CV()

    fun build(): CV = cv

    inline fun contact(setup: ContactBuilder.() -> Unit) {
        val contactBuilder = ContactBuilder()
        contactBuilder.setup()
        cv.contact = contactBuilder.build()
    }

    inline fun education(setup: EducationBuilder.() -> Unit) {
        val educationBuilder = EducationBuilder()
        educationBuilder.setup()
        cv.education = educationBuilder.build()
    }

    inline fun projects(setup: ProjectListBuilder.() -> Unit) {
        val projectListBuilder = ProjectListBuilder()
        projectListBuilder.setup()
        cv.projects = projectListBuilder.build()
    }

    inline fun skills(setup: SkillsBuilder.() -> Unit) {
        val skillsBuilder = SkillsBuilder()
        skillsBuilder.setup()
        cv.skillSet = skillsBuilder.build()
    }

    companion object {
        fun cv(setup: CVBuilder.() -> Unit): CV {
            val cvBuilder = CVBuilder()
            cvBuilder.setup()
            return cvBuilder.build()
        }
    }
}
