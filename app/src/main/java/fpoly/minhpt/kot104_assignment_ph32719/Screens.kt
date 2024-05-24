package fpoly.minhpt.kot104_assignment_ph32719

sealed class  Screens(val screen: String){
    data object Home : Screens("home")
    data object BookMark : Screens("bookmark")
    data object Notification : Screens("notification")
    data object Profile : Screens("profile")
    data object ProductDetail : Screens("productDetail")
}