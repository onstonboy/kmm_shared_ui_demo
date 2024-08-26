import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        KoinModuleKt.doInitKoin(baseUrl: "https://api.github.com/", mySharedPref: MySharedPref(context: NSObject()))
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
