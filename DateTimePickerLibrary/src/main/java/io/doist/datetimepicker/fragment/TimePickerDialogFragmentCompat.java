/*
 * Copyright (C) 2007 The Android Open Source Project
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

package io.doist.datetimepicker.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import io.doist.datetimepicker.time.OnTimeSetListener;
import io.doist.datetimepicker.time.TimePicker;


/**
 * A dialog that prompts the user for the time of day using a {@link TimePicker}.
 *
 * <p>See the <a href="{@docRoot}guide/topics/ui/controls/pickers.html">Pickers</a>
 * guide.</p>
 */
public class TimePickerDialogFragmentCompat extends DialogFragment {
    public static final String TAG = TimePickerDialogFragmentCompat.class.getName();

    private TimePickerDialogFragmentDelegate mDelegate;

    public TimePickerDialogFragmentCompat() {
        mDelegate = onCreateTimePickerDialogFragmentDelegate();
    }

    protected TimePickerDialogFragmentDelegate onCreateTimePickerDialogFragmentDelegate() {
        return new TimePickerDialogFragmentDelegate();
    }

    public static TimePickerDialogFragmentCompat newInstance(OnTimeSetListener listener, int hourOfDay, int minute,
                                                             boolean is24Hour) {
        TimePickerDialogFragmentCompat fragment = new TimePickerDialogFragmentCompat();
        fragment.setArguments(TimePickerDialogFragmentDelegate.createArguments(hourOfDay, minute, is24Hour));
        fragment.setOnTimeSetListener(listener);
        return fragment;
    }

    @SuppressWarnings("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return mDelegate.onCreateDialog(getActivity(), savedInstanceState, getArguments());
    }

    public void setOnTimeSetListener(OnTimeSetListener listener) {
        mDelegate.setOnTimeSetListener(listener);
    }

    /**
     * Gets the {@link TimePicker} contained in this dialog.
     *
     * @return The calendar view.
     */
    public TimePicker getTimePicker() {
        return mDelegate.getTimePicker();
    }

    /**
     * Sets the current time.
     *
     * @param hourOfDay    The current hour within the day.
     * @param minuteOfHour The current minute within the hour.
     */
    public void updateTime(int hourOfDay, int minuteOfHour) {
        mDelegate.updateTime(hourOfDay, minuteOfHour);
    }
}
