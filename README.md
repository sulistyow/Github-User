# Github User

Submission for Dicoding - Belajar Fundamental Aplikasi Android 

## How App Work
1. This App will display list of Github users from Github Api
2. You can search by username of Github users you want
3. You can click any users and view detail profile
4. You can add user to favorite and it will dislplay on Favorites tab
5. You can switch to Dark mode by click switch button in home menu

## Prerequisites
Before run this app, you need to add Github Api address and your Github Personal Access Token in `Build.gradle(Module ..)`

```yaml
buildConfigField("String", "BASE_URL", ...)
buildConfigField("String", "USER_TOKEN", ...)
```

## Tech Stack
- Clean Architecture
- MVVM Architecture Pattern
- Dependency Injection with Koin
- Coroutines Flow
- Room Database

## Dependencies
- [Glide](https://github.com/bumptech/glide)
- [Lottie](https://github.com/airbnb/lottie-android)
- [AndroidX](https://mvnrepository.com/artifact/androidx)
- [Lifecycle & LiveData](https://developer.android.com/jetpack/androidx/releases/lifecycle)
- [Koin](https://github.com/InsertKoinIO/koin)
- [Retrofit](https://square.github.io/retrofit/)
- [Coroutines Flow](https://developer.android.com/kotlin/flow)
- [Room](https://developer.android.com/training/data-storage/room?gclid=Cj0KCQiA0MD_BRCTARIsADXoopYlw1cozWjwyR-ucLYa-aoqYlZeJmxG34JnhByjApMNwuchOcAzcy0aAgGHEALw_wcB&gclsrc=aw.ds)
