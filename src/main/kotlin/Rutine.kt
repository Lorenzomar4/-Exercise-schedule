class Routine(
    var name: String,
    val creator: User,
    val description: String,
) {
    val listOfExcercise: MutableList<Excercise> = mutableListOf()
    val listOfFollowers: MutableList<User> = mutableListOf()

    lateinit var criterionOfEdition: CriterionOfEdition

    fun duration() = listOfExcercise.sumOf { it.duration() }

    fun addExcercise(anExcercise: Excercise) {
        listOfExcercise.add(anExcercise)
    }

    fun removeAllExcercie() {
        listOfExcercise.clear()
    }

    fun trainingTheMuscle(muscle: Muscle) = muscleGroups().contains(muscle)

    fun equalsOrMoreThatTimeOfRoutine(time: Int) = allDurationOfExcercise().any { time >= it }

    fun muscleGroups() = listOfExcercise.flatMap { it.activity.muscleInTraining }.toSet()

    fun allDurationOfExcercise() = listOfExcercise.map { it.duration() }

    fun frequencyCardiacBase() = listOfExcercise.sumOf { it.frequencyBase } / numbersOfExcerciseInTheList()

    private fun numbersOfExcerciseInTheList() = listOfExcercise.size

    fun frequencyBaseReachedByUser(anUser: User) = (frequencyCardiacBase() + anUser.frequencyCardiacReserve()) / 2

    fun addFollower(anUser: User) {
        listOfFollowers.add(anUser)
    }

    fun canBeEdityBy(user: User) = user == creator || criterionOfEdition.canEdit(user, this)

    fun theUserIsFollower(user: User) = listOfFollowers.contains(user)

    fun isCompletyFor(user: User) = allActivityCanTrainBasicMuscleGroup() || user.isSatisfied(this)

    private fun allActivityCanTrainBasicMuscleGroup() = allActivity().all { it.trainingBasicGroup() }

    private fun allActivity() = listOfExcercise.map { it.activity }

    fun isHealthy(user: User): Boolean {

        val freq = user.frequencyCardiacOfTraining()

        return freq.toInt() in (user.frequencyCardiacReserve()..user.maximunFrequencyCardiac())
    }

    fun isCompletyAndHealthy(user: User) = isCompletyFor(user) && isHealthy(user)

}