//
//  MainViewModel.swift
//  SecureSwiftUI
//
//  Created by Takahiro Tominaga on 2022/10/18.
//

import Foundation
import SwiftUI

class MainViewModel: ObservableObject {

    @Published var isRecording = false

    init() {
        refreshIsRecording()
    }

    func refreshIsRecording() -> Void {
        // 録画中（isCaptured）なものがあるか調べる。
        for screen in UIScreen.screens {
            if (screen.isCaptured) {
                isRecording = true
                return
            }
        }
        isRecording = false
    }
}
