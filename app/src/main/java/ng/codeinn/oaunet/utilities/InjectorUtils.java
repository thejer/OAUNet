/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ng.codeinn.oaunet.utilities;

import android.content.Context;

import ng.codeinn.oaunet.AppExecutors;
import ng.codeinn.oaunet.data.ItemsRepository;
import ng.codeinn.oaunet.data.database.ItemsDatabase;
import ng.codeinn.oaunet.data.network.ItemsNetworkDataSource;
import ng.codeinn.oaunet.ui.detail.DetailViewModelFactory;
import ng.codeinn.oaunet.ui.list.MainViewModelFactory;

/**
 * Provides static methods to inject the various classes needed for Sunshine
 */
public class InjectorUtils {

    public static ItemsRepository provideRepository(Context context) {
        ItemsDatabase database = ItemsDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        ItemsNetworkDataSource networkDataSource =
                ItemsNetworkDataSource.getInstance(context.getApplicationContext(), executors);
        return ItemsRepository.getInstance(database.itemDao(), networkDataSource, executors);
    }

    public static ItemsNetworkDataSource provideNetworkDataSource(Context context) {
        AppExecutors executors = AppExecutors.getInstance();
        return ItemsNetworkDataSource.getInstance(context.getApplicationContext(), executors);
    }

    public static DetailViewModelFactory provideDetailViewModelFactory(Context context, String link) {
        ItemsRepository repository = provideRepository(context.getApplicationContext());
        return new DetailViewModelFactory(repository, link);
    }

    public static MainViewModelFactory provideMainActivityViewModelFactory(Context context, int itemType) {
        ItemsRepository repository = provideRepository(context.getApplicationContext());
        return new MainViewModelFactory(repository, itemType);
    }

}