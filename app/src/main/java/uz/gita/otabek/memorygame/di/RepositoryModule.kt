package uz.gita.otabek.memorygame.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.otabek.memorygame.data.repository.GameRepositoryImpl
import uz.gita.otabek.memorygame.domain.repository.GameRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideRepository(gameRepositoryImpl: GameRepositoryImpl): GameRepository
}