package org.edu.jvm.languages.game.inventory

import org.edu.jvm.languages.game.character.GameCharacter

data class Armor(override val name: String, val bonusHealth: Int) : Item(name) {
    override fun use(character: GameCharacter) {
        character.health += bonusHealth
        println("$name equipped! Increases health by $bonusHealth.")
    }
}
