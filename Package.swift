// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "CapacitorFacebookEvents",
    platforms: [.iOS(.v15)],
    products: [
        .library(
            name: "CapacitorFacebookEvents",
            targets: ["FacebookEventsPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "8.0.0"),
        .package(url: "https://github.com/facebook/facebook-ios-sdk.git", .upToNextMajor(from: "18.0.0"))
    ],
    targets: [
        .target(
            name: "FacebookEventsPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm"),
                .product(name: "FacebookCore", package: "facebook-ios-sdk")
            ],
            path: "ios/Sources/FacebookEventsPlugin"),
        .testTarget(
            name: "FacebookEventsPluginTests",
            dependencies: ["FacebookEventsPlugin"],
            path: "ios/Tests/FacebookEventsPluginTests")
    ]
)
