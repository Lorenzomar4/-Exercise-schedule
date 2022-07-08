class Activity(
    val name: String
) {
    val muscleInTraining: MutableList<Muscle> = mutableListOf()

    fun addMuscle(muscle: Muscle) {
        muscleInTraining.add(muscle)
    }
    fun removeMuscle(muscle: Muscle){
        muscleInTraining.remove(muscle)
    }


    fun trainingBasicGroup() = muscleInTraining.containsAll(basic())

    private fun basic() = listOf(Muscle.LEG, Muscle.ARMS, Muscle.ABDOMEN)

}

enum class Muscle {
    LEG, SHOULDERS, ARMS, CHEST, BACK, BUTTOCKS, ABDOMEN
}

