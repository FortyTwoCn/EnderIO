package com.enderio.enderio.client.core_to_sort.gui.screen;

public interface StateRestoringWidget {
    Object getValueForRestore();

    void restoreValue(Object value);
}
