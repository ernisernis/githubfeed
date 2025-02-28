GithubFeed App
==================

A simple app to browse through global Github users timeline, see specific users profile commit history or investigate users discussions feed!

Github feeds API result is stored in Room DB. It is cached and used to show the list screen if user does not have internet access. However, the user would not be able to enter the feeds while being disconnected.

## Tech Stack

### Core
- [Kotlin](https://kotlinlang.org/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose) (UI)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) (Structured concurrency)
- [Kotlin Flow](https://kotlinlang.org/docs/flow.html) (Reactive data stream)
- [Hilt](https://dagger.dev/hilt/) (DI)

### Testing
- [JUnit4](https://github.com/junit-team/junit4) (Unit testing)

### Local Persistence
- [Room DB](https://developer.android.com/training/data-storage/room) (SQLite ORM)

### Networking
- [Ktor client](https://ktor.io/docs/getting-started-ktor-client.html) (HTTP client)
- [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization) (JSON serialization)
- [RSS Parser](https://github.com/prof18/RSS-Parser) (RSS Parser)

### API
- [Github](https://docs.github.com/en/rest/activity/feeds?apiVersion=2022-11-28)
