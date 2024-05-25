# 🛠️ Lg5 Spring to create μ-services, simply faster

[![lg-labs][0]][1]
[![License][2]][LIC]

<img src="https://avatars.githubusercontent.com/u/105936384?s=400&u=290ae673580a956864a07d4aef8e4448372a836b&v=4" align="left" width="172px" height="172px"/>
<img align="left" width="0" height="172px" hspace="10"/>

> 👋 Manage all Spring Boot dependencies to create new applications using this library. It's just faster.
>

From **Lg Pentagon** or **lg5**! Get [Lg5-Spring][4] to develop μ-services faster.

For more information, check this pages https://lufgarciaqu.medium.com.
<h1>Hex Arch, DDD,SAGA, Outbox&Kafka</h1><br/>

## Use Spring Boot `3.2.X`, JDK 21

[More details][4]

## 🚀 Using Lg5 Spring

> Install 1/2: Add this to pom.xml:

```xml title="pom.xml" linenums="1" hl_lines="4"
<parent>
  <groupId>com.lg5.spring</groupId>
  <artifactId>lg5-spring-parent</artifactId>
  <version>1.0.0-alpha.[check lts version]</version>
</parent>   
```
_Note: Please check the [latest version][5]_

Install 2/2: Install the dependencies in your project.

```bash title="Terminal" linenums="1" hl_lines="1"
mvn install
```

# 🏗️🏗️ [Repo with Real Example using Lg5Spring][7]

Also, [The template repository(blank-service)][8] use it.


## Important Modules

* [lg5-spring](lg5-spring)
* [lg5-jvm](lg5-JVM)

# ⚠️ Important

> * If you change something into some submodule, you must verify and sync the `lg5-spring-parent`
>
> * If you create a submodule, you must verify and sync the `lg5-spring-parent`
>

## 🚀 Run locally


### You can ...

Using `makefile`

### Start with

😀 To **start** the build for all dependencies.

```shell
make all-build
```

### Publish Maven Local

😀 To **start** publishing in `maven` local.

```shell
make publish-local
```

> So, you must check the folder `.m2`.


### The Version Catalog with [TOML][6] file


The TOML file consists of 4 major sections:

the **[versions]** section is used to declare versions which can be referenced by dependencies

the **[libraries]** section is used to declare the aliases to coordinates

the **[bundles]** section is used to declare dependency bundles

the **[plugins]** section is used to declare plugins

## ⚖️ License

The MIT License (MIT). Please see [License][LIC] for more information.


[0]: https://img.shields.io/badge/LgLabs-community-blue?style=flat-square

[1]: https://lufgarciaqu.medium.com

[2]: https://img.shields.io/badge/license-MIT-green?style=flat-square

[4]: https://github.com/lg-labs-pentagon/lg5-spring

[5]: https://github.com/lg-labs-pentagon/lg5-spring/packages/2125499

[6]: https://docs.gradle.org/current/userguide/platforms.html

[7]: https://github.com/lg-labs/food-ordering-system
[8]: https://github.com/lg-labs/blank-service




[LIC]: LICENSE

[img1]: https://github.com/lg-labs-pentagon/lg-labs-boot-parent/assets/105936384/31c27db8-1e77-478d-a38e-7acf6ba2571c






