package com.example.mymusicplayer

import android.app.Application
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.mymusicplayer.data.datastore.DataStoreAppFragmentStateSerializer
import com.example.mymusicplayer.data.datastore.DataStoreTypes
import com.example.mymusicplayer.data.permission.StoragePermissionChecker
import com.example.mymusicplayer.data.permission.StoragePermissionCheckerImpl
import com.example.mymusicplayer.data.remote.*
import com.example.mymusicplayer.presentation.AlbumsViewModel
import com.example.mymusicplayer.presentation.FragmentStateViewModel
import com.example.mymusicplayer.presentation.TracksMyMusicViewModel
import com.example.mymusicplayer.presentation.TracksViewModel
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module


class MyApp : Application() {
    private val moduleDataStore: Module
        get() = module {

            factory<TracksMyMusicDataSource> { TracksMyMusicDataSourceImpl(get()) }
            single<Json> { Json }

            single(named(DataStoreTypes.FRAGMENT_STATE)) {
                DataStoreFactory.create(
                    DataStoreAppFragmentStateSerializer(get())
                ) {
                    applicationContext.preferencesDataStoreFile("application_prefs")
                }
            }
        }

    /* private val dbModule: Module
         get() = module {
             single<AlbumsDbDataSource> { AlbumsRoomDataSource(get()) }

             single {

                 Room.databaseBuilder(
                     get(),
                     MyDatabase::class.java, "database-itunes"
                 ).build()
             }

             *//*single {

            Room.databaseBuilder(
                get(),
                MyDatabase::class.java, "database-library"
            ).build()
        }*//*
        }*/

    private val fakeRemoteModule: Module
        get() = module {
            factory<RemoteDataSource> { RemoteDataSourceFake() }
        }

    private val remoteModule: Module
        get() = module {
            factory<RemoteDataSource> { RemoteDataSourceRetrofit() }
        }


    private val viewModelModule: Module
        get() = module {
            single <StoragePermissionChecker>{ params->StoragePermissionCheckerImpl(params.get())}
            //factory { AlbumsRepository(get(), get(), isCacheEnabled = true) }
            viewModelOf(::AlbumsViewModel)
            viewModelOf(::TracksViewModel)
            viewModelOf(::TracksMyMusicViewModel)
            viewModel { FragmentStateViewModel(get(named(DataStoreTypes.FRAGMENT_STATE))) }
        }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                fakeRemoteModule, viewModelModule,
                //dbModule,
                moduleDataStore
            )
        }
    }
}


