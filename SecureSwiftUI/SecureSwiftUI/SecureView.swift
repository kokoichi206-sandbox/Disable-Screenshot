//
//  MainViewController.swift
//  SecureSwiftUI
//
//  Created by Takahiro Tominaga on 2022/10/18.
//

import SwiftUI
import UIKit

struct SecureView: UIViewControllerRepresentable {
    typealias UIViewControllerType = SecureViewController

    func makeUIViewController(context: Context) -> UIViewControllerType {
        SecureViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
    }

    func makeCoordinator() -> Coordinator {
        Coordinator()
    }

    class Coordinator {
    }
}

