# 🛠️ Lg5 jvm checkstyle plugin

<img src="https://avatars.githubusercontent.com/u/105936384?s=400&u=290ae673580a956864a07d4aef8e4448372a836b&v=4" align="left" width="172px" height="172px"/>
<img align="left" width="0" height="172px" hspace="10"/>

> 👋 Configurations to create a plugin project with Gradle and Kotlin as the language.
>

[![lg-labs][0]][1]
[![License][2]][LIC]

From **Lg Pentagon** or **lg5**! Get a config basic over Gradle.kts.

For more information, check this pages https://lufgarciaqu.medium.com.

# Using Gradle 8.6, JDK 21
[More details][4]
## 🚀 Build project

Install 1/1: Install the plugin in your project.

```bash
plugins {
  id 'io.github.lg5.jvm.checkstyle.code' version '1.0.0-alpha'
}
```
## 📚Contents

> Thanks to [Semyon Kirekov][8]

![ Structure!][img1]

# The Version Catalog with [TOML][5] file
The TOML file consists of 4 major sections:

the [versions] section is used to declare versions which can be referenced by dependencies

the [libraries] section is used to declare the aliases to coordinates

the [bundles] section is used to declare dependency bundles

the [plugins] section is used to declare plugins
## ⚖️ License

The MIT License (MIT). Please see [License][LIC] for more information.


[0]: https://img.shields.io/badge/LgLabs-community-blue?style=flat-square
[1]: https://lufgarciaqu.medium.com
[2]: https://img.shields.io/badge/license-MIT-green?style=flat-square
[4]: https://docs.gradle.org/current/userguide/userguide.html
[5]: https://docs.gradle.org/current/userguide/platforms.html
[6]: https://docs.gradle.org/current/userguide/checkstyle_plugin.html#header
[7]: https://docs.gradle.org/current/userguide/custom_plugins.html#header
[8]: https://dev.to/kirekov/custom-gradle-plugin-for-unified-static-code-analysis-4bj0

[LIC]: LICENSE

[img1]: https://github.com/lg-labs-pentagon/lg-labs-boot-parent/assets/105936384/31c27db8-1e77-478d-a38e-7acf6ba2571c
