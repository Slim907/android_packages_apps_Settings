/*
 * Copyright (C) 2013 The LiquidSmooth Project
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

package com.android.settings.liquid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AboutLiquid extends SettingsPreferenceFragment {

    Preference mDroidDevUrl;
    Preference mFacebookUrl;
    Preference mOurGithubUrl;
    Preference mGooglePlusUrl;
    Preference mOurWebSiteUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.about_liquid);

        mDroidDevUrl = findPreference("about_droiddev");
        mFacebookUrl = findPreference("about_facebook");
        mOurGithubUrl = findPreference("about_github");
        mGooglePlusUrl = findPreference("about_google");
        mOurWebSiteUrl = findPreference("about_website");

        PreferenceGroup devsGroup = (PreferenceGroup) findPreference("about_devs");
        ArrayList<Preference> devs = new ArrayList<Preference>();
        for (int i = 0; i < devsGroup.getPreferenceCount(); i++) {
            devs.add(devsGroup.getPreference(i));
        }
        devsGroup.removeAll();
        devsGroup.setOrderingAsAdded(false);
        Collections.shuffle(devs);
        for(int i = 0; i < devs.size(); i++) {
            Preference p = devs.get(i);
            p.setOrder(i);
            devsGroup.addPreference(p);
        }
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == mDroidDevUrl) {
            launchUrl("http://www.drdevs.com/devs/teamliquid");
        } else if (preference == mFacebookUrl) {
            launchUrl("https://www.facebook.com/liquidsmoothroms");
        } else if (preference == mOurGithubUrl) {
            launchUrl("https://github.com/LiquidSmooth");
        } else if (preference == mGooglePlusUrl) {
            launchUrl("https://plus.google.com/communities/117452480298315341829");
        } else if (preference == mOurWebSiteUrl) {
            launchUrl("http://www.liquidsmooth.net");
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    private void launchUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent donate = new Intent(Intent.ACTION_VIEW, uriUrl);
        getActivity().startActivity(donate);
    }
}
