package com.roxasjearom.axiecardbrowserdemo.compose.presentation.card

import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.OriginCard

val fakeCardList = listOf(
    OriginCard(
        cardClass = "Aquatic",
        partType = "back",
        cardName = "Sponge",
        cardType = "secret",
        cardDamage = 0,
        cardShield = 70,
        cardHeal = 0,
        cardEnergy = "1",
        cardImage = "aquatic-back-08.pn",
        cardText = "Target any ally. Next turn, apply 5 {Damage Boost} to shielded target if its shield is broken.",
        tags = "skill,secret,shield"
    ),
    OriginCard(
        cardClass = "Beast",
        partType = "horn",
        cardName = "Arco",
        cardType = "attack",
        cardDamage = 65,
        cardShield = 0,
        cardHeal = 0,
        cardEnergy = "1",
        tags = "attack,non_multihit,single_attack",
        cardImage = "beast-horn-12.png",
        cardText = "This does not trigger Secrets.",
    ),
    OriginCard(
        cardClass = "Bird",
        partType = "back",
        cardName = "Balloon",
        cardType = "attack",
        cardDamage = 60,
        cardShield = 0,
        cardHeal = 0,
        cardEnergy = "1",
        tags = "attack,non_multihit,single_attack,debuff,curse",
        cardImage = "bird-back-02.png",
        cardText = "Apply {Fear} for 4 turns.",
    ),
    OriginCard(
        cardClass = "Bug",
        partType = "tail",
        cardName = "Ant",
        cardType = "skill",
        cardDamage = 0,
        cardShield = 70,
        cardHeal = 0,
        cardEnergy = "1",
        tags = "skill,shield,taunt",
        cardImage = "bug-tail-02.png",
        cardText = "Apply {Taunt} to this Axie for 4 turns.",
    ),
)
