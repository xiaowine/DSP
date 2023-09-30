# DSP

#### 这是SharedPreferences和XSharedPreferences的包装

---

### 使用方法

### 1. 项目 Gradle 添加 JitPack 依赖

```groovy
allprojects {
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
}
```

or

```kotlin
allprojects {
    repositories {
        // ...
        maven("https://jitpack.io")
    }
}
```

### 2. 要使用的模块下添加 Lyric-Getter-API 依赖

最新版本⬇️⬇️⬇️

[![](https://jitpack.io/v/xiaowine/dsp.svg)](https://jitpack.io/#xiaowine/dsp/)

```groovy
dependencies {
    // ...
    implementation 'com.github.xiaowine:dsp:<VERSION>'
}
```

or

```kotlin
dependencies {
    // ...
    implementation("com.github.xiaowine:dsp:<VERSION>")
}
```

### 3.具体使用请见[Demo](/app/src/main/java/cn/xiaowine/dsp/MainActivity.kt)
---

## 注意 若开启了 proguard 请保证 API 类不被混淆:

```shrinker_config
-keep class cn.xiaowine.dsp.*{*;}
```

## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=xiaowine/dsp&type=Timeline)](https://star-history.com/#xiaowine/dsp&Timeline)