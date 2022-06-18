package com.example.mymusicplayer

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.mymusicplayer.data.datastore.DataStoreAppFragmentStateSerializer
import com.example.mymusicplayer.data.datastore.DataStoreTypes
import com.example.mymusicplayer.data.db.AlbumsRepository
import com.example.mymusicplayer.data.permission.StoragePermissionChecker
import com.example.mymusicplayer.data.permission.StoragePermissionCheckerImpl
import com.example.mymusicplayer.data.remote.*
import com.example.mymusicplayer.domain.purchase.PurchaseMakeInteractor
import com.example.mymusicplayer.domain.purchase.PurchaseMakerInteractorFake
import com.example.mymusicplayer.domain.purchase.PurchaseStateInteractor
import com.example.mymusicplayer.domain.purchase.PurchaseStateInteractorFake
import com.example.mymusicplayer.presentation.*
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
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

             single {

                 Room.databaseBuilder(
                     get(),
                     MyDatabase::class.java, "database-library"
                 ).build()
             }
         }*/

    /*private val remoteModule: Module
        get() = module {
            // factory<RemoteDataSource> { params -> RemoteDataSourceRetrofit(baseUrl = params.get()) }
            factory<RemoteDataSource> { RemoteDataSourceRetrofit() }
        }*/

    private val fakeRemoteModule: Module
        get() = module {
            factory<RemoteDataSource> { RemoteDataSourceFake() }
        }

    private val modulePurchases: Module
        get() = module {

            singleOf(::PurchaseStateInteractorFake)
            {
                bind<PurchaseStateInteractor>()
            }
            singleOf(::PurchaseMakerInteractorFake) {
                bind<PurchaseMakeInteractor>()
            }
        }

    private val moduleSharedPreferences: Module
        get() = module {
            single {
                val context: Context = get()
                context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
            }
        }

    private val viewModelModule: Module
        get() = module {
            single<StoragePermissionChecker> { params -> StoragePermissionCheckerImpl(params.get()) }
            factory { AlbumsRepository(get(), get(), isCacheEnabled = true) }
            viewModelOf(::AlbumsViewModel)
            viewModelOf(::TracksViewModel)
            viewModelOf(::TracksMyMusicViewModel)
            viewModelOf(::PurchaseViewModel)
            viewModel { FragmentStateViewModel(get(named(DataStoreTypes.FRAGMENT_STATE))) }
        }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                fakeRemoteModule,
                viewModelModule,
                modulePurchases,
                moduleSharedPreferences,
                //dbModule,
                moduleDataStore
            )
        }
    }
}
