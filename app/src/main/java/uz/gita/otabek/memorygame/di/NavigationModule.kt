package uz.gita.otabek.memorygame.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.otabek.memorygame.navigation.AppNavigationHandler
import uz.gita.otabek.memorygame.navigation.AppNavigator
import uz.gita.otabek.memorygame.navigation.AppNavigatorDispatcher

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    fun provideAppNavigator(impl: AppNavigatorDispatcher): AppNavigator

    @Binds
    fun provideAppNavigationHandler(impl: AppNavigatorDispatcher): AppNavigationHandler
}