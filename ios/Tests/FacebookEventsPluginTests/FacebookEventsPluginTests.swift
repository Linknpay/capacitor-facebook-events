import XCTest
import FBSDKCoreKit
@testable import FacebookEventsPlugin

class FacebookEventsPluginTests: XCTestCase {

    func testParametersPreserveTypes() {
        let implementation = FacebookEvents()
        let result = implementation.convertToFBParameters([
            "fb_content_id": "item_id",
            "_valueToSum": 12.5,
            "fb_num_items": 3,
            "flag": true
        ])

        XCTAssertEqual(result[AppEvents.ParameterName("fb_content_id")] as? String, "item_id")
        XCTAssertEqual(result[AppEvents.ParameterName("_valueToSum")] as? Double, 12.5)
        XCTAssertEqual(result[AppEvents.ParameterName("fb_num_items")] as? Int, 3)
        XCTAssertEqual(result[AppEvents.ParameterName("flag")] as? Bool, true)
    }

    func testNullParametersAreSkipped() {
        let implementation = FacebookEvents()
        let result = implementation.convertToFBParameters(["empty": NSNull()])
        XCTAssertTrue(result.isEmpty)
    }
}
