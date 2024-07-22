package sheridan.sullnich.assignment3.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import sheridan.sullnich.assignment3.data.local.ListItemDao
import sheridan.sullnich.assignment3.data.local.ListItemDatabase
import sheridan.sullnich.assignment3.data.repository.ListItemRepository
import sheridan.sullnich.assignment3.data.repository.LocalListItemRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideListItemDatabase(
        @ApplicationContext context: Context
    ): ListItemDatabase = Room.databaseBuilder(context, ListItemDatabase::class.java, "listitem_database")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideListItemDao(
        database: ListItemDatabase
    ): ListItemDao = database.listItemDao()


    @Singleton
    @Provides
    fun provideListItemRepository(
        listItemDao: ListItemDao
    ): ListItemRepository = LocalListItemRepository(listItemDao)
}