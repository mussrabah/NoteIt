package util

sealed class UiEvent {
    //this class will hold all kinds of events
    //that we wanna send from our ViewModels to our Composable
    //ex: navigating to different screens, popping the back stack,
    //showing a snack-bar ..etc. Basically everything we will like
    //to do on the UI just once.(once because that's not a state)

    object Success: UiEvent()
    object NavigateUp: UiEvent()

    data class ShowSnackbar(val message: UiText): UiEvent()
}