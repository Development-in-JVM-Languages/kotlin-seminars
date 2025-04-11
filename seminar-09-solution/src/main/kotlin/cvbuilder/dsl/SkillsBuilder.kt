package cvbuilder.dsl

import cvbuilder.models.Skill
import cvbuilder.models.SkillSet

@CVDsl
class SkillsBuilder {
    private val skills = mutableListOf<Skill>()

    fun build(): SkillSet = SkillSet(skills)

    operator fun String.unaryMinus() {
        skills.add(Skill(this))
    }
}