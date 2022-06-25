package com.roxasjearom.axiecardbrowserdemo.compose.domain.model

sealed class CardFilter(val id: String)
class CardClassFilter(id: String, val cardClass: CardClass) : CardFilter(id)
class PartTypeFilter(id: String, val partType: PartType) : CardFilter(id)

enum class CardClass {
    AQUATIC,
    BEAST,
    BIRD,
    BUG,
    PLANT,
    REPTILE,
}

enum class PartType {
    EYES,
    EARS,
    BACK,
    MOUTH,
    HORN,
    TAIL,
}

fun String.toCardClass(): CardClass? {
    return when {
        equals("aquatic", true) -> CardClass.AQUATIC
        equals("bird", true) -> CardClass.BIRD
        equals("beast", true) -> CardClass.BEAST
        equals("bug", true) -> CardClass.BUG
        equals("plant", true) -> CardClass.PLANT
        equals("reptile", true) -> CardClass.REPTILE
        else -> null
    }
}

fun String.toPartType(): PartType? {
    return when {
        equals("eyes", true) -> PartType.EYES
        equals("ears", true) -> PartType.EARS
        equals("back", true) -> PartType.BACK
        equals("mouth", true) -> PartType.MOUTH
        equals("horn", true) -> PartType.HORN
        equals("tail", true) -> PartType.TAIL
        else -> null
    }
}
