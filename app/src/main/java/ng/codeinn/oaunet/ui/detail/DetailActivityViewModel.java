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

package ng.codeinn.oaunet.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import ng.codeinn.oaunet.data.ItemsRepository;
import ng.codeinn.oaunet.data.database.Item;

/**
 * {@link ViewModel} for {@link DetailActivity}
 */
public class DetailActivityViewModel extends ViewModel {

    // Weather forecast the user is looking at
    private LiveData<Item> mItem;

    public DetailActivityViewModel(ItemsRepository repository, String link) {
        mItem = repository.getItemByLink(link);
    }

    public LiveData<Item> getItem() {
        return mItem;
    }

}
