
class Excercise(
    var activity: Activity,
    var frequencyBase: Int,
    var minuteOfRest: Int,
    var series: Int,
    var minuteOfWork: Int,
    var repeats: Int,
) {
    //tested
    fun name() = activity.name

    private fun hasSeriesAnRepeats() = series > 0 && repeats > 0

    fun duration() = if (hasSeriesAnRepeats()) minuteOfRest * series * 2 else minuteOfRest + minuteOfWork
    //tested


}