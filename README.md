# 勤怠管理システム

従業員の勤怠管理と労働時間追跡のためのSpring Boot Webアプリケーションです。

## 対象ユーザーとユーザー体験

### 一般従業員への価値提供
- **直感的な勤怠記録**: シンプルなワンクリック操作で出勤・退勤を記録
- **透明性の確保**: リアルタイムで自分の労働時間を確認でき、働き方の振り返りが可能
- **ストレスフリーな体験**: 煩雑な紙の管理やExcel入力から解放され、本来の業務に集中
- **公正な労働環境**: 正確な労働時間記録により、適切な評価と給与計算を実現

### 管理者・人事担当者への価値提供
- **効率的な人材管理**: 全従業員の勤怠状況を一元管理し、迅速な意思決定をサポート
- **コンプライアンス対応**: 労働基準法に準拠した正確な労働時間管理で、法的リスクを軽減
- **生産性の可視化**: チーム・個人レベルでの働き方分析により、業務効率化を促進
- **戦略的人事運営**: データ駆動型の人事施策立案で、組織のパフォーマンス向上を実現

### 組織全体への価値提供
- **デジタルトランスフォーメーション**: 紙ベースの古い管理体制からクラウド対応の現代的システムへ移行
- **働き方改革の推進**: 正確な労働時間把握により、ワークライフバランスの改善を支援
- **コスト削減**: 人事管理業務の自動化により、管理コストを大幅削減
- **信頼関係の構築**: 透明で公正な勤怠管理により、従業員と管理者間の信頼関係を強化

## 機能

- **ユーザー認証・認可**: ロールベースのアクセス制御を備えたセキュアなログインシステム
- **勤怠記録**: 出勤・退勤時刻、休憩時間の記録と総労働時間の自動計算
- **管理者ダッシュボード**: ユーザー管理と職場管理のための管理者インターフェース
- **勤怠レポート**: 勤怠履歴の閲覧と総労働時間の確認
- **マルチロール対応**: 一般ユーザーと管理者向けの異なるインターフェース

## 技術スタック

- **バックエンド**: Spring Boot 3.1.4
- **フロントエンド**: Thymeleafテンプレート（HTML/CSS/JavaScript）
- **データベース**: PostgreSQL
- **セキュリティ**: Spring Security（カスタム認証機能付き）
- **ビルドツール**: Maven
- **Javaバージョン**: 17

## プロジェクト構成

```
workManagement/
├── src/
│   ├── main/
│   │   ├── java/com/example/workManagement/
│   │   │   ├── WorkManagementApplication.java
│   │   │   ├── controller/
│   │   │   │   └── PathController.java
│   │   │   ├── model/
│   │   │   │   ├── User.java
│   │   │   │   └── Attendance.java
│   │   │   ├── repository/
│   │   │   │   ├── UserManager.java
│   │   │   │   └── AttendanceManager.java
│   │   │   └── service/
│   │   │       ├── SecurityConfig.java
│   │   │       ├── CustomAuthenticationProvider.java
│   │   │       ├── CustomAuthenticationSuccessHandler.java
│   │   │       ├── CustomAuthenticationFailureHandler.java
│   │   │       └── PasswordEncoderUtil.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/ (CSS、JSファイル)
│   │       └── templates/ (HTMLテンプレート)
│   └── test/ (単体テスト)
├── pom.xml
└── mvnw (Maven wrapper)
```

## 前提条件

- Java 17以上
- PostgreSQLデータベース
- Maven 3.6以上（または同梱のMaven wrapperを使用）

## データベースセットアップ

1. PostgreSQLをインストールし、`attendancemanagement`という名前のデータベースを作成
2. `src/main/resources/application.properties`でデータベース認証情報を更新:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/attendancemanagement
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

## インストール・実行方法

1. リポジトリをクローン
2. `workManagement`ディレクトリに移動
3. Maven wrapperを使用してアプリケーションを実行:
   ```bash
   ./mvnw spring-boot:run
   ```
   またはMavenがインストール済みの場合:
   ```bash
   mvn spring-boot:run
   ```

アプリケーションは `http://localhost:8080` で起動します

## 使用方法

### 一般ユーザー向け:
- 認証情報でログイン
- 勤怠登録（出勤・退勤時刻、休憩時間）
- 勤怠履歴の確認
- 総労働時間の確認

### 管理者向け:
- ユーザーアカウント管理
- 全従業員の勤怠記録閲覧
- 勤怠レポート生成
- 職場設定管理

## テスト実行

以下のコマンドでテストを実行:
```bash
./mvnw test
```

## 依存関係

- Spring Boot Web
- Spring Boot Data JPA
- Spring Boot Security
- Spring Boot Thymeleaf
- PostgreSQL Driver
- Lombok
- Spring Boot DevTools（開発用）

## 開発メモ

- ORMにHibernateを使用し、DDLの自動更新を有効化
- カスタム認証プロバイダーを使ったセキュリティ実装
- サーバーサイドレンダリングにThymeleafを使用
- 静的リソース（CSS/JS）は`/static`ディレクトリから配信