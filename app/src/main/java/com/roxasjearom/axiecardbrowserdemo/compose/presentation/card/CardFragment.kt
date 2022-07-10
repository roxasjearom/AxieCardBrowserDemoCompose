package com.roxasjearom.axiecardbrowserdemo.compose.presentation.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.roxasjearom.axiecardbrowserdemo.compose.databinding.FragmentCardBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CardFragment : Fragment() {

    private val viewModel: CardViewModel by viewModels()

    private var _binding: FragmentCardBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var cardAdapter: CardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardListRecyclerView.apply {
            this.adapter = cardAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
        setObserver()
        setBottomSheetFilters()
    }

    private fun setObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.cardsUiState.collect { uiState ->
                        cardAdapter.setCards(uiState.cards)

                        binding.noResultImage.visibility =
                            if (uiState.cards.isEmpty()) View.VISIBLE else View.GONE

                        binding.bottomSheetFilters.clearButton.visibility =
                            if (uiState.hasFilter) View.VISIBLE else View.INVISIBLE

                        binding.progressIndicator.visibility =
                            if (uiState.isLoading) View.VISIBLE else View.GONE

                        uiState.message?.let { message ->
                            Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
                            viewModel.messageShown()
                        }
                    }
                }
            }
        }
    }

    private fun setBottomSheetFilters() {
        /*with(binding.bottomSheetFilters) {
            chipAqua.setOnClickListener {
                viewModel.filterCards(CardClassFilter(CardClass.AQUATIC.name, CardClass.AQUATIC))
            }
            chipBeast.setOnClickListener {
                viewModel.filterCards(CardClassFilter(CardClass.BEAST.name, CardClass.BEAST))
            }
            chipBird.setOnClickListener {
                viewModel.filterCards(CardClassFilter(CardClass.BIRD.name, CardClass.BIRD))
            }
            chipBug.setOnClickListener {
                viewModel.filterCards(CardClassFilter(CardClass.BUG.name, CardClass.BUG))
            }
            chipPlant.setOnClickListener {
                viewModel.filterCards(CardClassFilter(CardClass.PLANT.name, CardClass.PLANT))
            }
            chipReptile.setOnClickListener {
                viewModel.filterCards(CardClassFilter(CardClass.REPTILE.name, CardClass.REPTILE))
            }

            chipEars.setOnClickListener {
                viewModel.filterCards(PartTypeFilter(PartType.EARS.name, PartType.EARS))
            }
            chipEyes.setOnClickListener {
                viewModel.filterCards(PartTypeFilter(PartType.EYES.name, PartType.EYES))
            }
            chipBack.setOnClickListener {
                viewModel.filterCards(PartTypeFilter(PartType.BACK.name, PartType.BACK))
            }
            chipMouth.setOnClickListener {
                viewModel.filterCards(PartTypeFilter(PartType.MOUTH.name, PartType.MOUTH))
            }
            chipHorn.setOnClickListener {
                viewModel.filterCards(PartTypeFilter(PartType.HORN.name, PartType.HORN))
            }
            chipTail.setOnClickListener {
                viewModel.filterCards(PartTypeFilter(PartType.TAIL.name, PartType.TAIL))
            }

            clearButton.setOnClickListener {
                viewModel.clearFilters()
                classChipGroup.clearCheck()
                bodyPartsChipGroup.clearCheck()
            }
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
