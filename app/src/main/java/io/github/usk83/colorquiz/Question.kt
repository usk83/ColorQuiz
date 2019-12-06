package io.github.usk83.colorquiz

class Question {
    private lateinit var correctColor: Color

    fun getQuestion(size: Int): Pair<Color, List<Color>> {
        if (size > Color.values().size) {
            throw IllegalArgumentException("`size` is too big: there are not enough colors")
        }

        val colorSet = HashSet<Color>(size)
        // Note: this algorithm is foolish,
        // need to improve by assuming when there are a lot of colors and buttons
        while (colorSet.size != size) {
            colorSet.add(Color.getRandomColor())
        }

        var colorList = colorSet.toMutableList()
        colorList.shuffle()
        correctColor = colorList[0]
        return correctColor to colorList.slice(1 until size)
    }

    fun checkAnswer(color: Int) = color == correctColor.rgb

}
