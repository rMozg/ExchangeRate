package ru.mozgovoy.oleg.exchangerate.model.network;

import android.support.annotation.Nullable;

public interface INetworkHelper {
    @Nullable
    String downloadFileToString(String address);
}
