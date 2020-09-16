package com.bits.datatransfer.jcode;

import com.bits.datatransfer.settings.Settings;

public interface FileHandling {

    Settings getSettings();

    void saveSettings(Settings settings);

}
