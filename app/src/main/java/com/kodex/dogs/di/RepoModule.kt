package com.kodex.dogs.di

import com.kodex.dogs.data.repository.BooksRepo_Impl
import com.kodex.dogs.data.repository.FavoritesFirebaseRepoImpl
import com.kodex.dogs.data.repository.FirebaseAuthRepoImpl
import com.kodex.dogs.data.repository.ModerationRepoImpl
import com.kodex.dogs.data.repository.MyPostsRepositoryImpl
import com.kodex.dogs.data.repository.RelatedBooksRepositoryImpl
import com.kodex.dogs.data.repository.SavedPostsLocalSourceImpl
import com.kodex.dogs.data.repository.SavedPostsRepositoryImpl
import com.kodex.dogs.data.repository.UserSettingsRepoImpl
import com.kodex.dogs.data.repository.UserAccessRepoImpl
import com.kodex.dogs.data.repository.UserRoleRepoImpl
import com.kodex.dogs.data.source.local.SavedPostsLocalSource
import com.kodex.dogs.domain.repository.AuthRepo
import com.kodex.dogs.domain.repository.BooksRepo
import com.kodex.dogs.domain.repository.FavoritesRepo
import com.kodex.dogs.domain.repository.ModerationRepo
import com.kodex.dogs.domain.repository.MyPostsRepo
import com.kodex.dogs.domain.repository.RelatedBooksRepo
import com.kodex.dogs.domain.repository.SavedPostsRepo
import com.kodex.dogs.domain.repository.UserAccessRepo
import com.kodex.dogs.domain.repository.UserRoleRepo
import com.kodex.dogs.domain.repository.UserSettingsRepo
import com.kodex.dogs.domain.role.DefaultRolePermissionChecker
import com.kodex.dogs.domain.role.RolePermissionChecker
import com.kodex.dogs.domain.tarif.AuthStateProvider
import com.kodex.dogs.domain.tarif.DefaultTariffPolicy
import com.kodex.dogs.domain.tarif.FirebaseAuthStateProvider
import com.kodex.dogs.domain.tarif.TariffPolicy
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {


    @Binds
    @Singleton
    abstract fun bindMyPostsRepo(impl: MyPostsRepositoryImpl): MyPostsRepo

    @Binds
    @Singleton
    abstract fun bindRelatedBooksRepository(
        impl: RelatedBooksRepositoryImpl
    ): RelatedBooksRepo

    @Binds
    @Singleton
    abstract fun bindSavedPostsLocalSource(
        impl: SavedPostsLocalSourceImpl
    ): SavedPostsLocalSource

    @Binds
    @Singleton
    abstract fun bindSavedPostsRepository(
        impl: SavedPostsRepositoryImpl
    ): SavedPostsRepo
    @Binds
    abstract fun bindUserAccessRepository(
        impl: UserAccessRepoImpl
    ): UserAccessRepo

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: FirebaseAuthRepoImpl
    ): AuthRepo

    @Binds
    abstract fun bindTariffPolicy(
        impl: DefaultTariffPolicy
    ): TariffPolicy

    @Binds
    abstract fun bindAuthStateProvider(
        impl: FirebaseAuthStateProvider
    ): AuthStateProvider

    @Binds
    abstract fun bindUserRoleRepository(
        impl: UserRoleRepoImpl
    ): UserRoleRepo

    @Binds
    abstract fun bindRolePermissionChecker(
        impl: DefaultRolePermissionChecker
    ): RolePermissionChecker

    @Binds
    @Singleton
    abstract fun bindBooksPepo(
        booksRepoImpl: BooksRepo_Impl
    ): BooksRepo

    @Binds
    @Singleton
    abstract fun bindModerationPepo(
        moderationRepoInfo: ModerationRepoImpl
    ): ModerationRepo

    @Binds
    @Singleton
    abstract fun bindFavoritesRepo(
        favoritesRepoImpl: FavoritesFirebaseRepoImpl
    ): FavoritesRepo

    @Binds
    @Singleton
    abstract fun bindUserSettingsRepo(
        userSettingsRepo: UserSettingsRepoImpl
    ): UserSettingsRepo
}