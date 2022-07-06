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
    fun removeAllExcercie (){
        listOfExcercise.clear()

    }

    fun muscleGroups() = listOfExcercise.flatMap { it.activity.muscleInTraining }.toSet()

    fun frequencyCardiacBase() = listOfExcercise.sumOf { it.frequencyBase } / numbersOfExcerciseInTheList()

    private fun numbersOfExcerciseInTheList() = listOfExcercise.size

    fun frequencyBaseReachedByUser(anUser: User) = (frequencyCardiacBase() + anUser.frequencyCardiacReserve()) / 2

    fun addFollower(anUser: User) {
        listOfFollowers.add(anUser)
    }

    fun canBeEdityBy(user: User) = user == creator || criterionOfEdition.canEdit(user, this)

    fun theUserIsFollower(user: User) = listOfFollowers.contains(user)

    fun isCompletyFor(user: User) = allActivityCanTrainBasicMuscleGroup() //||

    private fun allActivityCanTrainBasicMuscleGroup() = allActivity().all{it.trainingBasicGroup()}

    private fun allActivity() = listOfExcercise.map { it.activity }
}