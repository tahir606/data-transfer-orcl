package com.bits.datatransfer.jcode;

import com.bits.datatransfer.settings.ExportSettings;
import com.bits.datatransfer.settings.ImportSettings;

public interface FileHandling {

    ImportSettings getImportSettings();

    void saveImportSettings(ImportSettings importSettings);

    ExportSettings getExportSettings();

    void saveExportSettings(ExportSettings exportSettings);

}
