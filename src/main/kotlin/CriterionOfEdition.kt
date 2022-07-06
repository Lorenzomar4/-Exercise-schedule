interface CriterionOfEdition {
    fun canEdit(user:User ,Routine: Routine) : Boolean
}

class CriterionFriendly :CriterionOfEdition {
    override fun canEdit(user: User, routine: Routine) =routine.creator.isFriendOf(user)
}

class CriterionSocial : CriterionOfEdition{
    override fun canEdit(user: User, routine: Routine) = routine.theUserIsFollower(user)

}
class CriterionFree : CriterionOfEdition{
    override fun canEdit(user: User, Routine: Routine) = true

}
class CriterionFortuitous : CriterionOfEdition{
    val listOfCharacters = listOf<String>("F","J","R")
    override fun canEdit(user: User, routine: Routine) = routine.name.length>6  || theCharIsIncludeInTheList(user)


    fun theCharIsIncludeInTheList(user: User) : Boolean {
        val nameOfUser = user.name.first().toString().uppercase()
        return listOfCharacters.contains(nameOfUser)
    }

}
