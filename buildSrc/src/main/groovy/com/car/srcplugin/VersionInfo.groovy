package com.car.srcplugin

class VersionInfo {
    private List<UpDataInfoBean> updataInfo;

    public List<UpDataInfoBean> getSetUpDataInfo() {
        return updataInfo;
    }

    public void setSetUpDataInfo(List<UpDataInfoBean> relaseInfo) {
        this.updataInfo = relaseInfo;
    }

    public static class UpDataInfoBean {
        /**
         * versionName :
         * versionCode :
         * versionInfo :
         * versionTime :
         */

        private String versionName;
        private String versionCode;
        private String versionInfo;
        private String versionTime;
        

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionInfo() {
            return versionInfo;
        }

        public void setVersionInfo(String versionInfo) {
            this.versionInfo = versionInfo;
        }

        public String getVersionTime() {
            return versionTime;
        }

        public void setVersionTime(String versionTime) {
            this.versionTime = versionTime;
        }
    }
}