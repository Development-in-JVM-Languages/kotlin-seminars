package org.edu.jvm.languages.game.battle

/**
 * Represents a battle between two characters.
 *
 * @property character1 the first character
 * @property character2 the second character
 */
class Battle(private val character1: GameCharacter, private val character2: GameCharacter) {

    fun simulate() {
        var round = 1
        var currentAttacker = character1
        var currentDefender = character2

        println("Battle starts: ${character1.name} vs ${character2.name}")

        while (character1.isAlive() && character2.isAlive()) {
            println("\nRound $round")

            // Perform action (attack or use item)
            val action = (1..10).random()
            if (action <= 7 || !currentAttacker.inventory.hasItems()) {  // 70% chance to attack or if no items
                currentAttacker.attack(currentDefender)
            } else {
                // Use a random item
                val item = currentAttacker.inventory.getRandomItem()
                item?.let {
                    it.use(currentAttacker)
                    if (it is Elixir) {
                        currentAttacker.inventory.removeItem(it)  // Only remove Elixir after use
                    }
                }
            }

            // Display health status
            println("${character1.name}: ${character1.health} HP | ${character2.name}: ${character2.health} HP")

            // Switch attacker and defender
            val temp = currentAttacker
            currentAttacker = currentDefender
            currentDefender = temp

            round++
        }

        // Announce winner
        val winner = if (character1.isAlive()) character1.name else character2.name
        println("\n$winner wins the battle!")
    }
}
