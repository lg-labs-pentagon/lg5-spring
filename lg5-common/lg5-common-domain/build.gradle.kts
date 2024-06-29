dependencies {
    api(libs.ddd.common.domain)
    api(project(":lg5-jvm-utils")){
        exclude(libs.lombok.get().group, libs.lombok.get().name)
        exclude(libs.mapstruct.get().group, libs.mapstruct.get().name)
    }
}