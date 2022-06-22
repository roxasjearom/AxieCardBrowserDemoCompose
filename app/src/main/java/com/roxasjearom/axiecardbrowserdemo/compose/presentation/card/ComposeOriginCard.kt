package com.roxasjearom.axiecardbrowserdemo.compose.presentation.card

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.roxasjearom.axiecardbrowserdemo.compose.R
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.OriginCard

@Composable
fun OriginCardScreen(cardViewModel: CardViewModel = viewModel()) {
    OriginCardList(
        cards = cardViewModel.cardsUiState.collectAsState().value.cards,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun OriginCardList(
    cards: List<OriginCard>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
    ) {
        items(cards) { card ->
            ComposeOriginCard(card = card)
        }
    }
}

@Composable
fun ComposeOriginCard(card: OriginCard) {
    ConstraintLayout(
        modifier = Modifier.wrapContentSize()
    ) {
        val (mainCardImage, cardNameText, cardDescriptionText) = createRefs()
        val midGuideLine = createGuidelineFromBottom(0.35f)
        val bottomGuideLine = createGuidelineFromBottom(0.05f)
        val startGuideLine = createGuidelineFromStart(0.4f)

        val imageUrl =
            "https://cdn.axieinfinity.com/game/origin-cards/base/version-20220422/${card.cardImage}"
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build(),
            placeholder = painterResource(R.drawable.card_frame),
            contentDescription = stringResource(R.string.cd_card_background),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(182.dp)
                .aspectRatio(0.65f)
                .constrainAs(mainCardImage) {
                    top.linkTo(parent.top)
                }
        )
        Text(
            text = card.cardName,
            style = MaterialTheme.typography.body2,
            color = Color.White,
            modifier = Modifier.constrainAs(cardNameText) {
                start.linkTo(startGuideLine)
                bottom.linkTo(midGuideLine)
            }
        )
        Text(
            text = card.cardText.orEmpty(),
            textAlign = TextAlign.Center,
            color = Color.White,
            style = MaterialTheme.typography.caption,
            fontSize = 11.sp,
            modifier = Modifier.constrainAs(cardDescriptionText) {
                top.linkTo(midGuideLine)
                bottom.linkTo(bottomGuideLine)
                start.linkTo(mainCardImage.start, 32.dp)
                end.linkTo(mainCardImage.end, 12.dp)
                width = Dimension.fillToConstraints
            }
        )

    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun CardPreview() {
    ComposeOriginCard(fakeCardList.first())
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun CardListPreview() {
    OriginCardList(cards = fakeCardList)
}
