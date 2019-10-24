package com.car.tabmine.mvp;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.mvp
 * @time 2019/10/24 21:04
 * @description
 */
public class GetUserInfoBean {
    /**
     * data : {"cashEndMoney":"50000","cashRate":"0.2","cashStartMoney":"100","isGetNoviceGift":"0","loginName":"15129379467","payPwd":0,"userCashBackMoney":"0.00","userGrowth":"0","userMoney":"0.00","userName":"345","userPhone":"15129379467","userPhoto":"Upload/users/2019-10/5da5d5fb3127d.jpg","userScore":"0","userSex":"1","userType":"0"}
     * msg : success
     * status : 1
     */

    private DataBean data;
    private String msg;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * cashEndMoney : 50000
         * cashRate : 0.2
         * cashStartMoney : 100
         * isGetNoviceGift : 0
         * loginName : 15129379467
         * payPwd : 0
         * userCashBackMoney : 0.00
         * userGrowth : 0
         * userMoney : 0.00
         * userName : 345
         * userPhone : 15129379467
         * userPhoto : Upload/users/2019-10/5da5d5fb3127d.jpg
         * userScore : 0
         * userSex : 1
         * userType : 0
         */

        private String cashEndMoney;
        private String cashRate;
        private String cashStartMoney;
        private String isGetNoviceGift;
        private String loginName;
        private int payPwd;
        private String userCashBackMoney;
        private String userGrowth;
        private String userMoney;
        private String userName;
        private String userPhone;
        private String userPhoto;
        private String userScore;
        private String userSex;
        private String userType;

        public String getCashEndMoney() {
            return cashEndMoney;
        }

        public void setCashEndMoney(String cashEndMoney) {
            this.cashEndMoney = cashEndMoney;
        }

        public String getCashRate() {
            return cashRate;
        }

        public void setCashRate(String cashRate) {
            this.cashRate = cashRate;
        }

        public String getCashStartMoney() {
            return cashStartMoney;
        }

        public void setCashStartMoney(String cashStartMoney) {
            this.cashStartMoney = cashStartMoney;
        }

        public String getIsGetNoviceGift() {
            return isGetNoviceGift;
        }

        public void setIsGetNoviceGift(String isGetNoviceGift) {
            this.isGetNoviceGift = isGetNoviceGift;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public int getPayPwd() {
            return payPwd;
        }

        public void setPayPwd(int payPwd) {
            this.payPwd = payPwd;
        }

        public String getUserCashBackMoney() {
            return userCashBackMoney;
        }

        public void setUserCashBackMoney(String userCashBackMoney) {
            this.userCashBackMoney = userCashBackMoney;
        }

        public String getUserGrowth() {
            return userGrowth;
        }

        public void setUserGrowth(String userGrowth) {
            this.userGrowth = userGrowth;
        }

        public String getUserMoney() {
            return userMoney;
        }

        public void setUserMoney(String userMoney) {
            this.userMoney = userMoney;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getUserPhoto() {
            return userPhoto;
        }

        public void setUserPhoto(String userPhoto) {
            this.userPhoto = userPhoto;
        }

        public String getUserScore() {
            return userScore;
        }

        public void setUserScore(String userScore) {
            this.userScore = userScore;
        }

        public String getUserSex() {
            return userSex;
        }

        public void setUserSex(String userSex) {
            this.userSex = userSex;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }
    }
}
