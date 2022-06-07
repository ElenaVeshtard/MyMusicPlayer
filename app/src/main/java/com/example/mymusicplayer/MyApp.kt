package com.example.mymusicplayer

import android.app.Application
import androidx.room.Room
import com.example.mymusicplayer.data.db.AlbumsDbDataSource
import com.example.mymusicplayer.data.db.AlbumsRepository
import com.example.mymusicplayer.data.db.LibraryRoomDataSource
import com.example.mymusicplayer.data.libraryroom.LibraryDatabase
import com.example.mymusicplayer.data.remote.RemoteDataSourceRetrofit
import com.example.mymusicplayer.presentation.AlbumsViewModel
import com.example.mymusicplayer.presentation.TracksViewModel
import com.example.mymusicplayer.data.remote.ITunesRemoteDataSourceRetrofit
import com.example.mymusicplayer.data.remote.LibraryRemoteDataSourceRetrofit
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

class MyApp : Application() {

    private val moduleDatasource: Module
        get() = module {

            factory<RemoteDataSourceRetrofit> { LibraryRemoteDataSourceRetrofit() }
            single<AlbumsDbDataSource> { LibraryRoomDataSource(get()) }
            //single<AlbumsDbDataSource> { ITunesRoomDataSource(get()) }

            single {

                Room.databaseBuilder(
                    get(),
                    LibraryDatabase::class.java, "database-name"
                ).build()
            }
        }

    private val moduleRepository: Module
        get() = module {
            factory { AlbumsRepository(get(), get(), isCacheEnabled = true) }
            viewModel { AlbumsViewModel(get()) }
        }
    /*  private val itunesModule: Module
          get() = module {
              factoryOf<RemoteDataSourceRetrofit>(::ITunesRemoteDataSourceRetrofit)
          }

      private val libraryModule: Module
          get() = module {
              factoryOf<RemoteDataSourceRetrofit>(::LibraryRemoteDataSourceRetrofit)
          }*/

    private val viewModelModule: Module
        get() = module {
            // viewModelOf(::AlbumsViewModel)
            viewModelOf(::TracksViewModel)
        }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                moduleDatasource, moduleRepository,
                // libraryModule, itunesModule,
                viewModelModule)
        }
    }
}
