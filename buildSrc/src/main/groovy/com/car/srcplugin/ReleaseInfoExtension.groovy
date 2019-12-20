package com.car.srcplugin

class ReleaseInfoExtension {
    String versionCode
    String versionName
    String versionInfo
    String fileName

    @Override
    String toString() {
        "versionCode = ${versionCode}\n" +
                "versionName = ${versionName}\n" +
                "versionInfo = ${versionInfo}\n" +
                "fileName = ${fileName}"
    }
}
