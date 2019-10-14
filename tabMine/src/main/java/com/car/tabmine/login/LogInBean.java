package com.car.tabmine.login;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.login
 * @time 2019/10/14 22:38
 * @description
 */
public class LogInBean {

    /**
     * data : {"isBindingQQ":"1","isBindingWX":"0","loginName":"15129379467","tokenId":"eca2d6cbb4c142ea14bbe9a755f9ba61bdd","userName":"15129379467","userScore":"0","userSex":"0","userStatus":"1","userType":"0"}
     * msg : 登录成功!
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
         * isBindingQQ : 1
         * isBindingWX : 0
         * loginName : 15129379467
         * tokenId : eca2d6cbb4c142ea14bbe9a755f9ba61bdd
         * userName : 15129379467
         * userScore : 0
         * userSex : 0
         * userStatus : 1
         * userType : 0
         */

        private String isBindingQQ;
        private String isBindingWX;
        private String loginName;
        private String tokenId;
        private String userName;
        private String userScore;
        private String userSex;
        private String userStatus;
        private String userType;

        public String getIsBindingQQ() {
            return isBindingQQ;
        }

        public void setIsBindingQQ(String isBindingQQ) {
            this.isBindingQQ = isBindingQQ;
        }

        public String getIsBindingWX() {
            return isBindingWX;
        }

        public void setIsBindingWX(String isBindingWX) {
            this.isBindingWX = isBindingWX;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getTokenId() {
            return tokenId;
        }

        public void setTokenId(String tokenId) {
            this.tokenId = tokenId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
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

        public String getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(String userStatus) {
            this.userStatus = userStatus;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }
    }
}
