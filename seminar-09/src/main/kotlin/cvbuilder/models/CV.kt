package cvbuilder.models

data class CV(
    var contact: Contact = Contact(),
    var education: Education = Education(),
    var projects: List<Project> = emptyList(),
    var skillSet: SkillSet = SkillSet()
)
