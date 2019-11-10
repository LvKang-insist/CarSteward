package com.car.core.utils.bean;

import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.utils.bean
 * @time 2019/11/10 21:33
 * @description
 */
public class IndexBean {

    /**
     * status : 1
     * msg : 用户未登录！
     * msgCount :
     * brandName :
     * yearsTypeName :
     * notice : [{"catName":"车榜样首页公告","articleId":"6","catId":"11","articleTitle":"车榜样微信端首页公告","articleImg":"","articleKey":"首页公告","articleContent":"车榜样 为您的汽车生活保驾护航！"},{"catName":"车榜样首页公告","articleId":"5","catId":"11","articleTitle":"车榜样微信端首页公告","articleImg":"","articleKey":"首页公告","articleContent":"车榜样二手车，买的放心，用的安心！"},{"catName":"车榜样首页公告","articleId":"4","catId":"11","articleTitle":"车榜样微信端首页公告","articleImg":"","articleKey":"首页公告","articleContent":"汽车消毒除味就选悦治理，专业专心专注！"},{"catName":"车榜样首页公告","articleId":"3","catId":"11","articleTitle":"车榜样微信端首页公告","articleImg":"","articleKey":"首页公告","articleContent":"车榜样特惠新车，全网最低价！"}]
     * ads : {"indexAds":[],"integralsAds":[],"selfGoodsAds":[],"giftAds":[],"usedCarsAds":[],"tehuiCarData":[]}
     * affiche : []
     */

    private int status;
    private String msg;
    private String msgCount;
    private String brandName;
    private String yearsTypeName;
    private AdsBean ads;
    private List<NoticeBean> notice;
    private List<?> affiche;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getYearsTypeName() {
        return yearsTypeName;
    }

    public void setYearsTypeName(String yearsTypeName) {
        this.yearsTypeName = yearsTypeName;
    }

    public AdsBean getAds() {
        return ads;
    }

    public void setAds(AdsBean ads) {
        this.ads = ads;
    }

    public List<NoticeBean> getNotice() {
        return notice;
    }

    public void setNotice(List<NoticeBean> notice) {
        this.notice = notice;
    }

    public List<?> getAffiche() {
        return affiche;
    }

    public void setAffiche(List<?> affiche) {
        this.affiche = affiche;
    }

    public static class AdsBean {
        private List<?> indexAds;
        private List<?> integralsAds;
        private List<?> selfGoodsAds;
        private List<?> giftAds;
        private List<?> usedCarsAds;
        private List<?> tehuiCarData;

        public List<?> getIndexAds() {
            return indexAds;
        }

        public void setIndexAds(List<?> indexAds) {
            this.indexAds = indexAds;
        }

        public List<?> getIntegralsAds() {
            return integralsAds;
        }

        public void setIntegralsAds(List<?> integralsAds) {
            this.integralsAds = integralsAds;
        }

        public List<?> getSelfGoodsAds() {
            return selfGoodsAds;
        }

        public void setSelfGoodsAds(List<?> selfGoodsAds) {
            this.selfGoodsAds = selfGoodsAds;
        }

        public List<?> getGiftAds() {
            return giftAds;
        }

        public void setGiftAds(List<?> giftAds) {
            this.giftAds = giftAds;
        }

        public List<?> getUsedCarsAds() {
            return usedCarsAds;
        }

        public void setUsedCarsAds(List<?> usedCarsAds) {
            this.usedCarsAds = usedCarsAds;
        }

        public List<?> getTehuiCarData() {
            return tehuiCarData;
        }

        public void setTehuiCarData(List<?> tehuiCarData) {
            this.tehuiCarData = tehuiCarData;
        }
    }

    public static class NoticeBean {
        /**
         * catName : 车榜样首页公告
         * articleId : 6
         * catId : 11
         * articleTitle : 车榜样微信端首页公告
         * articleImg :
         * articleKey : 首页公告
         * articleContent : 车榜样 为您的汽车生活保驾护航！
         */

        private String catName;
        private String articleId;
        private String catId;
        private String articleTitle;
        private String articleImg;
        private String articleKey;
        private String articleContent;

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }

        public String getArticleId() {
            return articleId;
        }

        public void setArticleId(String articleId) {
            this.articleId = articleId;
        }

        public String getCatId() {
            return catId;
        }

        public void setCatId(String catId) {
            this.catId = catId;
        }

        public String getArticleTitle() {
            return articleTitle;
        }

        public void setArticleTitle(String articleTitle) {
            this.articleTitle = articleTitle;
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

        public String getArticleContent() {
            return articleContent;
        }

        public void setArticleContent(String articleContent) {
            this.articleContent = articleContent;
        }
    }
}
