package com.car.tabmine.xieyi;

import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.xieyi
 * @time 2019/10/20 19:00
 * @description
 */
public class GetClauseAgreementBean  {

    /**
     * data : [{"articleContent":"","articleId":"1","articleImg":"","articleKey":"车榜样服务协议","articleTitle":"用户服务协议","catId":"5","catName":"用户服务协议"}]
     * msg : success
     * status : 1
     */

    private String msg;
    private int status;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * articleContent :
         * articleId : 1
         * articleImg :
         * articleKey : 车榜样服务协议
         * articleTitle : 用户服务协议
         * catId : 5
         * catName : 用户服务协议
         */

        private String articleContent;
        private String articleId;
        private String articleImg;
        private String articleKey;
        private String articleTitle;
        private String catId;
        private String catName;

        public String getArticleContent() {
            return articleContent;
        }

        public void setArticleContent(String articleContent) {
            this.articleContent = articleContent;
        }

        public String getArticleId() {
            return articleId;
        }

        public void setArticleId(String articleId) {
            this.articleId = articleId;
        }

        public String getArticleImg() {
            return articleImg;
        }

        public void setArticleImg(String articleImg) {
            this.articleImg = articleImg;
        }

        public String getArticleKey() {
            return articleKey;
        }

        public void setArticleKey(String articleKey) {
            this.articleKey = articleKey;
        }

        public String getArticleTitle() {
            return articleTitle;
        }

        public void setArticleTitle(String articleTitle) {
            this.articleTitle = articleTitle;
        }

        public String getCatId() {
            return catId;
        }

        public void setCatId(String catId) {
            this.catId = catId;
        }

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }
    }
}
