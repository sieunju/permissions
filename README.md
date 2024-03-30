
> [![](https://jitpack.io/v/sieunju/permissions.svg)](https://jitpack.io/#sieunju/permissions)   
> 권한 API가 AppCompat 1.3.0 이후로 변경이 되면서 그에 맞게 권한 라이브러리를 만들었습니다.
---

![AndroidMinSdkVersion](https://img.shields.io/badge/minSdkVersion-23-green.svg) ![AndroidTargetSdkVersion](https://img.shields.io/badge/targetSdkVersion-34-brightgreen.svg)

#### 라이브러리 추가 하는 방법
*Project Gradle*
```groovy
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

- App Module Gradle

```groovy
dependencies {
  implementation 'com.github.sieunju:permissions:$latestVersion'
}
```

## 유의사항
- 혹시나 머티리얼을 사용하시거나 프로젝트에 사용중인 라이브러리랑 충돌이 일어나는 경우에는 아래와 같이 사용해주시면 됩니다. 🙇‍♂️
- A.K.A exclude
```groovy
implementation("com.github.sieunju.permissions:$lateversion") {
        exclude("androidx.appcompat:appcompat")
}
```
