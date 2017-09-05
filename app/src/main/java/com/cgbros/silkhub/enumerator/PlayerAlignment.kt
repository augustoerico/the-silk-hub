package com.cgbros.silkhub.enumerator

enum class PlayerAlignment {

    LAWFUL_GOOD, LAWFUL_NEUTRAL, LAWFUL_EVIL,
    NEUTRAL_GOOD, TRUE_NEUTRAL, NEUTRAL_EVIL,
    CHAOTIC_GOOD, CHAOTIC_NEUTRAL, CHAOTIC_EVIL,
    EMPTY;

    companion object Factory {
        fun fromValues(lawChaosAxis: Int, goodEvilAxis: Int): PlayerAlignment {
            return when (lawChaosAxis)  {
                0 -> when (goodEvilAxis) {
                    0 -> LAWFUL_GOOD
                    1 -> LAWFUL_NEUTRAL
                    2 -> LAWFUL_EVIL
                    else -> EMPTY
                }
                1 -> when (goodEvilAxis) {
                    0 -> NEUTRAL_GOOD
                    1 -> TRUE_NEUTRAL
                    2 -> NEUTRAL_EVIL
                    else -> EMPTY
                }
                2 -> when (goodEvilAxis) {
                    0 -> CHAOTIC_GOOD
                    1 -> CHAOTIC_NEUTRAL
                    2 -> CHAOTIC_EVIL
                    else -> EMPTY
                }
                else -> EMPTY
            }
        }

        fun axisValues(alignment: PlayerAlignment): Pair<Int, Int> {

            return when (alignment) {
                LAWFUL_GOOD -> Pair(0, 0)
                LAWFUL_NEUTRAL -> Pair(0, 1)
                LAWFUL_EVIL -> Pair(0, 2)

                NEUTRAL_GOOD -> Pair(1, 0)
                TRUE_NEUTRAL -> Pair(1, 1)
                NEUTRAL_EVIL -> Pair(1, 2)

                CHAOTIC_GOOD -> Pair(2, 0)
                CHAOTIC_NEUTRAL -> Pair(2, 1)
                CHAOTIC_EVIL -> Pair(2, 2)

                else -> Pair(-1, -1)
            }
        }
    }
}