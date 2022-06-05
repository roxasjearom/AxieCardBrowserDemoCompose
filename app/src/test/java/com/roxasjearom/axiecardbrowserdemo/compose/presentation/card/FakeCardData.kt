package com.roxasjearom.axiecardbrowserdemo.compose.presentation.card

import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.OriginCard

/**
Composed of 6 Origin cards. 1 instance for each card class.
This data has 3 back cards (Aquatic, Bird, Reptile), 2 horn cards (Beast, Plant) and 1 tail card (Bug).
 **/
val fakeCardData = listOf(
    OriginCard(
        cardClass = "Aquatic",
        partType = "back",
        cardName = "Anemone",
        cardType = "attack",
        cardDamage = 50,
        cardShield = 0,
        cardHeal = 0,
        cardEnergy = "1",
        cardImage = "aquatic-back-10.png",
        cardText = "Whenever this is drawn, heal 20 HP.",
        tags = "attack,non_multihit,single_attack,on_draw"
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
    OriginCard(
        cardClass = "Plant",
        partType = "horn",
        cardName = "Bamboo Shoot",
        cardType = "attack",
        cardDamage = 65,
        cardShield = 0,
        cardHeal = 0,
        cardEnergy = "1",
        tags = "attack,non_multihit,single_attack",
        cardImage = "plant-horn-02.png",
        cardText = "Deal 20 more DMG for each Bamboo card used this turn.",
    ),
    OriginCard(
        cardClass = "Reptile",
        partType = "back",
        cardName = "Bone Sail",
        cardType = "secret",
        cardDamage = 0,
        cardShield = 70,
        cardHeal = 0,
        cardEnergy = "1",
        tags = "skill,secret,shield",
        cardImage = "reptile-back-02.png",
        cardText = "Target any ally. Next turn, after the target is attacked, draw 1 card.",
    )
)