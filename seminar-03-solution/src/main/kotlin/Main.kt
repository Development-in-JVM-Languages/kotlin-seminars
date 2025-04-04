package org.edu.jvm.languages

import org.edu.jvm.languages.game.battle.Battle
import org.edu.jvm.languages.game.inventory.*
import org.edu.jvm.languages.game.character.*

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    // TASK 1: Create two characters
    val warrior = Warrior("Thor")
    val mage = Mage("Merlin")

    // TASK 2 Fill inventory with items
    warrior.inventory.addItem(Weapon("Sword", 5))
    warrior.inventory.addItem(Elixir("Health Potion", 20))
    mage.inventory.addItem(Weapon("Staff", 7))
    mage.inventory.addItem(Armor("Robe", 15))

    // TASK 3: Create a battle and simulate it
    val battle = Battle(warrior, mage)
    battle.simulate()
}
