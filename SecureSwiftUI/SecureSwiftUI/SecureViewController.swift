//
//  SecureViewController.swift
//  SecureSwiftUI
//
//  Created by Takahiro Tominaga on 2022/10/18.
//

import UIKit

class SecureViewController: UIViewController {

    lazy var mainView: UIView = {
        let rect1 = UIView(frame: CGRect(x: 0, y: 0, width: 200, height: 200))
        rect1.layer.shadowColor = UIColor.black.cgColor
        rect1.layer.borderColor = UIColor.black.cgColor
        rect1.layer.borderWidth = 2
        rect1.layer.cornerRadius = 5
        rect1.layer.shadowRadius = 2
        rect1.backgroundColor = .red

        rect1.center = CGPoint(
            x: view.frame.size.width / 2,
            y: view.frame.size.height / 2
        )

        view.addSubview(rect1)
        view.backgroundColor = .white
        return view
    }()

    override func viewDidLoad() {
        super.viewDidLoad()

        mainView.makeSecure()
    }
}

extension UIView {
    func makeSecure() {
        // Thread 1: EXC_BAD_ACCESS (code=2, address=0x16b6affb0)
        // のエラーが出て、スクショ時に落ちる！
        DispatchQueue.main.async {
            let bgView = UIView(frame: UIScreen.main.bounds)
            bgView.backgroundColor = .black
            self.superview?.insertSubview(bgView, at: 0)

            let field = UITextField()
            field.isSecureTextEntry = true
            self.addSubview(field)

            field.centerYAnchor.constraint(equalTo: self.centerYAnchor).isActive = true
            field.centerXAnchor.constraint(equalTo: self.centerXAnchor).isActive = true
            self.layer.superlayer?.addSublayer(field.layer)
            field.layer.sublayers?.first?.addSublayer(self.layer)
        }
    }
}
