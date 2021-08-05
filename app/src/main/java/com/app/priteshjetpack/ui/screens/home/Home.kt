package com.app.priteshjetpack.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.app.priteshjetpack.R
import com.app.priteshjetpack.standardPadding
import com.app.priteshjetpack.ui.screens.home.state.HomeState
import com.app.priteshjetpack.ui.theme.componentColor
import com.app.priteshjetpack.ui.widgets.*
import com.app.priteshjetpack.utils.getStringRes
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.CollapsingToolbarScope
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

val LocalViewModel = compositionLocalOf { HomeViewModel() }

@ExperimentalPagerApi
@ExperimentalFoundationApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home(viewModel: HomeViewModel = HomeViewModel()) {
    CompositionLocalProvider(LocalViewModel provides  viewModel) {
        val state:HomeState  by viewModel.viewState.observeAsState(HomeState.Loading)
        when(state){
            HomeState.Finished -> HomeTree()
            HomeState.Loading -> LoadingView()
        }
    }
}

@ExperimentalPagerApi
@Composable
@Preview(showBackground = true)
fun HomeTree(){
    Column {
        TopAppBar(
            title =getStringRes(R.string.home_title),
            actions =  {
                IconButton(onClick = {  }) {
                    Icon(Icons.Rounded.PlayArrow, contentDescription = null)
                }
            }
        )
        CollapsingToolbarScaffold(
            modifier = Modifier.fillMaxSize(),
            state = rememberCollapsingToolbarScaffoldState(),
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbar = { TopToolbarItem() }
        ) {
            HomeContent()
        }
    }
}

@ExperimentalPagerApi
@Composable
fun CollapsingToolbarScope.TopToolbarItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .road(Alignment.TopCenter, Alignment.TopCenter)
    )

    BannerView(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .parallax()
    )

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .road(Alignment.BottomEnd, Alignment.BottomEnd)
            .pin()
    )
}

@ExperimentalPagerApi
@Composable
fun BannerView(modifier: Modifier) {
    val pagerState = rememberPagerState(
        pageCount = 3,
    )

    val contentDescription = getStringRes(R.string.cd_banner_image)

    Box(modifier) {
        HorizontalPager(
            state = pagerState,
        ) { page ->
            Image(
                modifier = modifier.fillMaxWidth(),
                painter = rememberImagePainter("https://picsum.photos/1000") {
                    memoryCacheKey("$page")
                },
                contentScale = ContentScale.FillWidth,
                contentDescription = contentDescription
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .padding(16.dp)
                .clickable {}
                .align(Alignment.BottomCenter)
        )
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchBar(modifier: Modifier) {
    val alert = LocalAlert.current
    Card(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.componentColor,
        elevation = 4.dp,
        shape = RoundedCornerShape(0),
        onClick = {alert?.invoke()}
    ) {
        Row(
            Modifier.padding(standardPadding / 2), verticalAlignment = Alignment.CenterVertically
        ) {

            val contentDescription = getStringRes(R.string.cd_search)

            val searchHint = getStringRes(R.string.search_hint)
            Icon(
                modifier = Modifier.padding(start = standardPadding / 2),
                imageVector = Icons.Filled.Search,
                contentDescription = contentDescription
            )
            DefaultSpacer()
            Text(text = searchHint)
        }
    }
}

@Composable
fun HomeContent(alert: (() -> Unit)? = null) {
    LazyColumn {
        val contentModifier = Modifier.padding(
            start = standardPadding,
            end = standardPadding
        )
        item {
            val popular = getStringRes(R.string.popular)
            val categories = getStringRes(R.string.categories)
            DefaultSpacer()
            HomeTitle(title = popular, modifier = contentModifier)
            TitleSpacer()
            PopularItems()
            TitleSpacer()
            HomeTitle(title = categories, modifier = contentModifier)
            TitleSpacer()
        }

        lazyGrid(
            items = (1..20).toList(),
            rowSize = 2,
            modifier = contentModifier
        ) { item, modifier ->
            CategoryRowItem(item = item, modifier = modifier)
        }

        item {
            Spacer(modifier = Modifier.size(70.dp))
        }

    }
}

@Composable
fun HomeTitle(title: String, modifier: Modifier) {
    Text(
        text = title,
        style =
        MaterialTheme.typography.h6,
        modifier = modifier.wrapContentSize()
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PopularItems() {
    val viewModel = LocalViewModel.current
    LazyRow {
        items(viewModel.popularItems) {
            val alert = LocalAlert.current
            Spacer(modifier = Modifier.padding(standardPadding / 2))
            Card(
                modifier = Modifier.width(200.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = 8.dp,
                onClick = { alert?.invoke() }
            ) {
                ListItem(
                    modifier = Modifier.width(250.dp),
                    icon = { Icon(Icons.Filled.Favorite, null) },
                    text = { Text(text = "Title $it") },
                    secondaryText = { Text(text = "Description $it") }
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryRowItem(item: Int, modifier: Modifier) {
    val alert = LocalAlert.current
    Card(
        modifier = modifier.height(130.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        onClick = { alert?.invoke() }
    ) {
        val contentDescription = getStringRes(R.string.cd_category_image)

        Image(
            modifier = modifier.fillMaxSize(),
            painter = rememberImagePainter("https://picsum.photos/600") {
                memoryCacheKey("$item")
            },
            contentScale = ContentScale.FillBounds,
            contentDescription = contentDescription
        )

        Text(
            text = "item $item",
            modifier = Modifier
                .wrapContentSize(Alignment.BottomStart)
                .padding(start = standardPadding, bottom = standardPadding / 2)
        )

    }
}