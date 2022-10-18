# Disable-Screenshot

各プラットフォームにおけるスクショ・キャプチャ防止機能の実装方法を調査した。

## Android

- FLAG_SECURE を window に設定したら一発
  - [詳細](./android/)

## iOS

- UITextField#isSecureTextEntry を sublayer として使う（トリッキー）
  - [詳細](./SecureStoryBoard/)
- capturedDidChangeNotification のイベントを拾う（キャプチャのみ）
  - [詳細](./SecureSwiftUI/)

## Web(ブラウザ)

- 実行するには、js では無理そう
- DRM などのコンテンツ保護を使うしかない
