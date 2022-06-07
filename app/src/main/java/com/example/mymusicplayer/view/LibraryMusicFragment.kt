package com.example.mymusicplayer.view

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.mymusicplayer.R
import com.example.mymusicplayer.data.UploadMusicWorker
import com.example.mymusicplayer.data.db.AlbumsRepository
import com.example.mymusicplayer.data.db.LibraryRoomDataSource
import com.example.mymusicplayer.data.libraryroom.LibraryDatabase
import com.example.mymusicplayer.data.remote.LibraryRemoteDataSourceRetrofit
import com.example.mymusicplayer.domain.Tracks
import com.example.mymusicplayer.presentation.AlbumsViewModel
import com.example.mymusicplayer.presentation.TracksViewModel
import kotlinx.coroutines.launch

class LibraryMusicFragment : Fragment() {
    lateinit var viewBinder: LibraryMusicFragmentBinder
    private val viewModel: AlbumsViewModel = AlbumsViewModel(
        AlbumsRepository(
            LibraryRoomDataSource(Room.databaseBuilder(requireContext(), LibraryDatabase::class.java, "database-name").build()),
            LibraryRemoteDataSourceRetrofit(),
            true
        )
    )
    private val tracksLibraryViewModel: TracksViewModel =
        TracksViewModel(LibraryRemoteDataSourceRetrofit())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinder = LibraryMusicFragmentBinder(this, ::onItemClick)

        return viewBinder.onCreateView(inflater, container, savedInstanceState)
    }

     val libraryRemoteDataSourceRetrofit: LibraryRemoteDataSourceRetrofit =
         LibraryRemoteDataSourceRetrofit()

    private fun onItemClick(view: View, tracks: Tracks): Boolean {

        val data = Data.Builder()
        data.putString("1", tracks.url)

        val uploadWork =
            OneTimeWorkRequestBuilder<UploadMusicWorker>().setInputData(data.build()).build()


        WorkManager.getInstance(requireContext()).enqueue(uploadWork)

        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.iTunes)
            ?.setOnClickListener {
                val navController = findNavController()

                val action =
                    LibraryMusicFragmentDirections.actionLibraryMusicFragmentToITunesMusicFragment()
                navController.navigate(action)
                changeCurrentSelection(
                    (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.iTunes) as TextView,
                    (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.library) as TextView,
                    (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.myMusic) as TextView,
                )
            }

        (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.myMusic)
            ?.setOnClickListener {
                val navController = findNavController()

                val action =
                    LibraryMusicFragmentDirections.actionLibraryMusicFragmentToMyMusicFragment()
                navController.navigate(action)
                changeCurrentSelection(
                    (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.myMusic) as TextView,
                    (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.library) as TextView,
                    (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.iTunes) as TextView,
                )
            }

        (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.imagePurchase)
            ?.setOnClickListener {
                val navController = findNavController()

                val action =
                    LibraryMusicFragmentDirections.actionLibraryMusicFragmentToPurchaseFragment()
                navController.navigate(action)
            }
        viewModel.loadData()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateITunes.collect {
                    if (it != null)
                        viewBinder.onDataLoaded(it.getOrThrow())
                }
            }
        }

        tracksLibraryViewModel.loadTracks()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tracksLibraryViewModel.stateITunes.collect {
                    viewBinder.tracksLoaded(it)
                }
            }
        }
    }

    private fun changeCurrentSelection(primary: TextView, secondary: TextView, three: TextView) {
        primary.typeface = Typeface.DEFAULT_BOLD
        primary.textSize = 18f
        primary.setTextColor(ContextCompat.getColor(requireContext(), R.color.selectedTextColor))
        primary.isClickable = false

        secondary.typeface = Typeface.create("light", Typeface.NORMAL)
        secondary.textSize = 16f
        secondary.setTextColor(ContextCompat.getColor(requireContext(), R.color.textColor))
        secondary.isClickable = true

        three.typeface = Typeface.create("light", Typeface.NORMAL)
        three.textSize = 16f
        three.setTextColor(ContextCompat.getColor(requireContext(), R.color.textColor))
        three.isClickable = true
    }
}