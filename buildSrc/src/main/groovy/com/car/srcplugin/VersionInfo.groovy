package com.car.srcplugin

class VersionInfo {
    private List<RelaseInfoBean> relaseInfo;

    public List<RelaseInfoBean> getRelaseInfo() {
        return relaseInfo;
    }

    public void setRelaseInfo(List<RelaseInfoBean> relaseInfo) {
        this.relaseInfo = relaseInfo;
    }

    public static class RelaseInfoBean {
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