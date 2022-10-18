//
//  ContentView.swift
//  SecureSwiftUI
//
//  Created by Takahiro Tominaga on 2022/10/18.
//

import SwiftUI

struct ContentView: View {

    @ObservedObject var viewModel = MainViewModel()

    var body: some View {

        ZStack {
            
            Text("Hello, world!!")
                .padding()

            // レコーディング検出時
            if (viewModel.isRecording) {
                Text("Detect Recording")
                    .foregroundColor(.red)
                    .font(.system(size: 30, weight: .bold))
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
                    .background(.black)
            }
        }
        .ignoresSafeArea(.all)
        .onReceive(NotificationCenter.default.publisher(for: UIScreen.capturedDidChangeNotification)) { _ in
            viewModel.refreshIsRecording()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
