package io.github.usk83.colorquiz

import kotlin.random.Random

enum class Color(val rgb: Int) {

    BLACK(-16777216),
    BLUE(-16776961),
    CYAN(-16711681),
    DARK_GREY(-12303292),
    GRAY(-7829368),
    GREEN(-16711936),
    LIGHT_GRAY(-3355444),
    MAGENTA(-65281),
    RED(-65536),
    WHITE(-1),
    YELLOW(-256);

    override fun toString() = super.toString()
        .split('_')
        .map { part -> part.toLowerCase().capitalize() }
        .joinToString(separator = " ")

    companion object {
        fun getRandomColor() = values()[Random.nextInt(values().size)]
    }

}
