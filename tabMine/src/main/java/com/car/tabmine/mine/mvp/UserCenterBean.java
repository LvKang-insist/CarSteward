package com.car.tabmine.mine.mvp;

import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.mvp
 * @time 2019/10/24 22:18
 * @description
 */
public class UserCenterBean {
    /**
     * affiche : []
     * bankCardCount : 0
     * browsHistoryCount : 15
     * couponsCount : 0
     * favoritesCount : 0
     * msg : success
     * msgCount : 0
     * orderCount : {"cancelCount":0,"waitForAppraisesCount":0,"waitForPayCount":0,"waitForReceiptCount":0,"waitForSendOutCount":0}
     * status : 1
     * userInfo : {"endGrow":"1000","levelGrow":"1","levenOverdueTime":"2020-09-19","loginName":"15129379467","maxEndGrow":"64000","percent":"0%","rankIcon_1":"Upload/rightsIMG/2019-04/5cc0087ba3999.png","rankIcon_2":"Upload/rightsIMG/2019-04/5cc0087da76a2.png","rankIcon_3":"Upload/rightsIMG/2019-04/5cc00880406af.png","rankName":"普通会员","startGrow":"0","userGrowth":"0","userName":"345","userPhoto":"Upload/users/2019-10/5da5d5fb3127d.jpg","userType":"0"}
     */

    private String bankCardCount;
    private String browsHistoryCount;
    private String couponsCount;
    private String favoritesCount;
    private String msg;
    private String msgCount;
    private OrderCountBean orderCount;
    private int status;
    private UserInfoBean userInfo;
    private List<?> affiche;

    public String getBankCardCount() {
        return bankCardCount;
    }

    public void setBankCardCount(String bankCardCount) {
        this.bankCardCount = bankCardCount;
    }

    public String getBrowsHistoryCount() {
        return browsHistoryCount;
    }

    public void setBrowsHistoryCount(String browsHistoryCount) {
        this.browsHistoryCount = browsHistoryCount;
    }

    public String getCouponsCount() {
        return couponsCount;
    }

    public void setCouponsCount(String couponsCount) {
        this.couponsCount = couponsCount;
    }

    public String getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(String favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(String msgCount) {
        this.msgCount = msgCount;
    }

    public OrderCountBean getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(OrderCountBean orderCount) {
        this.orderCount = orderCount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public List<?> getAffiche() {
        return affiche;
    }

    public void setAffiche(List<?> affiche) {
        this.affiche = affiche;
    }

    public static class OrderCountBean {
        /**
         * cancelCount : 0
         * waitForAppraisesCount : 0
         * waitForPayCount : 0
         * waitForReceiptCount : 0
         * waitForSendOutCount : 0
         */

        private int cancelCount;
        private int waitForAppraisesCount;
        private int waitForPayCount;
        private int waitForReceiptCount;
        private int waitForSendOutCount;

        public int getCancelCount() {
            return cancelCount;
        }

        public void setCancelCount(int cancelCount) {
            this.cancelCount = cancelCount;
        }

        public int getWaitForAppraisesCount() {
            return waitForAppraisesCount;
        }

        public void setWaitForAppraisesCount(int waitForAppraisesCount) {
            this.waitForAppraisesCount = waitForAppraisesCount;
        }

        public int getWaitForPayCount() {
            return waitForPayCount;
        }

        public void setWaitForPayCount(int waitForPayCount) {
            this.waitForPayCount = waitForPayCount;
        }

        public int getWaitForReceiptCount() {
            return waitForReceiptCount;
        }

        public void setWaitForReceiptCount(int waitForReceiptCount) {
            this.waitForReceiptCount = waitForReceiptCount;
        }

        public int getWaitForSendOutCount() {
            return waitForSendOutCount;
        }

        public void setWaitForSendOutCount(int waitForSendOutCount) {
            this.waitForSendOutCount = waitForSendOutCount;
        }
    }

    public static class UserInfoBean {
        /**
         * endGrow : 1000
         * levelGrow : 1
         * levenOverdueTime : 2020-09-19
         * loginName : 15129379467
         * maxEndGrow : 64000
         * percent : 0%
         * rankIcon_1 : Upload/rightsIMG/2019-04/5cc0087ba3999.png
         * rankIcon_2 : Upload/rightsIMG/2019-04/5cc0087da76a2.png
         * rankIcon_3 : Upload/rightsIMG/2019-04/5cc00880406af.png
         * rankName : 普通会员
         * startGrow : 0
         * userGrowth : 0
         * userName : 345
         * userPhoto : Upload/users/2019-10/5da5d5fb3127d.jpg
         * userType : 0
         */

        private String endGrow;
        private String levelGrow;
        private String levenOverdueTime;
        private String loginName;
        private String maxEndGrow;
        private String percent;
        private String rankIcon_1;
        private String rankIcon_2;
        private String rankIcon_3;
        private String rankName;
        private String startGrow;
        private String userGrowth;
        private String userName;
        private String userPhoto;
        private String userType;

        public String getEndGrow() {
            return endGrow;
        }

        public void setEndGrow(String endGrow) {
            this.endGrow = endGrow;
        }

        public String getLevelGrow() {
            return levelGrow;
        }

        public void setLevelGrow(String levelGrow) {
            this.levelGrow = levelGrow;
        }

        public String getLevenOverdueTime() {
            return levenOverdueTime;
        }

        public void setLevenOverdueTime(String levenOverdueTime) {
            this.levenOverdueTime = levenOverdueTime;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getMaxEndGrow() {
            return maxEndGrow;
        }

        public void setMaxEndGrow(String maxEndGrow) {
            this.maxEndGrow = maxEndGrow;
        }

        public String getPercent() {
            return percent;
        }

        public void setPercent(String percent) {
            this.percent = percent;
        }

        public String getRankIcon_1() {
            return rankIcon_1;
        }

        public void setRankIcon_1(String rankIcon_1) {
            this.rankIcon_1 = rankIcon_1;
        }

        public String getRankIcon_2() {
            return rankIcon_2;
        }

        public void setRankIcon_2(String rankIcon_2) {
            this.rankIcon_2 = rankIcon_2;
        }

        public String getRankIcon_3() {
            return rankIcon_3;
        }

        public void setRankIcon_3(String rankIcon_3) {
            this.rankIcon_3 = rankIcon_3;
        }

        public String getRankName() {
            return rankName;
        }

        public void setRankName(String rankName) {
            this.rankName = rankName;
        }

        public String getStartGrow() {
            return startGrow;
        }

        public void setStartGrow(String startGrow) {
            this.startGrow = startGrow;
        }

        public String getUserGrowth() {
            return userGrowth;
        }

        public void setUserGrowth(String userGrowth) {
            this.userGrowth = userGrowth;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPhoto() {
            return userPhoto;
        }

        public void setUserPhoto(String userPhoto) {
            this.userPhoto = userPhoto;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }
    }
}
