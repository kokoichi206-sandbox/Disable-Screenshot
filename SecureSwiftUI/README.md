## SwiftUI でレコーディングを防止

StoryBoard でやったことを SwiftUI に持ってきたかったのですが、実装できず、、、

とりあえず録画の検知のみ実装しました。

### [capturedDidChangeNotification](https://developer.apple.com/documentation/uikit/uiscreen/2921652-captureddidchangenotification) イベントを検知

[capturedDidChangeNotification](https://developer.apple.com/documentation/uikit/uiscreen/2921652-captureddidchangenotification) なるものがあり、これはキャプチャの状態が変更された時（録画開始 or 停止）に発火されるイベントです。

これを swiftUI で検知するには以下のようにします。

```swift
ZStack {
    ...
}
.onReceive(NotificationCenter.default.publisher(for: UIScreen.capturedDidChangeNotification)) { _ in
    // イベント検知後の処理。
}
```

### 録画中の画面があるかどうか

[UIScreen#isCaptured](https://developer.apple.com/documentation/uikit/uiscreen/2921651-iscaptured) を使って判断します。

> A Boolean value that indicates whether the system is actively cloning the screen to another destination.

正確には、画面が他の場所にクローンされているか（ミラーリングされているか？）の判断をしているので、他端末に投影させているかの検知も可能です。

```swift
for screen in UIScreen.screens {
    if (screen.isCaptured) {
        // やばい、録画中だった！
    }
}
// 録画中じゃありませんでした。
}
```

### 録画中を検知して画面を変更する

**ViewModel**

```swift
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
```

**MainScreen**

```swift
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
```

### 実動

![](./readme/swiftui.gif)

### Links

- [capturedDidChangeNotification](https://developer.apple.com/documentation/uikit/uiscreen/2921652-captureddidchangenotification)
- [SwiftUI detect when the user takes a screenshot or screen recording](https://stackoverflow.com/questions/63954077/swiftui-detect-when-the-user-takes-a-screenshot-or-screen-recording)
